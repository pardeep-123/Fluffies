package com.puppypedia.ui.fragments.profile

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.AddLifePicAdapter
import com.puppypedia.common_adapters.PictureAdapter
import com.puppypedia.model.AddLifeEventModel
import com.puppypedia.model.GetImageModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.showToast
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category_detail.DeleteResponse
import com.puppypedia.ui.main.ui.mypetprofile.MyPetProfileActivity
import com.puppypedia.utils.helper.AppUtils
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import com.zxy.tiny.Tiny
import kotlinx.android.synthetic.main.activity_add_weight.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_life_event_detail.*
import kotlinx.android.synthetic.main.activity_life_event_detail.tvTime
import kotlinx.android.synthetic.main.activity_my_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.tb
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_picture.*
import kotlinx.android.synthetic.main.fragment_picture.rvPicture
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class LifeEventDetailActivity : AppCompatActivity(), AddLifePicAdapter.OnImageClick,
    Observer<RestObservable> {
    private lateinit var time: TimePickerDialog.OnTimeSetListener
    private lateinit var date: DatePickerDialog.OnDateSetListener
    private val myCalendar: Calendar = Calendar.getInstance()

    // arraylist for images
    var images = ArrayList<MultipartBody.Part?>()
    private var imageArray = ArrayList<String>()
    private var imageResponseList = ArrayList<String>()
    private var adapter: AddLifePicAdapter? = null
    private var image = ""
    private var dateString = ""
    private lateinit var mValidationClass: ValidationsClass
    private var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_event_detail)

        mValidationClass = ValidationsClass.getInstance()
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.add_life)
        setAdapter(imageArray)

        etTime.setOnClickListener {
            date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                dateString = AppUtils.dateInString(
                    myCalendar.timeInMillis,
                    "dd-MM-yyyy"
                )
            }
            datePicker(this)


        }

        btSave.setOnClickListener {
           callAddLifeApi()
        }
    }

    private fun datePicker(context: Context) {
        time = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            myCalendar[Calendar.HOUR_OF_DAY] = hour
            myCalendar[Calendar.MINUTE] = minute

            etTime.setText(
                dateString + " " + AppUtils.dateInString(
                    myCalendar.timeInMillis,
                    "hh:mm aa"
                )
            )
        }
        timePicker(this)

        val datePicker = DatePickerDialog(
            context, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]
        )
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()

        // baad mein chlega

    }

    fun callAddLifeApi() {
      //  if (isValid()) {
            callUploadPetApi()

    //    }
    }

    private fun isValid(): Boolean {
        val etTitle = etTitle.text.toString().trim()
        val tvTime = etTime.text.toString().trim()
        val tvDescriptionDetail = tvDescriptionDetail.text.toString().trim()
        var check = false
        if (mValidationClass.checkStringNull(etTitle))
            Helper.showErrorAlert(this, resources.getString(R.string.error_title))
        else if (!mValidationClass.checkStringNull(tvTime))
            Helper.showErrorAlert(this, resources.getString(R.string.error_time))
        else if (!mValidationClass.checkStringNull(tvDescriptionDetail))
            Helper.showErrorAlert(this, resources.getString(R.string.error_description))
        else
            check = true
        return check
    }

    private fun timePicker(context: Context) {
        TimePickerDialog(
            context,
            time,
            myCalendar[Calendar.HOUR_OF_DAY],
            myCalendar[Calendar.MINUTE],
            false
        ).show()
    }

    override fun onImageClick() {
        selectImage()
    }

    // function for images upload
    private fun callUploadPetApi() {
        val map = HashMap<String, RequestBody>()
        map["folder"] = mValidationClass.createPartFromString("pets")
        viewModel.imageUploadmultile(this, true, map, images)
        viewModel.mResponse.observe(this, this)

    }

    private fun selectImage() {
        Album.image(this).singleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(this).title(getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                //   Glide.with(this).load(result[0].path).into(imageView)

                image = result[0].path
                imageArray.add(image)
                setAdapter(imageArray)
                multipartImageGet()

            }.onCancel {
            }.start()

    }

    private fun setAdapter(list: ArrayList<String>) {
        adapter = AddLifePicAdapter(this, list)
        rvAddImages.adapter = adapter
    }

    private fun multipartImageGet(): MultipartBody.Part {

        val imageFile: MultipartBody.Part
        val options = Tiny.FileCompressOptions()
        val result =
            Tiny.getInstance().source(image).asFile().withOptions(options)
                .compressSync()
        val fileReqBody = File(result.outfile).asRequestBody("image/*".toMediaTypeOrNull())
        imageFile =
            MultipartBody.Part.createFormData(
                "image",
                System.currentTimeMillis().toString() + ".jpg",
                fileReqBody
            )

        images.add(imageFile)
        // hit upload api for image
        // callUploadPetApi()
        return imageFile

    }

    override fun onChanged(livedata: RestObservable?) {
        when {

            livedata!!.status == Status.SUCCESS -> {
                if (livedata.data is ImageUploadResponse) {
                    livedata.data.body.forEach {
                        imageResponseList.add(it.image)
                    }
                    image = TextUtils.join(",", imageResponseList)

                    val time = tvTime.text.toString().trim()

                    val map = java.util.HashMap<String, String>()
                    map["pet_id"] = (MyPetProfileActivity().petId)
                    map["title"] = etTitle.text.toString()
                    map["date"] = time
                    map["description"] = tvDescriptionDetail.text.toString()
                    map["pet_image"] = image
                    viewModel.addLifeEvent(this, true, map)
                    viewModel.mResponse.observe(this, this)
                }
                else if (livedata.data is AddLifeEventModel){
                    showToast(livedata.data.msg)
                    finish()
                }
            }
            livedata.status == Status.ERROR -> {
                if (livedata.data != null) {
                    Helper.showErrorAlert(this, livedata.data as String)
                } else {
                    Helper.showErrorAlert(this, livedata.error.toString())
                }
            }
        }
    }
}
package com.puppypedia.ui.main.ui.addhealthproblem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.StatusAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.mypetprofile.PetProfileResponse
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import com.zxy.tiny.Tiny
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_add_health_details.*
import kotlinx.android.synthetic.main.activity_edit_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.tb
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AddHealthDetails : AppCompatActivity(), Observer<RestObservable> {
    private var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
    private lateinit var mValidationClass: ValidationsClass
    private var imageOne = ""
    private var imageTwo = ""
    private var petId = ""
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    var nameList: ArrayList<String> = ArrayList()
    var idList: ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_health_details)
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.add_health_problem)
        mValidationClass = ValidationsClass.getInstance()
        // setSpinner()
        apiPetProfile()
        ivPetProfile1.setOnClickListener {
            selectImage(ivPetProfile1)
        }

        ivPetProfile2.setOnClickListener {
            selectImage(ivPetProfile2)
        }

        btnSubmit.setOnClickListener {
            callAddHealthApi()
        }

        // set spinner
        spinner_trader_distance!!.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {

                    petId = idList[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    fun apiPetProfile() {
        viewModel.getPetProfile(this, true)
        viewModel.mResponse.observe(this, this)
    }

    private fun selectImage(imageView: CircleImageView) {
        Album.image(this).singleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(this).title(getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                Glide.with(this).load(result[0].path).into(imageView)

                if (imageView.id == R.id.ivPetProfile1)
                    imageOne = result[0].path
                else
                    imageTwo = result[0].path
            }.onCancel {
            }.start()
    }

    private fun multipartImageGet(): MultipartBody.Part {
        val imageFile: MultipartBody.Part
        val options = Tiny.FileCompressOptions()
        val result =
            Tiny.getInstance().source(imageOne).asFile().withOptions(options)
                .compressSync()
        val fileReqBody = File(result.outfile).asRequestBody("image/*".toMediaTypeOrNull())
        imageFile =
            MultipartBody.Part.createFormData(
                "image_1",
                System.currentTimeMillis().toString() + ".jpg",
                fileReqBody
            )
        return imageFile

    }

    private fun multipartImageGet1(): MultipartBody.Part {
        val imageFile: MultipartBody.Part
        val options = Tiny.FileCompressOptions()
        val result =
            Tiny.getInstance().source(imageTwo).asFile().withOptions(options)
                .compressSync()
        val fileReqBody = File(result.outfile).asRequestBody("image/*".toMediaTypeOrNull())
        imageFile =
            MultipartBody.Part.createFormData(
                "image_2",
                System.currentTimeMillis().toString() + ".jpg",
                fileReqBody
            )
        return imageFile

    }

    private fun callAddHealthApi() {
        if (isValid()) {
            val map = HashMap<String, RequestBody>()
            map["pet_id"] = mValidationClass.createPartFromString(petId)
            map["description"] =
                mValidationClass.createPartFromString(healthDescription.text.toString())
            viewModel.addHealthDetails(this, true, map, multipartImageGet(), multipartImageGet1())
            viewModel.mResponse.observe(this, this)
        }
    }

    private fun isValid(): Boolean {
        val name = healthDescription.text.toString().trim()
//        val about = etAbout.text.toString().trim()
//        val weight = etWeight.text.toString().trim()

        var check = false
        if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_description))
//        else if (mValidationClass.checkStringNull(about))
//            Helper.showErrorAlert(this, resources.getString(R.string.about))
////        else if (mValidationClass.checkStringNull(weight))
////            Helper.showErrorAlert(this, resources.getString(R.string.error_weight))
//        else if (mValidationClass.checkStringNull(breed))
//            Helper.showErrorAlert(this, resources.getString(R.string.error_breed))
//        else if (mValidationClass.checkStringNull(age))
//            Helper.showErrorAlert(this, resources.getString(R.string.error_age))
        else
            check = true
        return check
    }

    override fun onChanged(liveData: RestObservable?) {
        when {
            liveData!!.status == Status.SUCCESS -> {
                if (liveData.data is PetProfileResponse) {
                    for (i in 0 until liveData.data.body.size) {
                        nameList.add(liveData.data.body[i].name)
                        idList.add(liveData.data.body[i].id)

                    }
                    val adapter1: ArrayAdapter<*> = ArrayAdapter(
                        this, R.layout.size_customspinner,
                        nameList
                    )
                    spinner_trader_distance!!.adapter = adapter1
                }
            }
            liveData.status == Status.ERROR -> {
                if (liveData.data != null) {
                    Helper.showErrorAlert(this, liveData.data as String)
                } else {
                    Helper.showErrorAlert(this, liveData.error.toString())
                }
            }
        }
    }
}
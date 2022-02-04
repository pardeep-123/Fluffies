package com.puppypedia.ui.main.ui.addhealthproblem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.StatusAdapter
import com.puppypedia.model.AddHealthDetail
import com.puppypedia.model.GetHealthListModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.showToast
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.mypetprofile.PetProfileResponse
import com.puppypedia.utils.helper.others.Constants
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
import kotlinx.android.synthetic.main.item_health_details.view.*
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
    var idList: ArrayList<String> = ArrayList()

    var images = ArrayList<MultipartBody.Part?>()
   // make list to get data from previous screen

    var list: GetHealthListModel.Body?=null
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
            callUploadPetApi()
        }

        // set spinner
        spinner_trader_distance!!.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {

                    petId = if (position != 0) {
                        idList[position-1]

                    } else {
                        ""
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        // get intent from previous screen
        if(intent.extras?.get("data")!=null){
            list = intent.extras?.get("data") as GetHealthListModel.Body?
            healthDescription.setText(list?.description)
           // spinner_trader_distance.setSelection()
            // Set Images
            if (list?.image1!="") {
                Glide.with(this)
                    .load(Constants.PET_IMAGE_URL +list?.image1)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
                    .placeholder(R.drawable.place_holder)
                    .into(ivPetProfile1)
            }
            if (list?.image2!=""){
                Glide.with(this)
                    .load(Constants.PET_IMAGE_URL +list?.image2)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
                    .placeholder(R.drawable.logo)
                    .into(ivPetProfile2)
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

                if (imageView.id == R.id.ivPetProfile1) {
                    imageOne = result[0].path
                    multipartImageGet()
                }
                else {
                    imageTwo = result[0].path
                    multipartImageGet1()
                }
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
                "image",
                System.currentTimeMillis().toString() + ".jpg",
                fileReqBody
            )
        images.add(imageFile)
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
                "image",
                System.currentTimeMillis().toString() + ".jpg",
                fileReqBody
            )
        images.add(imageFile)
        return imageFile

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

    // function for images upload
    private fun callUploadPetApi() {
        if (isValid()) {
            val map = HashMap<String, RequestBody>()
            map["folder"] = mValidationClass.createPartFromString("pets")
            viewModel.imageUploadmultile(this, true, map, images)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(liveData: RestObservable?) {
        when {
            liveData!!.status == Status.SUCCESS -> {
                if (liveData.data is PetProfileResponse) {
                    for (i in 0 until liveData.data.body.size) {
                        nameList.add(0,"Select Pet")
                        nameList.add(liveData.data.body[i].name)
                        idList.add(liveData.data.body[i].id.toString())

                    }
                    val adapter1: ArrayAdapter<*> = ArrayAdapter(
                        this, R.layout.size_customspinner,
                        nameList
                    )
                    spinner_trader_distance!!.adapter = adapter1
                } else if(liveData.data is AddHealthDetail){
                    showToast(liveData.data.msg)
                    finish()
                } else if (liveData.data is ImageUploadResponse){
                     val image = liveData.data.body[0].image
                     val image2 = liveData.data.body[1].image
                    val map = HashMap<String, RequestBody>()
                    map["pet_id"] = mValidationClass.createPartFromString(petId)
                    map["image_1"] = mValidationClass.createPartFromString(image)
                    map["image_2"] = mValidationClass.createPartFromString(image2)
                    map["description"] = mValidationClass.createPartFromString(healthDescription.text.toString())
                    viewModel.addHealthDetails(this, true, map)
                    viewModel.mResponse.observe(this, this)
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
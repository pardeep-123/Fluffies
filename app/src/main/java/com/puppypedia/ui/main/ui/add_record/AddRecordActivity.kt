package com.puppypedia.ui.main.ui.add_record


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.ClickCallBack
import com.puppypedia.common_adapters.ImageAdapter
import com.puppypedia.common_adapters.MultiEditImageAdapter

import com.puppypedia.common_adapters.MultipleImageAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category_detail.GetPetResponse
import com.puppypedia.ui.main.ui.mypetprofile.PetProfileResponse
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget

import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AddRecordActivity : AppCompatActivity(), Observer<RestObservable>, View.OnClickListener,
    ClickCallBack {
    var data: GetPetResponse.Body? = null
    var petId = ""
    var firstImage = ""
    lateinit var sharedPrefUtil: SharedPrefUtil
    var images = ArrayList<MultipartBody.Part?>()
    lateinit var adapter: MultipleImageAdapter
    lateinit var multiEditImageAdapter: MultiEditImageAdapter
    var aboutResponse: PetProfileResponse? = null
    private var mAlbumFiles = ArrayList<AlbumFile>()
    private var stringImage = ArrayList<String>()
    private val viewModel: AllViewModel by lazy { ViewModelProvider(this).get(AllViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)
        mValidationClass = ValidationsClass.getInstance()
        sharedPrefUtil = SharedPrefUtil(this)
        btnAddRecord.setOnClickListener(this)
        tvAdd.setOnClickListener(this)
        btnAddRecord.setOnClickListener(this)
        toolbar.tv_title.setTextColor(getColor(R.color.black))
        toolbar.iv_back.setImageResource(R.drawable.back_arrow)
        toolbar.iv_back.setOnClickListener {
            onBackPressed()
        }
        if (intent.getStringExtra("from") == "add") {
            toolbar.tv_title.text = "Add Record"
        } else {
            toolbar.tv_title.text = "Edit Record"
            btnAddRecord.text = "Submit"
            data = (intent.getSerializableExtra("data") as GetPetResponse.Body)
            petId = data!!.id.toString()
            edDescription.setText(data!!.description)
            firstImage = data!!.petImages[0].petImage
            rv_img.adapter = ImageAdapter(this, data!!.petImages)

        }
    }

    private fun isValid(): Boolean {
        val description = edDescription.text.toString().trim()
        var check = false

        /*   if (mValidationClass.checkStringNull(image))
               Helper.showErrorAlert(this, "Please select your pet image")
           else */
        if (mValidationClass.checkStringNull(description))
            Helper.showErrorAlert(this, resources.getString(R.string.enter_description))
        else
            check = true
        return check
    }

    private fun callUploadPetApi() {
        if (isValid()) {
            val map = HashMap<String, RequestBody>()
            map["folder"] = mValidationClass.createPartFromString("pets")
            viewModel.imageUploadmultile(this, true, map, images)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is AddPetRecordResponse) {
                    val registerResponse: AddPetRecordResponse = it.data
                    if (registerResponse.code == Constants.success_code) {
                        // SharedPrefUtil.getInstance().savePostId(registerResponse.body[0].petImages[0].postId.toString())
                        finish()
                    } else {
                        Helper.showErrorAlert(this, registerResponse.code.toString())
                    }
                }
                if (it.data is ImageUploadResponse) {
                    for (i in 0 until it.data.body.size) {
                        stringImage.add(it.data.body[i].image)
                    }
                    val description = edDescription.text.toString().trim()
                    val map = HashMap<String, String>()
                    map["petid"] = sharedPrefUtil.petId.toString()
                    map["description"] = description
                    map["pet_image"] =
                        stringImage.toString().replace("[", "").replace("]", "").replace(" ", "")
                    viewModel.apiAddPuppyDescription(this, true, map)
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(this, it.data.toString())
                } else {
                    Helper.showErrorAlert(this, it.error.toString())
                }
            }
        }
    }

    fun multipartImageGet() {
        images.clear()
        images = ArrayList<MultipartBody.Part?>()
        for (i in 0..mAlbumFiles.size - 1) {
            var newFile: File? = null
            var imageFileBody: MultipartBody.Part? = null
            if (mAlbumFiles.get(i).path != "") {
                newFile = File(mAlbumFiles.get(i).path)
            }
            if (newFile != null && newFile.exists() && !newFile.equals("")) {
                val mediaType: MediaType?
                if (mAlbumFiles.get(i).path.endsWith("png")) {
                    mediaType = "image/png".toMediaTypeOrNull()
                } else {
                    mediaType = "image/jpeg".toMediaTypeOrNull()
                }
                val requestBody: RequestBody = newFile.asRequestBody(mediaType)
                imageFileBody =
                    MultipartBody.Part.createFormData("image", newFile.name, requestBody)
            }
            images.add(imageFileBody)
        }
    }

    private fun selectImage() {
        Album.image(this).multipleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(this).title(getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                Log.e(
                    "checkimagelink",
                    "-////-" + images.toString().replace("[", "").replace("]", "")
                )
                rv_edit_img.adapter = MultipleImageAdapter(this, mAlbumFiles, "complete")

            }
            .onCancel {
            }.start()

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnAddRecord -> {
                multipartImageGet()
                callUploadPetApi()
            }
            R.id.tvAdd -> {
                selectImage()
            }
        }
    }

    override fun onItemClick(pos: Int, value: String) {
        when (value) {
            "pet" -> {
                petId = aboutResponse!!.body[pos].id.toString()
            }
        }
    }
}
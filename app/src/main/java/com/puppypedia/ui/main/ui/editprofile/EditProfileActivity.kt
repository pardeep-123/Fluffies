package com.puppypedia.ui.main.ui.editprofile

import android.content.Context

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class EditProfileActivity : AppCompatActivity(), Observer<RestObservable> {
    private lateinit var mValidationClass: ValidationsClass
    var firstimage = ""
    lateinit var context: Context
    private var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
    var check = false
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        mValidationClass = ValidationsClass.getInstance()
        context = this
        tb.tv_title.text = getString(R.string.edit_profile)
        clicksHandle()

        firstimage = intent.getStringExtra("image").toString()
        etName.setText(intent.getStringExtra("name").toString())
        etEmail.setText(intent.getStringExtra("email").toString())
        //  Log.e("editprofile", "" + intent.getStringExtra("email") + intent.getStringExtra("name"))
        Glide.with(this).load(Constants.IMAGE_URL + intent.getStringExtra("image").toString())
            .placeholder(R.drawable.profile_pic).into(rivProfile)
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        btnUpdate.setOnClickListener {
            CallApiEDitProfile()
        }
        ivCamera.setOnClickListener {
            // apiEDitProfile()
            mAlbumFiles = ArrayList()
            mAlbumFiles.clear()
            selectImage()
        }
    }

    private fun selectImage() {
        Album.image(this).singleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(this).title(getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                Glide.with(context).load(result[0].path).into(rivProfile)
                firstimage = result[0].path
            }.onCancel {
            }.start()
    }

    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        var check = false
        if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_name))
        else if (mValidationClass.checkStringNull(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_email))
        else if (!mValidationClass.isValidEmail(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_validemail))
        else
            check = true
        return check
    }

    private fun CallApiEDitProfile() {
        if (isValid()) {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val map = HashMap<String, String>()
            map["name"] = name
            map["email"] = email
            map["image"] = "zczzxcxzcxzc"

            viewModel.editProfileApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is EditProfileResponse) {
                    val aboutResponse: EditProfileResponse = it.data
                    SharedPrefUtil.getInstance().saveAuthToken(aboutResponse.body.authKey)
                    SharedPrefUtil.getInstance().saveImage(aboutResponse.body.image)
                    SharedPrefUtil.getInstance().saveUserId(aboutResponse.body.id.toString())
                    SharedPrefUtil.getInstance().saveEmail(aboutResponse.body.email)
                    SharedPrefUtil.getInstance().saveName(aboutResponse.body.name)
                    finish()
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(this, it.data as String)
                } else {
                    Helper.showErrorAlert(this, it.error.toString())
                }
            }
        }
    }
}
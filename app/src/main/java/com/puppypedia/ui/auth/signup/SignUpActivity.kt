package com.puppypedia.ui.auth.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.auth.AuthViewModel
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.main.ui.petdetail.YourPetDetailActivity
import com.puppypedia.utils.helper.MyApplication
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import com.zxy.tiny.Tiny
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class SignUpActivity : AppCompatActivity(), Observer<RestObservable> {
    private var image = ""
    var token = ""
    private var mAlbumFiles = ArrayList<AlbumFile>()
    private val viewModel: AuthViewModel by lazy { ViewModelProvider(this).get(AuthViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        getFirebaseToken()
        SharedPrefUtil.init(this)
        passwordShowHide()
        mValidationClass = ValidationsClass.getInstance()
        btnSignUp.setOnClickListener {
            callSignupApi()
        }
        signUp.setOnClickListener { onBackPressed() }
        ivCamera.setOnClickListener {

            callImagePicker(this)
        }
    }

    private fun passwordShowHide() {
        cbPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                etPassword.setSelection(etPassword.text.length)
            } else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                etPassword.setSelection(etPassword.text.length)
            }
        }

        cbConfirmPassPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                etConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                etConfirmPass.setSelection(etConfirmPass.text.length)
            } else {
                etConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
                etConfirmPass.setSelection(etConfirmPass.text.length)
            }
        }
    }
    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPass.text.toString().trim()
        var check = false
        /*if (!mValidationClass.isNetworkConnected)
            Helper.showErrorAlert(this, resources.getString(R.string.no_internet))
        else */if (mValidationClass.checkStringNull(image))
            Helper.showErrorAlert(this, "Please select profile image")
        else if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_name))
        else if (mValidationClass.checkStringNull(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_email))
        else if (!mValidationClass.isValidEmail(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_validemail))
        else if (mValidationClass.checkStringNull(password))
            Helper.showErrorAlert(this, resources.getString(R.string.error_password))
        else if (password.length < 6)
            Helper.showErrorAlert(this, resources.getString(R.string.error_password_length))
        else if (mValidationClass.checkStringNull(confirmPassword))
            Helper.showErrorAlert(this, "Please enter confirm password")
        else if (password != confirmPassword)
            Helper.showErrorAlert(this, "Password doesn't match")
        else
            check = true
        return check
    }

    private fun callSignupApi() {
        if (isValid()) {
            val map = HashMap<String, RequestBody>()
            map["folder"] = mValidationClass.createPartFromString("users")
            viewModel.imageUpload(this, true, map, multipartImageGet())
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is SignUpResponse) {
                    val registerResponse: SignUpResponse = it.data
                    if (registerResponse.code == Constants.success_code) {
                        SharedPrefUtil.getInstance().saveImage(registerResponse.body.image)
                        SharedPrefUtil.getInstance().saveUserId(registerResponse.body.id.toString())
                        SharedPrefUtil.getInstance().saveEmail(registerResponse.body.email)
                        SharedPrefUtil.getInstance().saveName(registerResponse.body.name)

                        MyApplication.instance!!.setString(
                            Constants.AuthKey, registerResponse.body.authKey
                        )
                        startActivity(
                            Intent(this, YourPetDetailActivity::class.java)
                                .putExtra(
                                    "auth", registerResponse.body.authKey
                                )
                        )
                    } else {
                        Helper.showErrorAlert(this, registerResponse.code.toString())
                    }
                }

                if (it.data is ImageUploadResponse) {
                    val name = etName.text.toString().trim()
                    val email = etEmail.text.toString().trim()
                    val password = etPassword.text.toString().trim()
                    val confirmPassword = etConfirmPass.text.toString().trim()
                    val map = HashMap<String, String>()
                    map["name"] = name
                    map["email"] = email
                    map["password"] = password
                    map["confirmPassword"] = confirmPassword
                    map["image"] = it.data.body[0].image
                    map["deviceType"] = Constants.Device_Type
                    map["deviceToken"] = token
                    viewModel.signUpApi(this, true, map)

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


    fun multipartImageGet(): MultipartBody.Part {
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
        return imageFile

    }

    private fun callImagePicker(context: Context) {
        Album.image(context)
            .singleChoice()
            .camera(true)
            .columnCount(4)
            .widget(
                Widget.newDarkBuilder(context)
                    .title(context.getString(R.string.app_name))
                    .build()
            )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                if (result.isNotEmpty()) {
                    Glide.with(this).load(result[0].path).into(ivProfile)
                    image = result[0].path
                }
            }
            .onCancel {
                // Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
            .start()
    }

    private fun getFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("Login", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            token = task.result.toString()
            SharedPrefUtil.getInstance().saveFcmToken(task.result.toString())

            Log.e("Fetching FCM ", token!!)
        })
    }
}
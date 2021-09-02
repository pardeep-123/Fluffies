package com.puppypedia.ui.auth.signup
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.auth.AuthViewModel
import com.puppypedia.ui.main.ui.petdetail.YourPetDetailActivity
import com.puppypedia.utils.helper.MyApplication
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity(), View.OnClickListener, Observer<RestObservable> {
    private var firstImage = ""
    private var mAlbumFiles = ArrayList<AlbumFile>()
    private val viewModel: AuthViewModel by lazy { ViewModelProvider(this).get(AuthViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        SharedPrefUtil.init(this)
        passwordShowHide()
        mValidationClass = ValidationsClass.getInstance()
        btnSignUp.setOnClickListener {
            callSignupApi()
        }
        signUp.setOnClickListener { onBackPressed() }
        ivCamera.setOnClickListener {

            selectImage()
        }
    }

    private fun passwordShowHide() {
        cbPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        cbConfirmPassPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                etConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                etConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    override fun onClick(p0: View?) {}

    private fun selectImage() {
        Album.image(this).singleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(this).title(getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                Glide.with(this).load(result[0].path).into(ivProfile)
                firstImage = result[0].path
            }.onCancel {
            }.start()
    }

    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPass.text.toString().trim()

        var check = false
        /*if (!mValidationClass.isNetworkConnected)
            Helper.showErrorAlert(this, resources.getString(R.string.no_internet))
        else */if (mValidationClass.checkStringNull(name))
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
            Helper.showErrorAlert(this, "Password Don't match")
        else
            check = true
        return check
    }

    private fun callSignupApi() {
        if (isValid()) {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPass.text.toString().trim()
            val map = HashMap<String, String>()
            map["name"] = name
            map["email"] = email
            map["password"] = password
            map["confirmPassword"] = confirmPassword
            map["image"] = "zczzxcxzcxzc"
            map["deviceType"] = Constants.Device_Type
            map["deviceToken"] = "z"

            viewModel.signUpApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is SignUpResponse) {
                    val registerResponse: SignUpResponse = it.data
                    if (registerResponse.code == Constants.success_code) {
                        SharedPrefUtil.getInstance().saveAuthToken(registerResponse.body.authKey)
                        SharedPrefUtil.getInstance().saveImage(registerResponse.body.image)
                        SharedPrefUtil.getInstance().saveUserId(registerResponse.body.id.toString())
                        SharedPrefUtil.getInstance().saveEmail(registerResponse.body.email)
                        SharedPrefUtil.getInstance().saveName(registerResponse.body.name)
                        SharedPrefUtil.getInstance().isLogin = true

                        MyApplication.instance!!.setString(
                            Constants.AuthKey,
                            registerResponse.body.authKey
                        )
                        startActivity(Intent(this, YourPetDetailActivity::class.java))


                    } else {
                        Helper.showErrorAlert(this, registerResponse.code.toString())
                    }
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
}
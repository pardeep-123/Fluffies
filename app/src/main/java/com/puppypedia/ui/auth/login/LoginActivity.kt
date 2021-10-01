package com.puppypedia.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.auth.AuthViewModel
import com.puppypedia.ui.auth.forgotpassword.ForgotPasswordActivity
import com.puppypedia.ui.auth.signup.SignUpActivity
import com.puppypedia.ui.main.ui.home.HomeActivity
import com.puppypedia.utils.helper.MyApplication
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), Observer<RestObservable>, View.OnClickListener {
    private lateinit var mValidationClass: ValidationsClass
    var token = ""
    var select: Boolean = false
    private val viewModel: AuthViewModel
            by lazy { ViewModelProviders.of(this).get(AuthViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mValidationClass = ValidationsClass.getInstance()
        getFirebaseToken()
        SharedPrefUtil.init(this)
        passwordShowHide()
        tvForgotPassword.setOnClickListener(this)
        btnSignIn.setOnClickListener(this)
        llSignUp.setOnClickListener(this)

        try {
            if (!SharedPrefUtil.getInstanceRemember().getPassword().equals("")
                && SharedPrefUtil.getInstanceRemember().getPassword() != null
            ) {
                chkbox.isChecked = true
                etEmail.setText(SharedPrefUtil.getInstanceRemember().getEmailRememberMe())
                etPassword.setText(SharedPrefUtil.getInstanceRemember().getPassword())
            }
        } catch (e: Exception) {
        }

    }
    private fun passwordShowHide() {
        cbPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }
    private fun isValid(): Boolean {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        var check = false
        if (!mValidationClass.isNetworkConnected)
            Helper.showErrorAlert(this, resources.getString(R.string.no_internet))
        else if (mValidationClass.checkStringNull(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_email))
        else if (!mValidationClass.isValidEmail(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_validemail))
        else if (mValidationClass.checkStringNull(password))
            Helper.showErrorAlert(this, resources.getString(R.string.error_password))
        else
            check = true
        return check
    }
    fun callLoginApi() {
        if (isValid()) {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val map = HashMap<String, String>()
            map["email"] = email
            map["password"] = password
            map["deviceType"] = Constants.Device_Type
            map["deviceToken"] = token
            viewModel.loginApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is LoginResponse) {
                    val registerResponse: LoginResponse = it.data
                    if (registerResponse.code == Constants.success_code) {
                        SharedPrefUtil.getInstance().saveFcmToken(token)
                        SharedPrefUtil.getInstance().saveAuthToken(registerResponse.body.authKey)
                        // SharedPrefUtil.getInstance().saveUserId(registerResponse.body.id.toString())
                        SharedPrefUtil.getInstance().saveEmail(registerResponse.body.email)
                        SharedPrefUtil.getInstance().saveImage(registerResponse.body.image)
                        SharedPrefUtil.getInstance().saveName(registerResponse.body.name)
                        Log.e("DEVICETOKEN", "-----  " + registerResponse.body.deviceToken)
                        SharedPrefUtil.getInstance().isLogin = true
                        //  sharedPref.setSKIP(false)
                        MyApplication.instance!!.setString(
                            Constants.AuthKey,
                            registerResponse.body.authKey
                        )
                        startActivity(Intent(this, HomeActivity::class.java))
                        finishAffinity()
                        if (chkbox.isChecked) {
                            SharedPrefUtil.getInstanceRemember()
                                .saveEmailRememberMe(etEmail.text.toString())
                            SharedPrefUtil.getInstanceRemember()
                                .savePassword(etPassword.text.toString())
                            Log.e(
                                "lllll",
                                "---" + SharedPrefUtil.getInstanceRemember().getEmailRememberMe()
                            )

                        } else {
                            SharedPrefUtil.getInstanceRemember()
                                .saveEmailRememberMe("")
                            SharedPrefUtil.getInstanceRemember()
                                .savePassword("")
                        }
                    } else {
                        Helper.showErrorAlert(this, registerResponse.code as String)
                    }
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
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvForgotPassword -> {
                startActivity(Intent(this, ForgotPasswordActivity::class.java))
            }
            R.id.btnSignIn -> {
                callLoginApi()
            }
            R.id.llSignUp -> {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }
    }

    private fun getFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("Login", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            token = task.result.toString()
            Log.e("Fetching FCM ", token)
        })
    }

}
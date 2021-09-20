package com.puppypedia.ui.auth.forgotpassword

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.activity_forgot_password.*


class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener,
    Observer<RestObservable> {
    val context: Context = this
    private lateinit var mValidationClass: ValidationsClass
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        mValidationClass = ValidationsClass.getInstance()
        btnSubmit.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSubmit -> {
                  callApi()
            }
        }
    }
    private fun isValid(): Boolean {
        val email = etEmail.text.toString().trim()
        var check = false
        if (mValidationClass.checkStringNull(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_email))
        else if (!mValidationClass.isValidEmail(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_validemail))
        else
            check = true
        return check
    }

    fun callApi() {
        if (isValid()) {
            val email = etEmail.text.toString().trim()
            val map = HashMap<String, String>()
            map["email"] = email
            viewModel.apiForgot(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is ForgotPasswordResponse) {
                    val aboutResponse: ForgotPasswordResponse = it.data
                    if (aboutResponse.code == Constants.success_code) {
                        finish()
                        // finishAffinity()
                    } else {
                        Helper.showErrorAlert(this, aboutResponse.code as String)
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
}
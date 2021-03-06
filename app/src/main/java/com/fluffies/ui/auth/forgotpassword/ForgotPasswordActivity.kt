package com.fluffies.ui.auth.forgotpassword

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.fluffies.R
import com.fluffies.restApi.RestObservable
import com.fluffies.ui.main.ui.AllViewModel
import com.fluffies.utils.helper.others.Constants
import com.fluffies.utils.helper.others.Helper
import com.fluffies.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*


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
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
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
                        Toast.makeText(this, "Email sent successfully", Toast.LENGTH_LONG).show()
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
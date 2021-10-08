package com.puppypedia.ui.main.ui.changepassword

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class ChangePasswordActivity : AppCompatActivity(), Observer<RestObservable> {

    private lateinit var mValidationClass: ValidationsClass
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        mValidationClass = ValidationsClass.getInstance()
        SharedPrefUtil.init(this)
        tb.tv_title.text = getString(R.string.change_password)
        clicksHandle()
        passwordShowHide()
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        btnSave.setOnClickListener {
            //onBackPressed()
            changePasswordApi()
        }

    }

    private fun passwordShowHide() {
        cbOldPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                edtOldPass.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                edtOldPass.setSelection(edtOldPass.text.length)
            } else {
                edtOldPass.transformationMethod = PasswordTransformationMethod.getInstance()
                edtOldPass.setSelection(edtOldPass.text.length)
            }
        }

        cbNewPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                edtNewPass.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                edtNewPass.setSelection(edtNewPass.text.length)
            } else {
                edtNewPass.transformationMethod = PasswordTransformationMethod.getInstance()
                edtNewPass.setSelection(edtNewPass.text.length)
            }
        }

        cbConfirmNewPassword.setOnCheckedChangeListener { compoundButton, boolean ->
            if (boolean) {
                edtConfirmNewPass.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                edtConfirmNewPass.setSelection(edtConfirmNewPass.text.length)
            } else {
                edtConfirmNewPass.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                edtConfirmNewPass.setSelection(edtConfirmNewPass.text.length)
            }
        }
    }
    private fun isValid(): Boolean {
        var check = false
        if (!mValidationClass.isNetworkConnected)
            Helper.showErrorAlert(this, resources.getString(R.string.no_internet))
        else if (mValidationClass.checkStringNull(edtOldPass.text.toString().trim()))
            Helper.showErrorAlert(this, resources.getString(R.string.error_password))
        else if (!mValidationClass.isValidPassword(edtOldPass.text.toString().trim()))
            Helper.showErrorAlert(this, resources.getString(R.string.error_password_length))
        else if (mValidationClass.checkStringNull(edtNewPass.text.toString().trim()))
            Helper.showErrorAlert(this, resources.getString(R.string.error_new_password))
        else if (!mValidationClass.isValidPassword(edtNewPass.text.toString().trim()))
            Helper.showErrorAlert(this, resources.getString(R.string.error_password_length))
        else if (mValidationClass.checkStringNull(edtConfirmNewPass.text.toString().trim()))
            Helper.showErrorAlert(this, resources.getString(R.string.error_cpassword))
        else if (!edtNewPass.text.toString().trim().equals(edtConfirmNewPass.text.toString())) {
            Helper.showErrorAlert(this, resources.getString(R.string.error_password_not_matched))
        } else
            check = true
        return check
    }

    private fun changePasswordApi() {
        if (isValid()) {
            val map = HashMap<String, String>()
            map.put("old_password", edtOldPass.text.toString().trim())
            map.put("new_password", edtNewPass.text.toString().trim())
            map.put("confirm_password", edtConfirmNewPass.text.toString().trim())

            viewModel.changePasswordApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is ChangePasswordResponse) {
                    val commonResponse: ChangePasswordResponse = it.data
                    if (commonResponse.code == Constants.success_code) {
                        Helper.showSuccessAlert(
                            this,
                            commonResponse.msg + "Password Changed Successfully"
                        )
                        /*  val intent = Intent(this, LoginActivity::class.java)
                          intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                          startActivity(intent)*/
                        finish()
                    } else {
                        Helper.showErrorAlert(this, commonResponse.code as String)
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
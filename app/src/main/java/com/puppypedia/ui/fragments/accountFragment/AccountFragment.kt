package com.puppypedia.ui.fragments.accountFragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.auth.login.LoginActivity
import com.puppypedia.ui.commomModel.LogoutResponse
import com.puppypedia.ui.commomModel.NotificationOnOffModel
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.about_us.AboutUsActivity
import com.puppypedia.ui.main.ui.changepassword.ChangePasswordActivity
import com.puppypedia.ui.main.ui.mypetprofile.MyPetProfileActivity
import com.puppypedia.ui.main.ui.profile.ProfileActivity
import com.puppypedia.utils.helper.AppConstant
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment(), Observer<RestObservable> {
    lateinit var v: View
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    var status = "0"
    lateinit var dialog: Dialog
    var isFirst = false
    var logintype = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_account, container, false)

/*
        tvname.text = getPrefrence(Constants.name,"")
        tvEmail.text = getPrefrence(Constants.name,"")
        Glide.with(this@DrawerActivity).load(getPrefrence(Constants.image,""))
            .placeholder(R.drawable.placehoder)
            .into(civProfile)*/


        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicksHandle()
    }

    private fun clicksHandle() {
        civProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
        rlMyPets.setOnClickListener {
            startActivity(Intent(requireContext(), MyPetProfileActivity::class.java))
        }
        rlChangePassword.setOnClickListener {
            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
        }
        rlAboutUs.setOnClickListener {
            startActivity(Intent(requireContext(), AboutUsActivity::class.java))
        }
        rlTermsCondition.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            intent.putExtra(AppConstant.HEADING, 1)
            startActivity(intent)
        }
        rlPrivacyPolicy.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            intent.putExtra(AppConstant.HEADING, 2)
            startActivity(intent)
        }
        btnLogout.setOnClickListener {
            logoutDialog()
        }
        sw_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            status = if (isChecked) {
                "1"
            } else {
                " 0"
            }
            api(status)
        }
    }

    fun api(status: String) {
        viewModel.apiNotiOnOff(requireActivity(), status, true)
        viewModel.mResponse.observe(requireActivity(), this)
    }

    fun apiLogout() {
        viewModel.getLogout(requireActivity(), true)
        viewModel.mResponse.observe(requireActivity(), this)
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is NotificationOnOffModel) {
                    val aboutResponse: NotificationOnOffModel = it.data
                    if (logintype.equals("true")) {
                        logoutDialog()
                    }
                }
                if (it.data is LogoutResponse) {
                    SharedPrefUtil.getInstance().clear()
                    startActivity(Intent(activity, LoginActivity::class.java))
                    activity?.finishAffinity()

                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(requireActivity(), it.data as String)
                } else {
                    Helper.showErrorAlert(requireActivity(), it.error.toString())
                }
            }
        }
    }

    private fun logoutDialog() {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_logout)
        dialog.show()
        dialog.findViewById<AppCompatButton>(R.id.btnYes).setOnClickListener {
            dialog.dismiss()
            apiLogout()

        }
        dialog.findViewById<AppCompatButton>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()
        }
    }
}
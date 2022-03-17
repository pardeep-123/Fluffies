package com.fluffies.ui.fragments.accountFragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.fluffies.R
import com.fluffies.restApi.RestObservable
import com.fluffies.ui.auth.login.LoginActivity
import com.fluffies.ui.commomModel.LogoutResponse
import com.fluffies.ui.commomModel.NotificationOnOffModel
import com.fluffies.ui.main.ui.AllViewModel
import com.fluffies.ui.main.ui.about_us.AboutUsActivity
import com.fluffies.ui.main.ui.addhealthproblem.AddHealthProblemActivity
import com.fluffies.ui.main.ui.changepassword.ChangePasswordActivity
import com.fluffies.ui.main.ui.mypetprofile.MyPetProfileActivity
import com.fluffies.ui.main.ui.profile.ProfileActivity
import com.fluffies.utils.helper.AppConstant
import com.fluffies.utils.helper.MyApplication
import com.fluffies.utils.helper.others.Constants
import com.fluffies.utils.helper.others.Helper
import com.fluffies.utils.helper.others.SharedPrefUtil
import com.fluffies.utils.helper.others.SharedPrefUtil.NAME
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*


class AccountFragment : Fragment(), Observer<RestObservable> {
    lateinit var v: View
    private val viewModel: AllViewModel
    by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    var status = "0"
    lateinit var dialog: Dialog
    var isFirst = false
    lateinit var sharedPrefUtil: SharedPrefUtil
    var logintype = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_account, container, false)
        sharedPrefUtil = SharedPrefUtil(requireContext())
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicksHandle()
    }

    override fun onResume() {
        v.tvName.text = sharedPrefUtil.name
        v.tvEmail.text = sharedPrefUtil.email
        Glide.with(this).load(Constants.IMAGE_URL + sharedPrefUtil.image)
            .placeholder(R.drawable.profile).into(v.civProfile)
        super.onResume()
    }

    private fun clicksHandle() {
        civProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
        rlMyPets.setOnClickListener {
            startActivity(Intent(requireContext(), MyPetProfileActivity::class.java))
        }
        rlAddHealthProblem.setOnClickListener {
            startActivity(Intent(requireContext(),AddHealthProblemActivity::class.java))
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
            if (isChecked) {
                status = "1"
                Toast.makeText(context, "Notification On", Toast.LENGTH_SHORT).show()
            } else {
                status = " 0"
                Toast.makeText(context, "Notification Off", Toast.LENGTH_SHORT).show()
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
                    Log.e("lllll", "ppp    " + SharedPrefUtil.getInstanceRemember().getPassword())
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
            Log.e("msg", "jjjjjjjj" + MyApplication.instance!!.getString(NAME))
            dialog.dismiss()
            apiLogout()
        }
        dialog.findViewById<AppCompatButton>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()
        }
    }
}
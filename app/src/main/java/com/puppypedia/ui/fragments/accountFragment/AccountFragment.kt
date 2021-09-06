package com.puppypedia.ui.fragments.accountFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.dialogius.LogoutDialog
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.commomModel.NotificationOnOffModel
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.about_us.AboutUsActivity
import com.puppypedia.ui.main.ui.changepassword.ChangePasswordActivity
import com.puppypedia.ui.main.ui.mypetprofile.MyPetProfileActivity
import com.puppypedia.ui.main.ui.profile.ProfileActivity
import com.puppypedia.utils.helper.AppConstant
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment(), Observer<RestObservable> {
    lateinit var v: View
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    var status = 0

    var isFirst = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_account, container, false)
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
            val dialog = LogoutDialog()
            dialog.show(requireActivity().supportFragmentManager, "logoutDialog")
        }

        sw_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            status = if (isChecked) {
                +
                1

            } else {
                0
            }

        }
    }

    fun api(status: String) {
        viewModel.apiNotiOnOff(requireActivity(), status, true)
        viewModel.mResponse.observe(requireActivity(), this)
    }


    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is NotificationOnOffModel) {
                    val aboutResponse: NotificationOnOffModel = it.data
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

}
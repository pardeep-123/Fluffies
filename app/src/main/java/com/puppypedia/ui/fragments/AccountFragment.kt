package com.puppypedia.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.databinding.FragmentAccountBinding
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


class AccountFragment : Fragment(), Observer<RestObservable> {
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clicksHandle()
    }

    private fun clicksHandle() {

        binding.civProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        binding.rlMyPets.setOnClickListener {
            startActivity(Intent(requireContext(), MyPetProfileActivity::class.java))
        }

        binding.rlChangePassword.setOnClickListener {
            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
        }

        binding.rlAboutUs.setOnClickListener {
            startActivity(Intent(requireContext(), AboutUsActivity::class.java))
        }

        binding.rlTermsCondition.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            intent.putExtra(AppConstant.HEADING,1)
            startActivity(intent)
        }

        binding.rlPrivacyPolicy.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            intent.putExtra(AppConstant.HEADING, 2)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            val dialog = LogoutDialog()
            dialog.show(requireActivity().supportFragmentManager, "logoutDialog")
        }

        /* binding.sc_switch.setOnCheckedChangeListener { buttonView, isChecked ->
             guestMessage = if (isChecked) {+
                 1
             } else {
                 0
             }
             if (isFirst) {
                 val param = HashMap<String, String>()
                 param[GlobalVariables.PARAMS.NotificationSetting.guestMessages] = guestMessage.toString()
                 setNotification(param)
             }
         }*/


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
package com.puppypedia.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puppypedia.R
import com.puppypedia.databinding.FragmentAccountBinding
import com.puppypedia.ui.main.ui.about_us.AboutUsActivity
import com.puppypedia.ui.main.ui.changepassword.ChangePasswordActivity
import com.puppypedia.ui.main.ui.mypetprofile.MyPetProfileActivity
import com.puppypedia.ui.main.ui.profile.ProfileActivity
import com.puppypedia.utils.helper.AppConstant


class AccountFragment : Fragment() {

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
            intent.putExtra(AppConstant.HEADING,2)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {

        }
    }

}
package com.fluffies.dialogius

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.fluffies.databinding.DialogLogoutBinding
import com.fluffies.ui.auth.login.LoginActivity


class LogoutDialog : DialogFragment() {

    private lateinit var binding: DialogLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DialogLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnYes.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finishAffinity()
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }
}
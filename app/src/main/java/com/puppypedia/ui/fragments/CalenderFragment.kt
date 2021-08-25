package com.puppypedia.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puppypedia.R
import com.puppypedia.databinding.FragmentCalenderBinding
import com.puppypedia.databinding.FragmentStatisticsBinding
import com.puppypedia.ui.main.ui.addremainder.AddRemainderActivity


class CalenderFragment : Fragment() {

    private lateinit var binding: FragmentCalenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clicksHandle()
    }

    private fun clicksHandle() {
        binding.ivAdd.setOnClickListener {
            startActivity(Intent(requireContext(), AddRemainderActivity::class.java))
        }
    }
}
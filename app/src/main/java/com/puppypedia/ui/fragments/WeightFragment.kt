package com.puppypedia.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puppypedia.R
import com.puppypedia.common_adapters.WeightAdapter
import com.puppypedia.databinding.FragmentWeightBinding


class WeightFragment : Fragment() {

    private lateinit var binding: FragmentWeightBinding

    private lateinit var weightAdapter: WeightAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weightAdapter = WeightAdapter()
        binding.rvWeight.adapter = weightAdapter

        binding.btnAddWeight.setOnClickListener {

        }
    }
}
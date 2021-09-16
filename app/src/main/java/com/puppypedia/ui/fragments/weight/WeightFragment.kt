package com.puppypedia.ui.fragments.weight

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
import com.puppypedia.common_adapters.WeightAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.add_weight.AddWeightActivity
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.fragment_weight.*


class WeightFragment : Fragment(), Observer<RestObservable> {
    lateinit var v: View
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass
    private lateinit var weightAdapter: WeightAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_weight, container, false)
        //mValidationClass = ValidationsClass.getInstance()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weightAdapter = WeightAdapter()
        rvWeight.adapter = weightAdapter

        btnAddWeight.setOnClickListener {
            startActivity(Intent(requireContext(), AddWeightActivity::class.java))
        }
    }

/*    fun api(){
        viewModel.getWeightApi(this, true,map)
        viewModel.mResponse.observe(this, this)
    }*/

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is GetWeightResponse) {
                    val aboutResponse: GetWeightResponse = it.data

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
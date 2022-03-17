package com.fluffies.ui.fragments.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.fluffies.R
import com.fluffies.click
import com.fluffies.openImagePopUp
import com.fluffies.restApi.RestObservable
import com.fluffies.ui.main.ui.AllViewModel
import com.fluffies.ui.main.ui.category_detail.BackgroundModel
import com.fluffies.utils.helper.others.Helper
import kotlinx.android.synthetic.main.fragment_statistics.*

class StatisticsFragment : Fragment(), Observer<RestObservable> {

    var image = ""
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_statistics, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backgroundApi()

        // open image
        backgroundImage.click {
            openImagePopUp(image,requireContext())
        }
    }
     private fun backgroundApi() {
    viewModel.getBackground(requireActivity(), true)
    viewModel.mResponse.observe(viewLifecycleOwner, this)
    }

    //
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is BackgroundModel) {
              image = it.data.body.backgroundImage
                    Glide.with(requireContext()).load(it.data.body.backgroundImage)
                        .placeholder(R.drawable.place_holder).into(backgroundImage)
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
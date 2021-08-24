package com.puppypedia.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.puppypedia.R
import com.puppypedia.common_adapters.HomeAdapter
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.databinding.FragmentHomeBinding
import com.puppypedia.model.HomeImageModel
import com.puppypedia.model.ServicesModel
import com.puppypedia.ui.main.ui.category_detail.CategoryDetailActivity
import com.puppypedia.ui.main.ui.notification.NotificationActivity
import com.puppypedia.utils.helper.AppConstant
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var serviceAdapter: ServicesAdapter

    var datalist: ArrayList<HomeImageModel> = ArrayList<HomeImageModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setService()
        val indicator = view.findViewById<ScrollingPagerIndicator>(R.id.indicator)
        datalist.add(HomeImageModel(R.drawable.dogsimg))
        datalist.add(HomeImageModel(R.drawable.dogsimg))
        datalist.add(HomeImageModel(R.drawable.dogsimg))

        val ad = HomeAdapter(datalist)
        binding.rcDetailsImg.adapter = ad
        indicator.attachToRecyclerView(binding.rcDetailsImg)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rcDetailsImg)


        binding.rlNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }
    }

    fun setService() {
        val arrayList = ArrayList<ServicesModel>()
        arrayList.add(ServicesModel(R.drawable.feedingactive, "Feeding", true))
        arrayList.add(ServicesModel(R.drawable.weight, "Weight Chart", false))
        arrayList.add(ServicesModel(R.drawable.dogtrainingunactive, "Training", false))
        arrayList.add(ServicesModel(R.drawable.groomingunactive, "Medical/Health", false))
        arrayList.add(ServicesModel(R.drawable.dogtrainingunactive, "Essential", false))
        arrayList.add(ServicesModel(R.drawable.dogunactive, "Grooming/Cosmetics", false))

        serviceAdapter = ServicesAdapter(arrayList)
        binding.rcServices.adapter = serviceAdapter

        serviceAdapter.onItemClick = { servicesModel ->
            if (servicesModel.service != "Weight Chart") {
                val intent = Intent(requireContext(), CategoryDetailActivity::class.java)
                intent.putExtra(AppConstant.HEADING, servicesModel.service)
                startActivity(intent)
            }
        }
    }


}
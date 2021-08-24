package com.puppypedia.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.puppypedia.R
import com.puppypedia.common_adapters.HomeAdapter
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.model.HomeImageModel
import com.puppypedia.model.ServicesModel
import com.puppypedia.ui.main.ui.notification.NotificationActivity
import kotlinx.android.synthetic.main.fragment_home.*
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator


class HomeFragment : Fragment() {

    var datalist: ArrayList<HomeImageModel> = ArrayList<HomeImageModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setService()
        val indicator = view.findViewById<ScrollingPagerIndicator>(R.id.indicator)
        datalist.add(HomeImageModel(R.drawable.dogsimg))
        datalist.add(HomeImageModel(R.drawable.dogsimg))
        datalist.add(HomeImageModel(R.drawable.dogsimg))

        val ad = HomeAdapter(datalist)
        rc_details_img.adapter = ad
        indicator.attachToRecyclerView(rc_details_img)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rc_details_img)


        rl_notification.setOnClickListener {
            startActivity(Intent(requireContext(),NotificationActivity::class.java))
        }
    }

    fun setService() {

        val arrayList = ArrayList<ServicesModel>()
        arrayList.add(ServicesModel(R.drawable.feedingactive, "Feeding"))
        arrayList.add(ServicesModel(R.drawable.feedingactive, "Feeding"))
        arrayList.add(ServicesModel(R.drawable.feedingactive, "Feeding"))
        arrayList.add(ServicesModel(R.drawable.feedingactive, "Feeding"))
        arrayList.add(ServicesModel(R.drawable.feedingactive, "Feeding"))
        arrayList.add(ServicesModel(R.drawable.feedingactive, "Feeding"))
        val servicesAdapter = ServicesAdapter(arrayList)
        rc_services.adapter = servicesAdapter

    }


}
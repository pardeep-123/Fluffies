package com.puppypedia.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.puppypedia.R
import com.puppypedia.common_adapters.DogsAdapter
import com.puppypedia.common_adapters.HomeAdapter
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.databinding.FragmentHomeBinding
import com.puppypedia.model.DogsModel
import com.puppypedia.model.HomeImageModel
import com.puppypedia.model.ServicesModel
import com.puppypedia.ui.main.ui.category_detail.CategoryDetailActivity
import com.puppypedia.ui.main.ui.notification.NotificationActivity
import com.puppypedia.ui.main.ui.weight_chart.WeightChartActivity
import com.puppypedia.utils.helper.AppConstant
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var serviceAdapter: ServicesAdapter

    var datalist: ArrayList<HomeImageModel> = ArrayList<HomeImageModel>()

    private var myPopupWindow: PopupWindow? = null


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
        setPopUpWindow()
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

        binding.rlChoosePet.setOnClickListener {
            myPopupWindow?.showAsDropDown(it, 40, 40)
        }
    }

    fun setService() {
        val arrayList = ArrayList<ServicesModel>()
        arrayList.add(ServicesModel(R.drawable.icon1, "Feeding", true))
        arrayList.add(ServicesModel(R.drawable.icon3, "Weight Chart", false))
        arrayList.add(ServicesModel(R.drawable.icon5, "Training", false))
        arrayList.add(ServicesModel(R.drawable.icon7, "Medical/Health", false))
        arrayList.add(ServicesModel(R.drawable.icon9, "Essential", false))
        arrayList.add(ServicesModel(R.drawable.icon11, "Grooming/Cosmetics", false))

        serviceAdapter = ServicesAdapter(arrayList)
        binding.rcServices.adapter = serviceAdapter

        serviceAdapter.onItemClick = { servicesModel ->
            if (servicesModel.service != "Weight Chart") {
                val intent = Intent(requireContext(), CategoryDetailActivity::class.java)
                intent.putExtra(AppConstant.HEADING, servicesModel.service)
                startActivity(intent)
            } else {
                startActivity(Intent(requireContext(), WeightChartActivity::class.java))
            }
        }
    }

    private fun setPopUpWindow() {
        val inflater =
            activity?.applicationContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup, null)

        myPopupWindow = PopupWindow(
            view,
            800,
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        val rvDogs = view.findViewById<RecyclerView>(R.id.rvDogs)


        val arrayList = ArrayList<DogsModel>()
        arrayList.add(DogsModel(R.drawable.dog_profile, "Rony", true))
        arrayList.add(DogsModel(R.drawable.dog_img, "Rocky", false))

        val dogsAdapter = DogsAdapter(arrayList)
        rvDogs.adapter = dogsAdapter


        dogsAdapter.onItemSelected = {dogModel ->
            myPopupWindow?.dismiss()
        }


    }
}
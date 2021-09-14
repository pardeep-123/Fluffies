package com.puppypedia.ui.fragments.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.DogsAdapter
import com.puppypedia.common_adapters.HomeAdapter
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.model.DogsModel
import com.puppypedia.model.HomeImageModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category.CategoryActivity
import com.puppypedia.ui.main.ui.notification.NotificationActivity
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.fragment_home.*
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator


class HomeFragment : Fragment(), Observer<RestObservable> {
    lateinit var v: View
    var selectedpos = ""
    var aboutResponse: HomeFragmentResponse? = null
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    lateinit var serviceAdapter: ServicesAdapter
    var datalist: ArrayList<HomeImageModel> = ArrayList<HomeImageModel>()
    private var myPopupWindow: PopupWindow? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false)
        apihome()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  setService()
        setPopUpWindow()
/*        val indicator = view.findViewById<ScrollingPagerIndicator>(R.id.indicator)
        datalist.add(HomeImageModel(R.drawable.dogsimg))
        datalist.add(HomeImageModel(R.drawable.dogsimg))
        datalist.add(HomeImageModel(R.drawable.dogsimg))

        val ad = HomeAdapter(datalist)
        rc_details_img.adapter = ad
        indicator.attachToRecyclerView(rc_details_img)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rc_details_img)*/
        clicksHandle()
    }

    private fun clicksHandle() {
        rl_notification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }
        rlChoosePet.setOnClickListener {
            myPopupWindow?.showAsDropDown(it, 40, 40)
        }
        tvViewAll.setOnClickListener {
            val i = Intent(context, CategoryActivity::class.java)
            i.putExtra("aboutResponse", aboutResponse)
            i.putExtra("selectedpos", selectedpos)
            startActivity(i)


            // startActivity(Intent(requireContext(), CategoryActivity::class.java))
        }
    }

    /*    private fun setService() {
            val arrayList = ArrayList<ServicesModel>()
            arrayList.add(ServicesModel(R.drawable.icon1, "Feeding", true))
            arrayList.add(ServicesModel(R.drawable.icon3, "Weight Chart", false))
            arrayList.add(ServicesModel(R.drawable.icon5, "Training", false))
            arrayList.add(ServicesModel(R.drawable.icon7, "Medical/Health", false))
            arrayList.add(ServicesModel(R.drawable.icon9, "Essential", false))
            arrayList.add(ServicesModel(R.drawable.icon11, "Grooming/Cosmetics", false))
            serviceAdapter = ServicesAdapter(arrayList)
            rc_services.adapter = serviceAdapter
            serviceAdapter.onItemClick = { servicesModel ->
                if (servicesModel.service != "Weight Chart") {
                    val intent = Intent(requireContext(), CategoryDetailActivity::class.java)
                    intent.putExtra(AppConstant.HEADING, servicesModel.service)
                    startActivity(intent)
                } else {
                    startActivity(Intent(requireContext(), WeightChartActivity::class.java))
                }
            }
        }*/
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
        arrayList.add(DogsModel(R.drawable.dogsimg, "Rony", true))
        arrayList.add(DogsModel(R.drawable.dog_img, "Rocky", false))
        val dogsAdapter = DogsAdapter(arrayList)
        rvDogs.adapter = dogsAdapter
        dogsAdapter.onItemSelected = { dogModel ->
            myPopupWindow?.dismiss()
        }
    }

    fun apihome() {
        viewModel.getHomeDetails(requireActivity(), true)
        viewModel.mResponse.observe(requireActivity(), this)
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is HomeFragmentResponse) {
                    val aboutResponse: HomeFragmentResponse = it.data
                    serviceAdapter = ServicesAdapter(this, aboutResponse)
                    rc_services.adapter = serviceAdapter
                    val indicator = view?.findViewById<ScrollingPagerIndicator>(R.id.indicator)
                    val homeAdapter = HomeAdapter(this, aboutResponse)
                    rc_details_img.adapter = homeAdapter
                    indicator?.attachToRecyclerView(rc_details_img)
                    val snapHelper: SnapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(rc_details_img)

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
    /*fun petDetails(position: Int) {
        selectedpos=position.toString()
        Glide.with(this).load(Constants.BASE_URL + aboutResponse!!.body.category[position].image)
            .placeholder(R.drawable.profile_pic).into(ivImg)
        tvName.setText(aboutResponse!!.body.category[position].name)

    }*/


}
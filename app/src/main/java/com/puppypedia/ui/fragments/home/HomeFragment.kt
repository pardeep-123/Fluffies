package com.puppypedia.ui.fragments.home

import android.annotation.SuppressLint
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
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.ClickCallBack
import com.puppypedia.common_adapters.DogsAdapter
import com.puppypedia.common_adapters.HomeAdapter
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category.CategoryActivity
import com.puppypedia.ui.main.ui.category_detail.CategoryDetailActivity
import com.puppypedia.ui.main.ui.notification.NotificationActivity
import com.puppypedia.ui.main.ui.petdetail.YourPetDetailActivity
import com.puppypedia.ui.main.ui.weight_chart.WeightChartActivity
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_home.*
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator

class HomeFragment : Fragment(), Observer<RestObservable>, ClickCallBack {

    lateinit var v: View
    var aboutResponse: HomeFragmentResponse? = null
    var arrayList = ArrayList<HomeFragmentResponse.Body.Pet>()
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private var myPopupWindow: PopupWindow? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        apihome()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicksHandle()
        //  etAddress.setText(addresses[0].locality)
        tv_choose_dog.setText("")
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
            startActivity(i)
            // startActivity(Intent(requireContext(), CategoryActivity::class.java))
        }
    }

    @SuppressLint("InflateParams")
    private fun setPopUpWindow() {
        val inflater =
            activity?.applicationContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popup, null)
        myPopupWindow = PopupWindow(
            view, 800, RelativeLayout.LayoutParams.WRAP_CONTENT, true
        )
        val rvDogs = view.findViewById<RecyclerView>(R.id.rvDogs)
        rvDogs.adapter = DogsAdapter(requireContext(), arrayList, this)
    }

    fun apihome() {
        viewModel.getHomeDetails(requireActivity(), true)
        viewModel.mResponse.observe(requireActivity(), this)
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is HomeFragmentResponse) {
                    arrayList.addAll(it.data.body.pets as ArrayList<HomeFragmentResponse.Body.Pet>)
                    if (arrayList.size == 0) {
                        whitebackground.visibility = View.VISIBLE
                        // SharedPrefUtil.getInstance().clear()
                        startActivity(Intent(activity, YourPetDetailActivity::class.java))
                        activity?.finishAffinity()
                    } else {
                        whitebackground.visibility = View.GONE
                    }
                    aboutResponse = it.data
                    if (aboutResponse!!.body.notificationsCount == 0) {
                        tvCount.visibility = View.GONE
                    } else {
                        tvCount.setText(aboutResponse!!.body.notificationsCount.toString())
                    }
                    rc_services.adapter = ServicesAdapter(requireContext(), aboutResponse!!, this)



                    setPopUpWindow()
/////////////////////////////////////// Banneer Adapter with Indigator
                    val indicator = view?.findViewById<ScrollingPagerIndicator>(R.id.indicator)
                    val homeAdapter = HomeAdapter(this, aboutResponse!!)
                    rc_details_img.adapter = homeAdapter
                    indicator?.attachToRecyclerView(rc_details_img)
                    val snapHelper: SnapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(rc_details_img)
                    petDetails(SharedPrefUtil.getInstance().petPos)
                    SharedPrefUtil.getInstance()
                        .savePetId(arrayList[SharedPrefUtil.getInstance().petPos].id.toString())

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

    override fun onItemClick(pos: Int, value: String) {
        when (value) {
            "cat" -> {
                if (aboutResponse!!.body.category[pos].name == "Weight Chart") {
                    startActivity(Intent(requireContext(), WeightChartActivity::class.java))
                } else {
                    val intent =
                        Intent(requireContext(), CategoryDetailActivity::class.java)
                            .putExtra("data", aboutResponse!!.body.category[pos])
                    startActivity(intent)
                }
            }
            "pet" -> {
                SharedPrefUtil.getInstance().savePetId(arrayList[pos].id.toString())
                SharedPrefUtil.getInstance().savePetPos(pos)
                petDetails(pos)
                myPopupWindow?.dismiss()
            }
        }
    }

    fun petDetails(position: Int) {

        if (aboutResponse!!.body.pets[position].name.length > 12) {
            tv_choose_dog.text = (aboutResponse!!.body.pets[position].name.replaceRange(
                12,
                aboutResponse!!.body.pets[position].name.length,
                "..."
            ))
        } else {
            tv_choose_dog.text = aboutResponse!!.body.pets[position].name
        }



        Glide.with(requireContext())
            .load(Constants.IMAGE_URL + aboutResponse!!.body.pets[position].image)
            .placeholder(R.drawable.place_holder).into(ivDogImg)

    }
}



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
import com.puppypedia.common_adapters.*
import com.puppypedia.model.PetNameModel
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

class HomeFragment : Fragment(), Observer<RestObservable>, ClickCallBackNew, ClickCallBack {

    lateinit var v: View
    var aboutResponse: HomeFragmentResponse? = null
    var dogArrayList = ArrayList<HomeFragmentResponse.Body.Pet>()
    var catArrayList = ArrayList<HomeFragmentResponse.Body.Pet>()
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private var myPopupWindow: PopupWindow? = null

    private var petNameList : ArrayList<PetNameModel> = ArrayList()

    private var adapter : PetNameAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        apihome()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicksHandle()
        //  etAddress.setText(addresses[0].locality)
        tv_choose_dog.text = ""

        petNameList.add(PetNameModel("Dog",R.drawable.dog))
        petNameList.add(PetNameModel("Cat",R.drawable.cat))
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
       // rvDogs.adapter = DogsAdapter(requireContext(), arrayList, this)
        adapter = PetNameAdapter(petNameList,dogArrayList,catArrayList,this)
        rvDogs.adapter = adapter

        // send intent from add pet button
        adapter?.onClickListener ={
            val intent = Intent(requireContext(), YourPetDetailActivity::class.java)
            startActivity(intent)
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
                    val dogList = it.data.body.pets.filter { it.type==1 }
                    dogArrayList.addAll(dogList as ArrayList<HomeFragmentResponse.Body.Pet>)
                    /*  if (arrayList.size == 0) {
                          whitebackground.visibility = View.VISIBLE
                          SharedPrefUtil.getInstance().clear()
                         // startActivity(Intent(this, LoginActivity::class.java))
                          startActivity(Intent(activity, LoginActivity::class.java))
                          activity?.finishAffinity()
                      } else {
                          whitebackground.visibility = View.GONE
                      }*/
                    val catList = it.data.body.pets.filter { it.type==2 }
                    catArrayList.addAll(catList as ArrayList<HomeFragmentResponse.Body.Pet>)

                    aboutResponse = it.data
                    if (aboutResponse!!.body.notificationsCount == 0) {
                        tvCount.visibility = View.GONE
                    } else {
                        tvCount.text = aboutResponse!!.body.notificationsCount.toString()
                    }
                    rc_services.adapter = ServicesAdapter(
                        requireContext(),
                        aboutResponse!!,
                        this,
                        1
                    )
                    setPopUpWindow()
/////////////////////////////////////// Banner Adapter with Indicator
                    val indicator = view?.findViewById<ScrollingPagerIndicator>(R.id.indicator)
                    val homeAdapter = HomeAdapter(this, aboutResponse!!)
                    rc_details_img.adapter = homeAdapter
                    indicator?.attachToRecyclerView(rc_details_img)
                    val snapHelper: SnapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(rc_details_img)
                    petDetails(0,SharedPrefUtil.getInstance().petPos)
                    SharedPrefUtil.getInstance().savePetId(dogArrayList[SharedPrefUtil.getInstance().petPos].id.toString())
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

    override fun onItemClick(selectedPos:Int,pos: Int, value: String) {
        when (value) {
            "cat" -> {
                // name change by pardeep from weight chart to weight log.
                if (aboutResponse!!.body.category[pos].name == "Weight Log" ||
                    aboutResponse!!.body.category[pos].name == "Weight Chart") {
                    startActivity(Intent(requireContext(), WeightChartActivity::class.java))
                } else {
                    val intent =
                        Intent(requireContext(), CategoryDetailActivity::class.java)
                            .putExtra("data", aboutResponse!!.body.category[pos])
                    startActivity(intent)
                }
            }
            "pet" -> {
                if (selectedPos ==0)
                SharedPrefUtil.getInstance().savePetId(dogArrayList[pos].id.toString())
                else
                    SharedPrefUtil.getInstance().savePetId(catArrayList[pos].id.toString())
                SharedPrefUtil.getInstance().savePetPos(pos)
                petDetails(selectedPos,pos)
                myPopupWindow?.dismiss()
            }
        }
    }

    private fun petDetails(selectedPos: Int,position: Int) {
      // selected position is 0 for dog and 1 for cat
        if (selectedPos == 0) {
            if (dogArrayList[position].name.length > 12) {
                tv_choose_dog.text = (dogArrayList[position].name.replaceRange(
                    12,
                    dogArrayList[position].name.length,
                    "..."
                ))
            } else {
                tv_choose_dog.text = dogArrayList[position].name
            }
            Glide.with(requireContext())
                .load(Constants.IMAGE_URL + dogArrayList[position].image)
                .placeholder(R.drawable.place_holder).into(ivDogImg)
        }else{
            if (catArrayList[position].name.length > 12) {
                tv_choose_dog.text = (catArrayList[position].name.replaceRange(
                    12,
                    catArrayList[position].name.length,
                    "..."
                ))
            } else {
                tv_choose_dog.text = catArrayList[position].name
            }
            Glide.with(requireContext())
                .load(Constants.IMAGE_URL + catArrayList[position].image)
                .placeholder(R.drawable.place_holder).into(ivDogImg)
        }
    }

    override fun onItemClick(pos: Int, value: String) {
        when (value) {
            "cat" -> {
                // name change by pardeep from weight chart to weight log.
                if (aboutResponse!!.body.category[pos].name == "Weight Log" ||
                    aboutResponse!!.body.category[pos].name == "Weight Chart"
                ) {
                    startActivity(Intent(requireContext(), WeightChartActivity::class.java))
                } else {
                    val intent =
                        Intent(requireContext(), CategoryDetailActivity::class.java)
                            .putExtra("data", aboutResponse!!.body.category[pos])
                    startActivity(intent)
                }
            }
        }
    }
}



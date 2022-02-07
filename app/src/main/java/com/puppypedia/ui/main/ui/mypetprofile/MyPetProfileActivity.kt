package com.puppypedia.ui.main.ui.mypetprofile

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.StatusAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.fragments.profile.LifeEventFragment
import com.puppypedia.ui.fragments.profile.MyPetProfileFragment
import com.puppypedia.ui.fragments.profile.PictureFragment
import com.puppypedia.ui.fragments.statistics.StatisticsFragment
import com.puppypedia.ui.fragments.weight.WeightFragment
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.editpetprofile.EditPetProfileActivity
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Constants.Companion.gender
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_edit_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.tb
import kotlinx.android.synthetic.main.activity_my_pet_profile.view.*
import kotlinx.android.synthetic.main.activity_weight_chart.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*


class MyPetProfileActivity : AppCompatActivity(), Observer<RestObservable> {

    lateinit var adapter: StatusAdapter
    var selectedpos = ""
    var aboutResponse: PetProfileResponse? = null
    lateinit var context: Context
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pet_profile)

        context = this
        openFragment(MyPetProfileFragment())
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.my_pet_profile)

        btnProfile.setOnClickListener {
            profileBtnClick()
        }
        btnPicture.setOnClickListener {
            pictureBtnClick()
        }

        btnLifeEvent.setOnClickListener {
            lifeEventBtnClick()
        }
    }

    override fun onResume() {
        super.onResume()
        apiPetProfile()
    }

    fun apiPetProfile() {
        viewModel.getPetProfile(this, true)
        viewModel.mResponse.observe(this, this)
    }
    override fun onChanged(liveData: RestObservable?) {
        when {
            liveData!!.status == Status.SUCCESS -> {
                if (liveData.data is PetProfileResponse) {
                    aboutResponse = liveData.data
                    adapter = StatusAdapter(this, aboutResponse!!, this@MyPetProfileActivity)
                    rv_status.adapter = adapter
                  //  petDetails(0)
                }
            }
            liveData.status == Status.ERROR -> {
                if (liveData.data != null) {
                    Helper.showErrorAlert(this, liveData.data as String)
                } else {
                    Helper.showErrorAlert(this, liveData.error.toString())
                }
            }
        }
    }
    private fun openFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rlWeightContainer, fragment)
        transaction.commit()
    }

    private fun currentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.rlWeightContainer)
    }

    private fun profileBtnClick() {
        btnProfile.background = ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        btnStatistics.setBackgroundColor(Color.TRANSPARENT)
        btnProfile.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnStatistics.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is MyPetProfileFragment) {
            openFragment(MyPetProfileFragment())
        }
    }

    private fun pictureBtnClick() {
        btnStatistics.background =
            ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        btnProfile.setBackgroundColor(Color.TRANSPARENT)
//        btnBodyConditionChart.setBackgroundColor(Color.TRANSPARENT)
        btnStatistics.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnProfile.setTextColor(ContextCompat.getColor(this, R.color.black))
//        btnBodyConditionChart.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is PictureFragment) {
            openFragment(PictureFragment())
        }
    }

    private fun lifeEventBtnClick() {
        btnStatistics.background =
            ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        btnWeight.setBackgroundColor(Color.TRANSPARENT)
//        btnBodyConditionChart.setBackgroundColor(Color.TRANSPARENT)
        btnStatistics.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnWeight.setTextColor(ContextCompat.getColor(this, R.color.black))
//        btnBodyConditionChart.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is LifeEventFragment) {
            openFragment(LifeEventFragment())
        }
    }

//    fun petDetails(position: Int) {
//        selectedpos = position.toString()
//        Glide.with(context).load(Constants.IMAGE_URL + aboutResponse!!.body[position].image)
//            .placeholder(R.drawable.place_holder).into(ivImg)
//        tvName.setText(aboutResponse!!.body[position].name)
//        tvGender.setText(gender(aboutResponse!!.body[position].gender))
//        tvWeight.setText(aboutResponse!!.body[position].weight.toString())
//        tvAge.setText(aboutResponse!!.body[position].age.toString())
//        tvBreed.setText(aboutResponse!!.body[position].breed)
//        tvAbout.setText(aboutResponse!!.body[position].about)
//
//
//    }
}
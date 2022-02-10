package com.puppypedia.ui.main.ui.mypetprofile

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.StatusAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.fragments.profile.LifeEventFragment
import com.puppypedia.ui.fragments.profile.MyPetProfileFragment
import com.puppypedia.ui.fragments.profile.PictureFragment
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.petdetail.YourPetDetailActivity
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_edit_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.tb
import kotlinx.android.synthetic.main.activity_my_pet_profile.view.*
import kotlinx.android.synthetic.main.activity_weight_chart.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class MyPetProfileActivity : AppCompatActivity(), Observer<RestObservable>,
    StatusAdapter.OnProfileClick {

    lateinit var adapter: StatusAdapter
    var aboutResponse: PetProfileResponse? = null
    lateinit var context: Context
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    var petId = ""
    var myposition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pet_profile)
        apiPetProfile()
        context = this

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
                    petId = aboutResponse?.body!![0].id.toString()
                    openFragment(MyPetProfileFragment(petId, myposition))

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
        btnProfile.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnPicture.setBackgroundColor(Color.TRANSPARENT)
        btnPicture.setTextColor(ContextCompat.getColor(this, R.color.black))
        btnLifeEvent.setBackgroundColor(Color.TRANSPARENT)
        btnLifeEvent.setTextColor(ContextCompat.getColor(this, R.color.black))
        if (currentFragment() !is MyPetProfileFragment) {
            openFragment(MyPetProfileFragment(petId, myposition))
        }
    }

    private fun pictureBtnClick() {
        btnPicture.background = ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        btnPicture.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnProfile.setBackgroundColor(Color.TRANSPARENT)
        btnProfile.setTextColor(ContextCompat.getColor(this, R.color.black))
        btnLifeEvent.setBackgroundColor(Color.TRANSPARENT)
        btnLifeEvent.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is PictureFragment) {
            openFragment(PictureFragment(petId))
        }
    }

    private fun lifeEventBtnClick() {
        btnLifeEvent.background = ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        btnLifeEvent.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnProfile.setBackgroundColor(Color.TRANSPARENT)
        btnProfile.setTextColor(ContextCompat.getColor(this, R.color.black))
        btnPicture.setBackgroundColor(Color.TRANSPARENT)

        btnPicture.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is LifeEventFragment) {
            openFragment(LifeEventFragment(petId))
        }
    }

    override fun onimageClick(myPetId: String, position: Int) {
        petId = myPetId
        myposition = position
        when {
            currentFragment() is MyPetProfileFragment -> {
                openFragment(MyPetProfileFragment(petId, position))
            }
            currentFragment() is LifeEventFragment -> {
                openFragment(LifeEventFragment(petId))
            }
            currentFragment() is PictureFragment -> {
                openFragment(PictureFragment(petId))
            }
        }
    }

    override fun onitemClick(name: String, value: String) {
        val intent = Intent(this, YourPetDetailActivity::class.java).putExtra(name, value)
        launcher.launch(intent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                apiPetProfile()
                btnProfile.background = ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
                btnProfile.setTextColor(ContextCompat.getColor(this, R.color.white))
                btnPicture.setBackgroundColor(Color.TRANSPARENT)
                btnPicture.setTextColor(ContextCompat.getColor(this, R.color.black))
                btnLifeEvent.setBackgroundColor(Color.TRANSPARENT)
                btnLifeEvent.setTextColor(ContextCompat.getColor(this, R.color.black))
            }

        }

}
package com.puppypedia.ui.main.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.editprofile.EditProfileActivity
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_about_us.tb
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class ProfileActivity : AppCompatActivity(), Observer<RestObservable> {
    var name = ""
    var email = ""
    var image = ""
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        context = this
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.profile)
        btnEditProfile.setOnClickListener {
            val i = Intent(context, EditProfileActivity::class.java)
            i.putExtra("email", email)
            i.putExtra("name", name)
            i.putExtra("image", Constants.USER_IMAGE_URL + image)
            startActivity(i)
        }
    }
    override fun onResume() {
        super.onResume()
        apiProfile()
    }
    fun apiProfile() {
        viewModel.getProfile(this, true)
        viewModel.mResponse.observe(this, this)
    }
    override fun onChanged(liveData: RestObservable?) {
        when {
            liveData!!.status == Status.SUCCESS -> {
                if (liveData.data is ProfileResponse) {
                    val aboutResponse: ProfileResponse = liveData.data
                    SharedPrefUtil.getInstance().saveAuthToken(aboutResponse.body.authKey)
                    tvEmail.setText(liveData.data.body.email)
                    tvName.setText(liveData.data.body.name)
                    Glide.with(context).load(liveData.data.body.image)
                        .placeholder(R.drawable.profile_pic).into(ivProfileImg)
                    email = liveData.data.body.email
                    name = liveData.data.body.name
                    image = liveData.data.body.image
                    //   startActivity(Intent(this, EditProfileActivity::class.java))
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
}
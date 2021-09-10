package com.puppypedia.ui.main.ui.mypetprofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.StatusAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.editpetprofile.EditPetProfileActivity
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_my_pet_profile.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class MyPetProfileActivity : AppCompatActivity(), Observer<RestObservable> {
    lateinit var adapter: StatusAdapter
    var name = ""
    var gender = ""
    var weight = ""
    var age = ""
    var breed = ""
    var about = ""
    var image = ""
    lateinit var context: Context
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pet_profile)
        clickHandle()
        context = this
        val arrayList = ArrayList<Int>()
        arrayList.add(R.drawable.petprofile)
        arrayList.add(R.drawable.petprofile_one)
        arrayList.add(R.drawable.plusbg)
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.my_pet_profile)

        adapter = StatusAdapter(this, arrayList)
        rv_status.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        apiPetProfile()
    }

    fun clickHandle() {
        BtnEdit.setOnClickListener {
            val i = Intent(context, EditPetProfileActivity::class.java)
            i.putExtra("gender", gender)
            i.putExtra("name", name)
            i.putExtra("weight", weight)
            i.putExtra("age", age)
            i.putExtra("breed", breed)
            i.putExtra("about", about)
            i.putExtra("image", Constants.USER_IMAGE_URL + image)
            startActivity(i)


            //   startActivity(Intent(this,EditPetProfileActivity::class.java))
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
                    val aboutResponse: PetProfileResponse = liveData.data

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
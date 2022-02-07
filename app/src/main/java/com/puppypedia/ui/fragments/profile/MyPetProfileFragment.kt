package com.puppypedia.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.StatusAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.editpetprofile.EditPetProfileActivity
import com.puppypedia.ui.main.ui.mypetprofile.PetProfileResponse
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_my_pet_profile.*
import kotlinx.android.synthetic.main.fragment_my_pet_profile.*


class MyPetProfileFragment : Fragment(), Observer<RestObservable> {
    var selectedpos = ""
    var aboutResponse: PetProfileResponse? = null

    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_pet_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickHandle()
    }

    fun apiPetProfile() {
        viewModel.getPetProfile(requireActivity(), true)
        viewModel.mResponse.observe(this, this)
    }
    override fun onChanged(liveData: RestObservable?) {
        when {
            liveData!!.status == Status.SUCCESS -> {
                if (liveData.data is PetProfileResponse) {
                    aboutResponse = liveData.data

                    petDetails(0)
                }
            }
            liveData.status == Status.ERROR -> {
                if (liveData.data != null) {
                    Helper.showErrorAlert(requireActivity(), liveData.data as String)
                } else {
                    Helper.showErrorAlert(requireActivity(), liveData.error.toString())
                }
            }
        }
    }

    fun clickHandle() {
        BtnEdit.setOnClickListener {
            val i = Intent(context, EditPetProfileActivity::class.java)
            i.putExtra("aboutResponse", aboutResponse)
            i.putExtra("selectedpos", selectedpos)
            startActivity(i)
            //   startActivity(Intent(this,EditPetProfileActivity::class.java))
        }
    }

    fun petDetails(position: Int) {
        selectedpos = position.toString()
        Glide.with(requireContext()).load(Constants.IMAGE_URL + aboutResponse!!.body[position].image)
            .placeholder(R.drawable.place_holder).into(ivImg)
        tvName.setText(aboutResponse!!.body[position].name)
        tvGender.setText(Constants.gender(aboutResponse!!.body[position].gender))
        tvWeight.setText(aboutResponse!!.body[position].weight.toString())
        tvAge.setText(aboutResponse!!.body[position].age.toString())
        tvBreed.setText(aboutResponse!!.body[position].breed)
        tvAbout.setText(aboutResponse!!.body[position].about)


    }
}
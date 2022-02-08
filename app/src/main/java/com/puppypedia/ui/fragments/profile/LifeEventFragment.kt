package com.puppypedia.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.GetLifeAdapter
import com.puppypedia.common_adapters.PictureAdapter
import com.puppypedia.model.GetImageModel
import com.puppypedia.model.GetLifeEventModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category_detail.DeleteResponse
import com.puppypedia.ui.main.ui.mypetprofile.MyPetProfileActivity
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.fragment_life_event.*
import kotlinx.android.synthetic.main.fragment_picture.*
import okhttp3.RequestBody

class LifeEventFragment : Fragment(), Observer<RestObservable>, GetLifeAdapter.OnDeletePic {

    var adapter : GetLifeAdapter?=null
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private var mylist = ArrayList<GetLifeEventModel.Body>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_fab_event.setOnClickListener {
           val intent = Intent(requireContext(),LifeEventDetailActivity::class.java)
            startActivity(intent)
        }
        setAdapter(mylist)
        hitGetPictureApi()
    }

    private fun setAdapter(list: ArrayList<GetLifeEventModel.Body>) {
        adapter = GetLifeAdapter(this,list)
        rvLifeEvent.adapter = adapter
    }

    // get picture list api
    private fun hitGetPictureApi(){
        val map = HashMap<String, String>()
        map["pet_id"] = ((activity as MyPetProfileActivity).petId)
        viewModel.getLifeEvent(requireActivity(), true, map)
        viewModel.mResponse.observe(viewLifecycleOwner, this)
    }

    override fun onChanged(liveData: RestObservable?) {
        when {
            liveData!!.status == Status.SUCCESS -> {
                if (liveData.data is GetLifeEventModel) {
                    if (liveData.data.body.size > 0)
                        mylist.clear()
                    mylist.addAll(liveData.data.body)
                    adapter?.notifyDataSetChanged()
                }
//                else if (liveData.data is DeleteResponse) {
//                    myList.removeAt(delPosition)
//                    adapter?.notifyDataSetChanged()
//                }
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

    override fun onDeletePic(id: String, position: Int) {

    }
}
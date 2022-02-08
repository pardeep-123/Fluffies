package com.puppypedia.ui.main.ui.addhealthproblem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.AddHealthListAdapter
import com.puppypedia.model.GetHealthListModel
import com.puppypedia.myDeleteDialog
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category_detail.DeleteResponse
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.activity_add_health_problem.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.tb
import kotlinx.android.synthetic.main.auth_toolbar.view.*

/*created by naveen singh*/
class AddHealthProblemActivity : AppCompatActivity(), Observer<RestObservable>,
    AddHealthListAdapter.OnDeleteClick {

    private var adapter : AddHealthListAdapter?=null
    private var myPos = -1
    private var mylist: ArrayList<GetHealthListModel.Body> = ArrayList()
   private val viewModel : AllViewModel by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_health_problem)

        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.health_problem)


        tb.tvAdd.setOnClickListener {
            val intent = Intent(this,AddHealthDetails::class.java)
            startActivity(intent)
            tb.tvAdd.visibility = View.GONE
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        tb.tvAdd.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        tb.tvAdd.visibility = View.VISIBLE
        apiGetHealthDetails()
    }

    private fun setAdapter(list: ArrayList<GetHealthListModel.Body>) {
        adapter = AddHealthListAdapter(this,list)
        rvHealthList.adapter = adapter
    }

    private fun apiGetHealthDetails() {
        viewModel.getHealthDetail(this, true)
        viewModel.mResponse.observe(this, this)
    }

    override fun onChanged(it: RestObservable?) {
        when{
            it!!.status == Status.SUCCESS ->{
                if (it.data is GetHealthListModel){
                    mylist.clear()
                    mylist.addAll(it.data.body)
                    if (mylist.size>0) {
                        setAdapter(mylist)
                        no_data.visibility = View.GONE
                    }  else
                        no_data.visibility = View.VISIBLE
                } else if (it.data is DeleteResponse){
                    mylist.removeAt(myPos)
                    adapter?.notifyDataSetChanged()
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(this, it.data as String)
                } else {
                    Helper.showErrorAlert(this, it.error.toString())
                }
            }
        }
    }

    fun apideleteHealthDetail(healthId: String, petId: String) {
        viewModel.apideleteHealthDetail(this, petId, healthId, true)
        viewModel.mResponse.observe(this, this)
    }

    override fun onDelete(healthId: String, petId: String,position :Int) {
        myPos = position
        myDeleteDialog(this,{apideleteHealthDetail(healthId,petId)},"Are you sure to delete?")
    }

}
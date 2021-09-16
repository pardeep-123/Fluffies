package com.puppypedia.ui.main.ui.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puppypedia.R
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class CategoryActivity : AppCompatActivity() {
    var data: HomeFragmentResponse? = null
    var poz = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        tb.tv_title.text = getString(R.string.category)
        clicksHandle()
        data = (intent.getSerializableExtra("aboutResponse") as HomeFragmentResponse)
        //poz= intent.getStringExtra("selectedpos")!!.toInt()

        var serviceAdapter = ServicesAdapter(this, data!!)
        rvCategory.adapter = serviceAdapter
        // setCategoryAdapter()
    }
    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
    }
/*    private fun setCategoryAdapter() {
        val arrayList = ArrayList<ServicesModel>()
        arrayList.add(ServicesModel(R.drawable.icon1, "Feeding", true))
        arrayList.add(ServicesModel(R.drawable.icon3, "Weight Chart", false))
        arrayList.add(ServicesModel(R.drawable.icon5, "Training", false))
        arrayList.add(ServicesModel(R.drawable.icon7, "Medical/Health", false))
        arrayList.add(ServicesModel(R.drawable.icon7, "Toys and Chews", false))
        arrayList.add(ServicesModel(R.drawable.icon9, "Essential", false))
        arrayList.add(ServicesModel(R.drawable.icon11, "Grooming/Cosmetics", false))
        val serviceAdapter = ServicesAdapter(this)
      rvCategory.adapter = serviceAdapter
        serviceAdapter.onItemClick = { servicesModel ->
            if (servicesModel.service != "Weight Chart") {
                val intent = Intent(this, CategoryDetailActivity::class.java)
                intent.putExtra(AppConstant.HEADING, servicesModel.service)
                startActivity(intent)
            } else {
                startActivity(Intent(this, WeightChartActivity::class.java))
            }
        }
    }*/
}
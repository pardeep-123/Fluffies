package com.puppypedia.ui.main.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.databinding.ActivityCategoryBinding
import com.puppypedia.model.ServicesModel
import com.puppypedia.ui.main.ui.category_detail.CategoryDetailActivity
import com.puppypedia.ui.main.ui.weight_chart.WeightChartActivity
import com.puppypedia.utils.helper.AppConstant

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.tvTitle.text = getString(R.string.category)
        clicksHandle()
        setCategoryAdapter()
    }

    private fun clicksHandle() {
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setCategoryAdapter() {
        val arrayList = ArrayList<ServicesModel>()
        arrayList.add(ServicesModel(R.drawable.icon1, "Feeding", true))
        arrayList.add(ServicesModel(R.drawable.icon3, "Weight Chart", false))
        arrayList.add(ServicesModel(R.drawable.icon5, "Training", false))
        arrayList.add(ServicesModel(R.drawable.icon7, "Medical/Health", false))
        arrayList.add(ServicesModel(R.drawable.icon7, "Toys and Chews", false))
        arrayList.add(ServicesModel(R.drawable.icon9, "Essential", false))
        arrayList.add(ServicesModel(R.drawable.icon11, "Grooming/Cosmetics", false))

        val serviceAdapter = ServicesAdapter(arrayList)
        binding.rvCategory.adapter = serviceAdapter

        serviceAdapter.onItemClick = { servicesModel ->
            if (servicesModel.service != "Weight Chart") {
                val intent = Intent(this, CategoryDetailActivity::class.java)
                intent.putExtra(AppConstant.HEADING, servicesModel.service)
                startActivity(intent)
            } else {
                startActivity(Intent(this, WeightChartActivity::class.java))
            }
        }
    }
}
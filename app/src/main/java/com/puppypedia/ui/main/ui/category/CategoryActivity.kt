package com.puppypedia.ui.main.ui.category

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puppypedia.R
import com.puppypedia.common_adapters.ClickCallBack
import com.puppypedia.common_adapters.ServicesAdapter
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.ui.main.ui.category_detail.CategoryDetailActivity
import com.puppypedia.ui.main.ui.weight_chart.WeightChartActivity
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class CategoryActivity : AppCompatActivity(), ClickCallBack {
    var data: HomeFragmentResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        tb.tv_title.text = getString(R.string.category)
        clicksHandle()
        data = (intent.getSerializableExtra("aboutResponse") as HomeFragmentResponse)
        //poz= intent.getStringExtra("selectedpos")!!.toInt()

        var serviceAdapter = ServicesAdapter(this, data!!, this)
        rvCategory.adapter = serviceAdapter
        // setCategoryAdapter()
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onItemClick(pos: Int, value: String) {
        when (value) {
            "cat" -> {
                if (data!!.body.category[pos].name == "Weight Chart") {
                    startActivity(Intent(this, WeightChartActivity::class.java))
                } else {
                    startActivity(
                        Intent(this, CategoryDetailActivity::class.java)
                            .putExtra("data", data!!.body.category[pos])
                    )
                }
            }
        }
    }

}
package com.puppypedia.ui.main.ui.category_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.databinding.ActivityCategoryDetailBinding
import com.puppypedia.utils.helper.AppConstant

class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.tvTitle.setTextColor(getColor(R.color.white))

        val heading = intent.getStringExtra(AppConstant.HEADING)

        if (heading != null){
            binding.tb.tvTitle.text = heading
        }

        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}
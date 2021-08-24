package com.puppypedia.ui.main.ui.about_us

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.databinding.ActivityAboutUsBinding
import com.puppypedia.utils.helper.AppConstant

//************************0 -> About Us**********************
//************************1 -> Terms & Conditions**********************
//************************2 -> Privacy Police**********************

class AboutUsActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }

        if (intent.getIntExtra(AppConstant.HEADING, 0) == 2) {
            binding.tb.tvTitle.text = getString(R.string.privacy_police)
        } else if (intent.getIntExtra(AppConstant.HEADING, 0) == 1) {
            binding.tb.tvTitle.text = getString(R.string.terms_conditions)
        } else {
            binding.tb.tvTitle.text = getString(R.string.about_us)
        }
    }
}
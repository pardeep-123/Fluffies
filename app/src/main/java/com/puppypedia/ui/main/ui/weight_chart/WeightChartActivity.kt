package com.puppypedia.ui.main.ui.weight_chart

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.puppypedia.R
import com.puppypedia.databinding.ActivityWeightChartBinding
import com.puppypedia.ui.fragments.StatisticsFragment
import com.puppypedia.ui.fragments.WeightFragment

class WeightChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeightChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clicksHandle()
        openFragment(WeightFragment())
    }

    private fun clicksHandle() {
        binding.tb.tvTitle.text = getString(R.string.weight_chart)
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnWeight.setOnClickListener {
            weightBtnClick()
        }

        binding.btnStatistics.setOnClickListener {
            statisticsBtnClick()
        }


    }

    private fun weightBtnClick() {
        binding.btnWeight.background = ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        binding.btnStatistics.setBackgroundColor(Color.TRANSPARENT)
        binding.btnWeight.setTextColor(ContextCompat.getColor(this, R.color.white))
        binding.btnStatistics.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is WeightFragment) {
            openFragment(WeightFragment())
        }
    }

    private fun statisticsBtnClick() {
        binding.btnStatistics.background =
            ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        binding.btnWeight.setBackgroundColor(Color.TRANSPARENT)
        binding.btnStatistics.setTextColor(ContextCompat.getColor(this, R.color.white))
        binding.btnWeight.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is StatisticsFragment) {
            openFragment(StatisticsFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rlWeightContainer, fragment)
        transaction.commit()
    }

    private fun currentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.rlWeightContainer)
    }
}
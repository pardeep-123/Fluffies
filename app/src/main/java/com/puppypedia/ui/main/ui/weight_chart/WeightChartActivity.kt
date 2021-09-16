package com.puppypedia.ui.main.ui.weight_chart

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.puppypedia.R
import com.puppypedia.ui.fragments.StatisticsFragment
import com.puppypedia.ui.fragments.weight.WeightFragment
import kotlinx.android.synthetic.main.activity_weight_chart.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class WeightChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_chart)
        clicksHandle()
        openFragment(WeightFragment())
    }

    private fun clicksHandle() {
        tb.tv_title.text = getString(R.string.weight_chart)
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }

        btnWeight.setOnClickListener {
            weightBtnClick()
        }

        btnStatistics.setOnClickListener {
            statisticsBtnClick()
        }


    }

    private fun weightBtnClick() {
        btnWeight.background = ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        btnStatistics.setBackgroundColor(Color.TRANSPARENT)
        btnWeight.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnStatistics.setTextColor(ContextCompat.getColor(this, R.color.black))

        if (currentFragment() !is WeightFragment) {
            openFragment(WeightFragment())
        }
    }

    private fun statisticsBtnClick() {
        btnStatistics.background =
            ContextCompat.getDrawable(this, R.drawable.bg_sky_blue_10dp)
        btnWeight.setBackgroundColor(Color.TRANSPARENT)
        btnStatistics.setTextColor(ContextCompat.getColor(this, R.color.white))
        btnWeight.setTextColor(ContextCompat.getColor(this, R.color.black))

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
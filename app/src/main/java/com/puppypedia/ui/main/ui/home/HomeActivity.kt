package com.puppypedia.ui.main.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.puppypedia.R
import com.puppypedia.databinding.ActivityHomeBinding
import com.puppypedia.ui.fragments.CalenderFragment
import com.puppypedia.ui.fragments.HomeFragment
import com.puppypedia.ui.fragments.SearchFragment
import com.puppypedia.ui.fragments.accountFragment.AccountFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private val homeVM: HomeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.homeVM = homeVM

        setHomeFragment()
        clicksHandle()
    }

    private fun clicksHandle() {
        binding.llHome.setOnClickListener {
            setHomeFragment()
        }

        binding.llSearch.setOnClickListener {
            if (currentFragment() !is SearchFragment) {
                openFragment(SearchFragment())

                setViews(
                    R.drawable.home_unactive, R.drawable.search_active,
                    R.drawable.cal_unactive, R.drawable.acc_unactive,
                    R.color.lightGrayA3A3A3, R.color.black,
                    R.color.lightGrayA3A3A3, R.color.lightGrayA3A3A3
                )
            }
        }

        binding.llCalendar.setOnClickListener {
            if (currentFragment() !is CalenderFragment) {
                openFragment(CalenderFragment())

                setViews(
                    R.drawable.home_unactive, R.drawable.search_unactive,
                    R.drawable.cal_active, R.drawable.acc_unactive,
                    R.color.lightGrayA3A3A3, R.color.lightGrayA3A3A3,
                    R.color.black, R.color.lightGrayA3A3A3
                )
            }
        }

        binding.llAccount.setOnClickListener {
            if (currentFragment() !is AccountFragment) {
                openFragment(AccountFragment())

                setViews(
                    R.drawable.home_unactive, R.drawable.search_unactive,
                    R.drawable.cal_unactive, R.drawable.acc_active,
                    R.color.lightGrayA3A3A3, R.color.lightGrayA3A3A3,
                    R.color.lightGrayA3A3A3, R.color.black
                )
            }
        }

    }


    private fun setHomeFragment() {
        if (currentFragment() !is HomeFragment) {
            openFragment(HomeFragment())

            setViews(
                R.drawable.home_active, R.drawable.search_unactive,
                R.drawable.cal_unactive, R.drawable.acc_unactive,
                R.color.black, R.color.lightGrayA3A3A3,
                R.color.lightGrayA3A3A3, R.color.lightGrayA3A3A3
            )
        }
    }

    private fun currentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.frame_main)
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, fragment)
        transaction.commit()
    }

    private fun setViews(
        ivHome: Int, ivSearch: Int, ivCal: Int, ivAcc: Int,
        homeTextColor: Int, searchTextColor: Int, calTextColor: Int, accTextColor: Int
    ) {
        binding.ivHome.setImageResource(ivHome)
        binding.ivSearch.setImageResource(ivSearch)
        binding.ivCalendar.setImageResource(ivCal)
        binding.ivAccount.setImageResource(ivAcc)

        binding.tvHome.setTextColor(getColor(homeTextColor))
        binding.tvSearch.setTextColor(getColor(searchTextColor))
        binding.tvCalendar.setTextColor(getColor(calTextColor))
        binding.tvAccount.setTextColor(getColor(accTextColor))
    }

    override fun onBackPressed() {
        if (currentFragment() !is HomeFragment) {
            setHomeFragment()
        } else {
            super.onBackPressed()
        }
    }
}
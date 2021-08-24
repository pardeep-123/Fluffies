package com.puppypedia.ui.main.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.puppypedia.R
import com.puppypedia.databinding.ActivityHomeBinding
import com.puppypedia.databinding.ActivityLoginBinding
import com.puppypedia.ui.auth.login.LoginVM
import com.puppypedia.ui.fragments.AccountFragment
import com.puppypedia.ui.fragments.CalenderFragment
import com.puppypedia.ui.fragments.HomeFragment
import com.puppypedia.ui.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private val homeVM : HomeVM by viewModels()

    private val loginVM : LoginVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.homeVM = homeVM

        openFragment(HomeFragment())

        btm_navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> openFragment(HomeFragment())
                R.id.favorites -> openFragment(SearchFragment())
                R.id.myspecials -> openFragment(CalenderFragment())
                R.id.settings -> openFragment(AccountFragment())

            }
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, fragment)
        transaction.commit()
    }

}
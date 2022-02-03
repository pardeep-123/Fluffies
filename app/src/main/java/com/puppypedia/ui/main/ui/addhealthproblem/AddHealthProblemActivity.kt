package com.puppypedia.ui.main.ui.addhealthproblem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.common_adapters.AddHealthListAdapter
import kotlinx.android.synthetic.main.activity_add_health_problem.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.*
import kotlinx.android.synthetic.main.activity_my_pet_profile.tb
import kotlinx.android.synthetic.main.auth_toolbar.view.*

/*created by naveen singh*/
class AddHealthProblemActivity : AppCompatActivity() {

    private var adapter : AddHealthListAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_health_problem)

        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.health_problem)

        setAdapter()
    }


    private fun setAdapter(){
        adapter = AddHealthListAdapter()
        rvHealthList.adapter = adapter
    }
}
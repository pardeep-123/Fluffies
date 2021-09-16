package com.puppypedia.ui.main.ui.category_detail
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puppypedia.R
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var catgory: HomeFragmentResponse.Body.Category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        tb.tv_title.setTextColor(getColor(R.color.white))
        tb.iv_back.setImageResource(R.drawable.arrow_back_white)
        catgory = intent.getSerializableExtra("data") as HomeFragmentResponse.Body.Category
//        val heading = intent.getStringExtra(AppConstant.HEADING)
//        if (heading != null){
//            if(heading == "Essential"){
//                binding.tb.tvTitle.text = getString(R.string.house_essential)
//            }else{
//                binding.tb.tvTitle.text = heading
//            }
//
//        }

        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = catgory.name
        // tb.tvDescription.text = catgory.description
    }
}
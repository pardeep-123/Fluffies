package com.puppypedia.ui.main.ui.category_detail

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.utils.helper.others.Constants
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class CategoryDetailActivity : AppCompatActivity() {
    lateinit var context: Context
    private lateinit var catgory: HomeFragmentResponse.Body.Category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        context = this
        tb.tv_title.setTextColor(getColor(R.color.black))
        tb.iv_back.setImageResource(R.drawable.arrow_back_white)
        catgory = intent.getSerializableExtra("data") as HomeFragmentResponse.Body.Category

        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = catgory.name
        tv_description.text = HtmlCompat.fromHtml(
            catgory.description, HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        // tv_description.text = catgory.description
        Glide.with(context).load(Constants.IMAGE_URL + catgory.image)
            .placeholder(R.drawable.dog_img).into(img)
    }
}
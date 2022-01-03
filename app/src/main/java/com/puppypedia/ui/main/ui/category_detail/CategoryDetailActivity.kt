package com.puppypedia.ui.main.ui.category_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.common_adapters.AddRecordAdapter
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.ui.main.ui.AddRecordActivity
import com.puppypedia.utils.helper.others.Constants
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class CategoryDetailActivity : AppCompatActivity() {
    lateinit var context: Context

    private lateinit var addRecordAdapter: AddRecordAdapter

    private lateinit var catgory: HomeFragmentResponse.Body.Category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        context = this
        tb.tv_title.setTextColor(getColor(R.color.white))
        tb.iv_back.setImageResource(R.drawable.arrow_back_white)
        catgory = intent.getSerializableExtra("data") as HomeFragmentResponse.Body.Category
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = catgory.name
        tv_description.text =
            HtmlCompat.fromHtml(catgory.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        // tv_description.text = catgory.description
        Glide.with(context).load(Constants.IMAGE_URL + catgory.logo)
            .placeholder(R.drawable.place_holder).into(img)

        iv_addRecord.setOnClickListener {
            startActivity(
                (Intent(this, AddRecordActivity::class.java)).putExtra("from", "add"))        }

        if (catgory.id == 34) {
            iv_addRecord.visibility = View.VISIBLE
            rv_addRecord.visibility = View.VISIBLE
            addRecordAdapter = AddRecordAdapter(this)
            rv_addRecord.adapter = addRecordAdapter

            addRecordAdapter.onItemClickListener = { poss, type ->
                var pos = poss
                if (type == "1") {
                    //mList.removeAt(pos)
                    addRecordAdapter.notifyDataSetChanged()
                }
                if (type == "2") {
                    startActivity(
                        (Intent(this, AddRecordActivity::class.java)).putExtra("from", "edit"))
                }

            }

        } else {
            iv_addRecord.visibility = View.GONE
            rv_addRecord.visibility = View.GONE

        }

    }
}
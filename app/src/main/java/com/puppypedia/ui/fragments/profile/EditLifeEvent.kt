package com.puppypedia.ui.fragments.profile

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puppypedia.R
import com.puppypedia.common_adapters.EditLifeAdapter
import com.puppypedia.common_adapters.ImagePagerAdapter
import com.puppypedia.model.GetLifeEventModel
import com.puppypedia.openImagePopUp
import info.jeovani.viewpagerindicator.constants.PagerItemType
import kotlinx.android.synthetic.main.activity_edit_life_event.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class EditLifeEvent : AppCompatActivity(),
    EditLifeAdapter.OnImageClick {
    private var eventList: GetLifeEventModel.Body?=null
    private var imageArray = ArrayList<String>()
    private var adapter: EditLifeAdapter? = null
    private var selectedArrayList = ArrayList<Int>()
    private var unSelectedArrayList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_life_event)

        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.lifeEvent)

        if (intent.extras?.get("lifeData")!=null){
            eventList = (intent?.extras!!.get("lifeData") as GetLifeEventModel.Body?)!!
            etTitle?.setText(eventList?.title)
            etTime?.setText(eventList?.date)
            tvDescriptionDetail?.setText(eventList?.description)
            for (i in 0 until eventList?.petImages?.size!!)
                imageArray.add(eventList?.petImages!![i].petImage)

        }
        setViewPager()
       //  setAdapter(imageArray)
    }

//    private fun setAdapter(list: ArrayList<String>) {
//        adapter = EditLifeAdapter(this, list)
//        rvEditImages.adapter = adapter
//    }

    override fun onImageClick(image:String) {
        openImagePopUp(image,this)
    }

    fun setViewPager(){
        selectedArrayList.add(Color.parseColor("#10C7DF"))
        unSelectedArrayList.add(Color.parseColor("#80000000"))

        viewPagerIndicator.itemsCount = imageArray.size
        viewPagerIndicator.itemType = PagerItemType.OVAL
         viewPagerIndicator.itemSelectedColors = selectedArrayList
        viewPagerIndicator.itemsUnselectedColors = unSelectedArrayList
        viewPagerIndicator.itemElevation = 1
        viewPagerIndicator.itemWidth = 9
        viewPagerIndicator.itemHeight = 9
        viewPagerIndicator.itemMargin = 6
        viewPagerIndicator.setBackgroundColor(Color.TRANSPARENT)

        rv_addRecord.adapter = EditLifeAdapter(this,this,imageArray)

    }
}
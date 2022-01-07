package com.puppypedia.common_adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.puppypedia.R
import com.puppypedia.ui.main.ui.category_detail.GetPetResponse
import info.jeovani.viewpagerindicator.ViewPagerIndicator
import info.jeovani.viewpagerindicator.constants.PagerItemType
import kotlinx.android.synthetic.main.add_record_layout.view.*


class AddRecordAdapter(
    var context: Context,
    var dataList: GetPetResponse,
    var clickCallBack: ClickCallBack
) :
    RecyclerView.Adapter<AddRecordAdapter.ViewHolderGrid>(), ImageAdapter.SendClick {

    private var selectedArrayList = ArrayList<Int>()
    private var unSelectedArrayList = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGrid {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.add_record_layout, parent, false)
        return ViewHolderGrid(view)
    }

    override fun onBindViewHolder(holder: ViewHolderGrid, position: Int) {
//        try {
//            Glide.with(holder.itemView.context)
//                .load(Constants.PET_IMAGE_URL + dataList.body[position].petImages[0].petImage)
//                .placeholder(R.drawable.place_holder).into(holder.itemView.iv_record)
//        } catch (e: Exception) {
//        }

        //  set Adapter
//        holder.rv_addRecord. = ImageAdapter(
//            context,
//            dataList.body[position].petImages ,this,"category"
//        )

        holder.itemView.tv_record.text = dataList.body[position].description
        try {
            holder.ivDelete.setOnClickListener {
                clickCallBack.onItemClick(position, "3")
            }

        } catch (e: Exception) {
        }

        holder.ivEdit.setOnClickListener {
            clickCallBack.onItemClick(position, "2")
        }

        selectedArrayList.add(Color.parseColor("#10C7DF"))
        unSelectedArrayList.add(Color.parseColor("#80000000"))

        holder.viewPagerIndicator.itemsCount = dataList.body[position].petImages.size
        holder.viewPagerIndicator.itemType = PagerItemType.OVAL
        holder. viewPagerIndicator.itemSelectedColors = selectedArrayList
        holder.viewPagerIndicator.itemsUnselectedColors = unSelectedArrayList
        holder.viewPagerIndicator.itemElevation = 1
        holder.viewPagerIndicator.itemWidth = 9
        holder.viewPagerIndicator.itemHeight = 9
        holder.viewPagerIndicator.itemMargin = 6
        holder.viewPagerIndicator.setBackgroundColor(Color.TRANSPARENT)

        holder.rvAddRecord.adapter = ImagePagerAdapter(context, dataList.body[position].petImages,this,"category")


    }
    override fun getItemCount(): Int {
        return dataList.body.size
    }
    class ViewHolderGrid(item1: View) : RecyclerView.ViewHolder(item1) {
       // val ivRecord: ImageView = item1.findViewById(R.id.iv_record)
      //  val tv_record: TextView = item1.findViewById(R.id.tv_record)
        val ivDelete: ImageView = item1.findViewById(R.id.iv_delete)
        val ivEdit: ImageView = item1.findViewById(R.id.iv_edit)
        val rvAddRecord: ViewPager = item1.findViewById(R.id.rv_addRecord)
        val viewPagerIndicator: ViewPagerIndicator = item1.findViewById(R.id.viewPagerIndicator)


    }

   }

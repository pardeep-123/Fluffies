package com.puppypedia.base


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


object BindingAdapter {

    @BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }

//    @BindingAdapter(value = ["setSpinnerAdapter","setSpinnerSelected"], requireAll = false)
//    @JvmStatic
//    fun setSpinnerAdapter(spn: Spinner, list: MutableList<String>, listener: AdapterView.OnItemSelectedListener)
//    {
//        spn.adapter = ArrayAdapter(spn.context,R.layout.item_spinner,list)
//       // spn.adapter = ArrayAdapter(spn.context,android.R.layout.simple_spinner_dropdown_item,list)
//
//        spn.onItemSelectedListener = listener
//    }
//    @BindingAdapter(value = ["setImage"])
//    @JvmStatic
//    fun setImage(iv: ImageView, image: Int) {
//        iv.setImageDrawable(ContextCompat.getDrawable(iv.context, image))
//    }
//
//    @BindingAdapter(value = ["setEyeClick"], requireAll = false)
//    @JvmStatic
//    fun setEyeClick(
//        cbEye: CheckBox,
//        etPass: EditText
//
//    ) {
//        cbEye.setOnCheckedChangeListener { compoundButton, boolean ->
//            if (etPass.transformationMethod == PasswordTransformationMethod.getInstance()) {
//                etPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
//            } else {
//                etPass.transformationMethod = PasswordTransformationMethod.getInstance()
//            }
//        }
//    }
//
//    /* @JvmStatic
//     @BindingAdapter(value = ["bind:nestedAdapter", "bind:nestedData"], requireAll = false)
//     fun setNestedData(
//         nestedRecycler: RecyclerView,
//         nestedAdapter: RecyclerAdapter<ProductListingResponse.Body.ProductsAddOn.AddonDetail>,
//         nestedData: List<ProductListingResponse.Body.ProductsAddOn.AddonDetail>
//     ) {
//         var adapter = nestedAdapter;
//         adapter.addItems(nestedData)
//         nestedRecycler.adapter = adapter
//     }*/
//
//
//    @BindingAdapter(value = ["setImageSrc"], requireAll = false)
//    @JvmStatic
//    fun setImageSrc(
//        ivImage: ImageView,
//        str: String?
//
//    ) {
//        try {
//            Glide.with(ivImage.context)
//                .asBitmap().load(CommonKeys.BASE_IMAGE_PROFILE + str)
//                /*.apply(RequestOptions().override(600, 200))*/
//                .into(object : CustomTarget<Bitmap>() {
//                    override fun onResourceReady(
//                        resource: Bitmap,
//                        transition: Transition<in Bitmap>?
//                    ) {
//                        ivImage.setImageBitmap(resource)
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {
//// this is called when imageView is cleared on lifecycle call or for
//// some other reason.
//// if you are referencing the bitmap somewhere else too other than this imageView
//// clear it here as you can no longer have the bitmap
//                    }
//
//                    override fun onLoadFailed(errorDrawable: Drawable?) {
//                        Log.e("Image", "LoadingFailed")
//                        ivImage.setImageResource(R.drawable.camera_get_img)
//                    }
//
//                    override fun onDestroy() {
//                        Log.e("Image", "destroyed")
//                        ivImage.setImageResource(R.drawable.camera_get_img)
//                    }
//                })
//
//
//            /*var img: String = CommonKeys.BASE_IMAGE_PROFILE + str
//            Glide.with(ivImage.context).load(CommonKeys.BASE_IMAGE_PROFILE + str).placeholder(
//                ContextCompat.getDrawable(
//                    ivImage.context,
//                    R.drawable.placeholder
//                )
//            ).into(ivImage)*/
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//
///*
//    @BindingAdapter(value = ["setImageNotification"])
//    @JvmStatic
//    fun setImageNotification(
//        ivNotification: ImageView,
//        bool: Boolean
//    ) {
//        val loginData = PreferenceFile.retrieveLoginData(ivNotification.context)
//        if (loginData?.body?.is_notification2!!.get() == "0") {
//            ivNotification.setImageDrawable(
//                ContextCompat.getDrawable(
//                    ivNotification.context,
//                    R.drawable.on
//                )
//            )
//
//        } else {
//            ivNotification.setImageDrawable(
//                ContextCompat.getDrawable(
//                    ivNotification.context,
//                    R.drawable.off
//                )
//            )
//        }
//    }
//
//    @BindingAdapter(value = ["setImageNotificationChanged"])
//    @JvmStatic
//    fun setImageNotificationChanged(
//        ivNotification: ImageView,
//        bool: Boolean
//    ) {
//        val loginData = PreferenceFile.retrieveLoginData(ivNotification.context)
//
//
//
//
//        if (bool) {
//            if (loginData?.body?.is_notification2!!.get() == "0") {
//                ivNotification.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        ivNotification.context,
//                        R.drawable.on
//                    )
//                )
//
//            } else {
//                ivNotification.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        ivNotification.context,
//                        R.drawable.off
//                    )
//                )
//            }
//        }
//    }*/
//
//
//    /*@JvmStatic
//    @BindingAdapter(value = ["bind:spinnerList", "bind:spinnerHint"], requireAll = false)
//    fun setSpinnerAdapter(spinner: Spinner, spinnerList: ArrayList<String>, spinnerHint: String) {
//        var list = spinnerList
//        var adapter = MySpinnerAdapter(spinner.context, R.layout.spinner_layout, list, spinnerHint);
//        spinner.adapter = adapter
//        adapter.notifyDataSetChanged()
//    }
//*/
//
//
//    @JvmStatic
//    @BindingAdapter(value = ["setCardImage"], requireAll = false)
//    fun setCardImage(
//        imageView: ImageView,
//        card: String?
//    ) {
//        if (card != null) {
//            when {
//                card.startsWith("4") -> {
//                    imageView.setImageResource(R.drawable.visa)
//                }
//                card.startsWith("5") -> {
//                    imageView.setImageResource(R.drawable.mastercard)
//                }
//                card.startsWith("37") -> {
//                    imageView.setImageResource(R.drawable.amex)
//                }
//                else -> {//FYI Discover starts with "6"
//                    imageView.setImageResource(R.drawable.discover)
//                }
//            }
//        }
//    }
//
//    @JvmStatic
//    @BindingAdapter(value = ["setEncodeNumber"], requireAll = false)
//    fun setEncodeNumber(
//        textView: TextView,
//        number: String
//    ) {
//        if (number.isNotEmpty()) {
//            var count = number.length - 4
//            var encode = "*"
//            for (item in 0..count - 1) {
//                encode = encode + "*"
//            }
//            textView.setText(number.replaceRange(0, number.length - 4, encode))
//        }
//    }
//    @BindingAdapter(value = ["setRating"], requireAll = false)
//    @JvmStatic
//    fun setRating(rb: SimpleRatingBar, str: String)
//    {
//        rb.rating = str.toFloat()
//    }
//
//    @BindingAdapter(value = ["setSwitch"], requireAll = false)
//    @JvmStatic
//    fun setSwitch(sw: SwitchCompat, swStatus:ObservableField<Boolean>)
//    {
//       sw.setOnCheckedChangeListener { buttonView, isChecked ->
//           swStatus.set(isChecked)
//       }
//    }
//
//    @BindingAdapter(value = ["setArrowImage"])
//    @JvmStatic
//    fun setArrowImage(ivArrow: ImageView, bool: Boolean) {
//        if (bool){
//            ivArrow.setImageResource(R.drawable.up_arrow)
//        }else{
//            ivArrow.setImageResource(R.drawable.arrow_down)
//        }
//    }
}
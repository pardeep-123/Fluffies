package com.fluffies.ui.main.ui.category_detail
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.fluffies.R
import com.fluffies.common_adapters.AddRecordAdapter
import com.fluffies.common_adapters.ClickCallBack
import com.fluffies.common_adapters.ImagePagerAdapter
import com.fluffies.model.CategoryModel
import com.fluffies.restApi.RestObservable
import com.fluffies.ui.fragments.home.HomeFragmentResponse
import com.fluffies.ui.main.ui.AllViewModel
import com.fluffies.ui.main.ui.add_record.AddRecordActivity
import com.fluffies.utils.helper.others.Constants
import com.fluffies.utils.helper.others.Helper
import com.fluffies.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class CategoryDetailActivity : AppCompatActivity(), Observer<RestObservable>, ClickCallBack ,ImagePagerAdapter.OnOpenImage{
    lateinit var context: Context
    var description = ""
    lateinit var dialog: Dialog

    lateinit var addRecordAdapter: AddRecordAdapter
    var aboutResponse: GetPetResponse? = null
    lateinit var sharedPrefUtil: SharedPrefUtil
    private lateinit var catgory: HomeFragmentResponse.Body.Category
    var list : ArrayList<GetPetResponse.Body> = ArrayList()
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        context = this
        sharedPrefUtil = SharedPrefUtil(this)
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
            startActivity((Intent(this, AddRecordActivity::class.java)).putExtra("from", "add"))
        }


    }

    override fun onResume() {
        super.onResume()
        if (catgory.id == 34) {
            iv_addRecord.visibility = View.VISIBLE
            rv_addRecord.visibility = View.VISIBLE
            apiCategoryDetails()

        } else {
            iv_addRecord.visibility = View.GONE
            rv_addRecord.visibility = View.GONE
        }
    }


    private fun apiPetData() {
        viewModel.apiPetData(this, sharedPrefUtil.petId.toString(), true)
        viewModel.mResponse.observe(this, this)
    }

    private fun apiCategoryDetails(){
        viewModel.apiCategoryDetails(this,catgory.id.toString(),true)
        viewModel.mResponse.observe(this,this)
    }
    fun apiDeletePet(postId: String) {
        viewModel.apiDeletePet(this, sharedPrefUtil.petId.toString(), postId, true)
        viewModel.mResponse.observe(this, this)
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {

                if (it.data is GetPetResponse) {

                    aboutResponse = it.data

                    if (aboutResponse?.body!!.isNotEmpty()) {
                        nodataFound.visibility = View.GONE
                        aboutResponse?.body?.add(0,GetPetResponse.Body().also {it.description =description })

                        addRecordAdapter = AddRecordAdapter(this, aboutResponse!!, this)
                        rv_addRecord.adapter = addRecordAdapter
                    }else
                        nodataFound.visibility = View.VISIBLE
                   // rv_addRecord.visibility = View.GONE
                }
                if (it.data is DeleteResponse) {

                    apiPetData()
                } else if (it.data is CategoryModel){
                    aboutResponse?.body?.clear()
                    if (it.data.body!=null)
                    description = it.data.body.description

                    apiPetData()
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(this, it.data as String)
                } else {
                    Helper.showErrorAlert(this, it.error.toString())
                }
            }
        }
    }
    override fun onItemClick(pos: Int, value: String) {
        when (value) {
            "3" -> {
                deleteDialog(aboutResponse!!.body[pos].petImages!![0].postId.toString())
            }
            "2" -> {
                val i = Intent(context, AddRecordActivity::class.java)
                i.putExtra("from", "edit")
                i.putExtra("data", aboutResponse!!.body[pos])
                startActivity(i)

            }
        }
    }

    private fun deleteDialog(postId: String) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_logout)
        dialog.show()
        dialog.findViewById<TextView>(R.id.tvTitle).text = "Are you sure you want to remove this record"
        dialog.findViewById<AppCompatButton>(R.id.btnYes).setOnClickListener {

            dialog.dismiss()
            apiDeletePet(postId)
        }
        dialog.findViewById<AppCompatButton>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()

        }
    }

    override fun onClick(path: String) {

    }
}
package com.puppypedia.ui.main.ui.category_detail
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.AddRecordAdapter
import com.puppypedia.common_adapters.ClickCallBack
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.add_record.AddRecordActivity
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class CategoryDetailActivity : AppCompatActivity(), Observer<RestObservable>, ClickCallBack {
    lateinit var context: Context
    var description = ""
    lateinit var addRecordAdapter: AddRecordAdapter
    var aboutResponse: GetPetResponse? = null
    lateinit var sharedPrefUtil: SharedPrefUtil
    private lateinit var catgory: HomeFragmentResponse.Body.Category
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
            apiPetData()
            /*  addRecordAdapter.onItemClickListener = { poss, type ->
                  var pos = poss
               if (type == "1") {
                    // mList.removeAt(pos)
                      addRecordAdapter.notifyDataSetChanged()
                  }
                  if (type == "2") {

                  }
                  if (type == "3") {
                      apiitPet()
                  }
              }*/
        } else {
            iv_addRecord.visibility = View.GONE
            rv_addRecord.visibility = View.GONE
        }
    }


    fun apiPetData() {
        viewModel.apiPetData(this, sharedPrefUtil.petId.toString(), true)
        viewModel.mResponse.observe(this, this)
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
                        addRecordAdapter = AddRecordAdapter(this, aboutResponse!!, this)
                        rv_addRecord.adapter = addRecordAdapter
                    }else
                        nodataFound.visibility = View.VISIBLE
                }
                if (it.data is DeleteResponse) {
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
                apiDeletePet(aboutResponse!!.body[pos].petImages[0].postId.toString())
            }
            "2" -> {
                var i = Intent(context, AddRecordActivity::class.java)
                i.putExtra("from", "edit")
                i.putExtra("data", aboutResponse!!.body[pos])
                startActivity(i)
                /*
                 startActivity((Intent(this, AddRecordActivity::class.java))
                     .putExtra("from", "edit")
                     .putExtra("description",  aboutResponse?.body)
                 )*/
            }
        }
    }
}
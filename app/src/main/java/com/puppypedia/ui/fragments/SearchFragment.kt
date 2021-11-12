package com.puppypedia.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.ClickCallBack
import com.puppypedia.common_adapters.SearchAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category_detail.CategoryDetailActivity
import com.puppypedia.ui.main.ui.weight_chart.WeightChartActivity
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(), Observer<RestObservable>, ClickCallBack {
    lateinit var v: View
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    var aboutResponse: HomeFragmentResponse? = null
    var list = ArrayList<HomeFragmentResponse.Body.Category>()
    lateinit var searchAdapter: SearchAdapter
    var data: HomeFragmentResponse? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search, container, false)
        apiSearch()
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ivCross.setOnClickListener {
            edtSearch.text.clear()
        }

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list.clear()
                for (item in aboutResponse!!.body.category) {
                    if (item.name.toLowerCase()
                            .contains(s.toString().toLowerCase())
                    ) {
                        list.add(item)
                    }
                }
                if (list.isEmpty()) {
                    rc_services.visibility = View.GONE
                    no_notification.visibility = View.VISIBLE
                    no_notification.text = "No Data Found"
                } else {
                    rc_services.visibility = View.VISIBLE
                    no_notification.visibility = View.GONE
                }

                searchAdapter.notifyDataSetChanged()
            }
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    ivCross.visibility = View.VISIBLE
                } else {
                    ivCross.visibility = View.GONE
                }
            }

        })
    }
    fun apiSearch() {
        viewModel.getHomeDetails(requireActivity(), true)
        viewModel.mResponse.observe(requireActivity(), this)
    }
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is HomeFragmentResponse) {
                    aboutResponse = it.data
                    for (item in aboutResponse!!.body.category) {
                        list.add(item)
                    }
                    rc_services.visibility = View.VISIBLE
                    no_notification.visibility = View.GONE
                    searchAdapter = SearchAdapter(requireContext(), list, this)
                    rc_services.adapter = searchAdapter

                    /* if(aboutResponse!!.body.category.isEmpty())
                     {
                         rc_services.visibility = View.GONE
                         no_notification.visibility = View.VISIBLE
                     }
                     else{
                         rc_services.visibility = View.VISIBLE
                         no_notification.visibility = View.GONE
                     }*/
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(requireActivity(), it.data as String)
                } else {
                    Helper.showErrorAlert(requireActivity(), it.error.toString())
                }
            }
        }
    }
    override fun onItemClick(pos: Int, value: String) {
        when (value) {
            "cat" -> {
                //data!!.body.category[pos].name
                //list[pos].name == "Weight Chart"
                if (list[pos].name == "Weight Chart") {
                    startActivity(Intent(requireContext(), WeightChartActivity::class.java))
                } else {
                    val i = Intent(requireContext(), CategoryDetailActivity::class.java)
                    i.putExtra("data", list[pos])
                    startActivity(i)
                }
            }
        }
    }
}
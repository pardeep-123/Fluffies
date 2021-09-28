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
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list.clear()
                for (i in 0 until aboutResponse!!.body.category.size) {
                    if (aboutResponse!!.body.category.get(i).name.toLowerCase()
                            .contains(s.toString().toLowerCase())
                    ) {
                        list.add(aboutResponse!!.body.category.get(i))
                    }
                }
/*
                if(s!!.isNotEmpty())
                {
                    searchText = s.toString()
                    api(s.toString())
                }
                else
                {
                    tvNoDataFound.visibility= View.VISIBLE
                    lottiView.visibility= View.VISIBLE
                    recyclerview.visibility = View.GONE
                    tv_search_result_count.visibility = View.GONE
                }}
*/
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
                    searchAdapter = SearchAdapter(requireContext(), list, this)
                    rc_services.adapter = searchAdapter
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
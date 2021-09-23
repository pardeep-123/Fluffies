package com.puppypedia.ui.fragments.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StatisticsFragment : Fragment(), Observer<RestObservable> {
    var datetype = "0"
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    lateinit var v: View
    var x: ArrayList<Entry> = ArrayList()
/*
    lateinit var y: ArrayList<String>
*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_statistics, container, false)
        apiChart("2")

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicksHandle()
    }

    private fun clicksHandle() {
        btnDay.setOnClickListener {
            btnDay.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_sky_blue_10dp)
            btnDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            btnMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            btnMonth.setBackgroundColor(Color.TRANSPARENT)
            btnYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            btnYear.setBackgroundColor(Color.TRANSPARENT)
            apiChart("1")
        }

        btnMonth.setOnClickListener {
            btnMonth.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_sky_blue_10dp)
            btnMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            btnDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            btnDay.setBackgroundColor(Color.TRANSPARENT)
            btnYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            btnYear.setBackgroundColor(Color.TRANSPARENT)
            apiChart("2")

        }

        btnYear.setOnClickListener {
            btnYear.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_sky_blue_10dp)
            btnYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            btnDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            btnDay.setBackgroundColor(Color.TRANSPARENT)
            btnMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            btnMonth.setBackgroundColor(Color.TRANSPARENT)
            apiChart("3")
        }
    }

    private fun setGraph(registerResponse: StatisticsResponse) {


        lineChat.clear()
        x.clear()
        for (item in registerResponse.body) {
            if (item.date.isNotEmpty()) {
                var a = (dateGet(item.date).toInt()).toFloat()
                var b = (item.weight).toFloat()
                x.add(Entry(a, b))
            }

        }
        /*x.add(Entry(1F, 1.5F))
        x.add(Entry(15F, 1.1F))
        x.add(Entry(20F, 0.7F))
        x.add(Entry(22F, 2.6F))
        x.add(Entry(25F, 2F))
        x.add(Entry(37F, 2.1F))
        x.add(Entry(30F, 2.1F))
        x.add(Entry(40F, 2.1F))
        x.add(Entry(28F, 2.6F))
        x.add(Entry(30F, 03F))*/

        lineChat.setDrawGridBackground(false)
        lineChat.description.isEnabled = false
        lineChat.setTouchEnabled(true)
        lineChat.setDragEnabled(true)
        lineChat.setScaleEnabled(true)
        lineChat.setPinchZoom(true)
        lineChat.getXAxis().setTextSize(15f)
        lineChat.getAxisLeft().setTextSize(15f)

        val xl: XAxis = lineChat.getXAxis()
        xl.setAvoidFirstLastClipping(true)
        xl.setPosition(XAxis.XAxisPosition.BOTTOM)

        val leftAxis: YAxis = lineChat.getAxisLeft()
        leftAxis.isInverted = false
        val rightAxis: YAxis = lineChat.getAxisRight()
        rightAxis.isEnabled = false
        val l: Legend = lineChat.getLegend()
        l.form = Legend.LegendForm.LINE


        val set1 = LineDataSet(x, "")
        set1.setColor(requireContext().getColor(R.color.theme_Color))
        set1.lineWidth = 1.5f
        set1.circleRadius = 5f
        val data = LineData(set1)
        lineChat.setData(data)
        lineChat.invalidate()
    }

    fun apiChart(datetype: String) {
        viewModel.addPetChartApi(
            requireActivity(),
            datetype,
            SharedPrefUtil.getInstance().petId,
            true
        )
        viewModel.mResponse.observe(requireActivity(), this)
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is StatisticsResponse) {
                    val registerResponse: StatisticsResponse = it.data
                    setGraph(registerResponse)
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


    fun dateGet(date: String): String {
        val input_date = date
        val format1 = SimpleDateFormat("yyyy-MM-dd")
        val dt1: Date = format1.parse(input_date)
        val format2: DateFormat = SimpleDateFormat("dd")
        val finalDay: String = format2.format(dt1)
        return finalDay
    }

}
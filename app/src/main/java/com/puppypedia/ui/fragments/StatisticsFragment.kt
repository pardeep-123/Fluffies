package com.puppypedia.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.puppypedia.R
import com.puppypedia.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding

    lateinit var x: ArrayList<Entry>
    lateinit var y: ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStatisticsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clicksHandle()
        setGraph()
    }

    private fun clicksHandle() {
        binding.btnDay.setOnClickListener {
            binding.btnDay.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_sky_blue_10dp)
            binding.btnDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btnMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnMonth.setBackgroundColor(Color.TRANSPARENT)
            binding.btnYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnYear.setBackgroundColor(Color.TRANSPARENT)
        }

        binding.btnMonth.setOnClickListener {
            binding.btnMonth.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_sky_blue_10dp)
            binding.btnMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btnDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnDay.setBackgroundColor(Color.TRANSPARENT)
            binding.btnYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnYear.setBackgroundColor(Color.TRANSPARENT)
        }

        binding.btnYear.setOnClickListener {
            binding.btnYear.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_sky_blue_10dp)
            binding.btnYear.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btnDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnDay.setBackgroundColor(Color.TRANSPARENT)
            binding.btnMonth.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnMonth.setBackgroundColor(Color.TRANSPARENT)
        }
    }


    private fun setGraph() {
        x = ArrayList()
        y = ArrayList()


        binding.lineChat.setDrawGridBackground(false)
        binding.lineChat.description.isEnabled = false
        binding.lineChat.setTouchEnabled(true)
        binding.lineChat.setDragEnabled(true)
        binding.lineChat.setScaleEnabled(true)
        binding.lineChat.setPinchZoom(true)
        binding.lineChat.getXAxis().setTextSize(10f)
        binding.lineChat.getAxisLeft().setTextSize(10f)

        val xl: XAxis = binding.lineChat.getXAxis()
        xl.setAvoidFirstLastClipping(true)
        xl.setPosition(XAxis.XAxisPosition.BOTTOM)

        val leftAxis: YAxis = binding.lineChat.getAxisLeft()
        leftAxis.isInverted = false
        val rightAxis: YAxis = binding.lineChat.getAxisRight()
        rightAxis.isEnabled = false
        val l: Legend = binding.lineChat.getLegend()
        l.form = Legend.LegendForm.LINE


        x.add(Entry(0F, 1.2f))
        x.add(Entry(10F, 1.5F))
        x.add(Entry(15F, 1.1F))
        x.add(Entry(20F, 0.7F))
        x.add(Entry(22F, 2.6F))
        x.add(Entry(25F, 2F))
        x.add(Entry(37F, 2.1F))
        x.add(Entry(28F, 2.6F))
        x.add(Entry(30F, 03F))

        y.add("A")
        y.add("B")
        y.add("C")
        y.add("D")
        y.add("E")


        val set1 = LineDataSet(x, "")
        set1.setColor(requireContext().getColor(R.color.theme_Color))
        set1.lineWidth = 1.5f
        set1.circleRadius = 5f
        val data = LineData(set1)
        binding.lineChat.setData(data)
        binding.lineChat.invalidate()

    }


}
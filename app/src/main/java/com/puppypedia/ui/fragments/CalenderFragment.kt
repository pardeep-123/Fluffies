package com.puppypedia.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.puppypedia.R
import com.puppypedia.common_adapters.AppointmentAdapter
import com.puppypedia.databinding.FragmentCalenderBinding
import com.puppypedia.ui.main.ui.addremainder.AddRemainderActivity
import com.puppypedia.utils.helper.AppUtils
import java.text.SimpleDateFormat
import java.util.*


class CalenderFragment : Fragment() {

    private lateinit var binding: FragmentCalenderBinding
    private val dateFormatMonth: SimpleDateFormat =
        SimpleDateFormat("MMM yyyy", Locale.getDefault())

    private lateinit var appointmentAdapter: AppointmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clicksHandle()
        setCalendarEvent()

        appointmentAdapter = AppointmentAdapter()
        binding.rvAppointments.adapter = appointmentAdapter
    }

    private fun clicksHandle() {
        binding.ivAdd.setOnClickListener {
            startActivity(Intent(requireContext(), AddRemainderActivity::class.java))
        }

        binding.ivBackCal.setOnClickListener {
            binding.compactCalendarView.scrollLeft()
        }

        binding.ivNextCal.setOnClickListener {
            binding.compactCalendarView.scrollRight()
        }
    }

    private fun setCalendarEvent() {
        binding.tvMonth.setText(AppUtils.dateInString(binding.compactCalendarView.firstDayOfCurrentMonth.time,"MMM yyyy"))
        binding.compactCalendarView.setUseThreeLetterAbbreviation(true)

        val ev1 = Event(
            requireContext().getColor(R.color.theme_Color),
            1630315210000L,
            "Teachers' Professional Day"
        )
        val ev2 = Event(
            requireContext().getColor(R.color.theme_Color),
            1629451210000L,
            "Teachers' Professional Day"
        )
        val ev3 = Event(
            requireContext().getColor(R.color.theme_Color),
            1629364810000,
            "Teachers' Professional Day"
        )
        binding.compactCalendarView.addEvent(ev1)
        binding.compactCalendarView.addEvent(ev2)
        binding.compactCalendarView.addEvent(ev3)

        /*binding.compactCalendarView.listener*/

        binding.compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {

            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                binding.tvMonth.setText(AppUtils.dateInString(firstDayOfNewMonth?.time!!,"MMM yyyy"))
            }
        })
    }
}
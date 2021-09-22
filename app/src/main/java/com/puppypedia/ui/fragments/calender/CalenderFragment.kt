package com.puppypedia.ui.fragments.calender

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.AppointmentAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.addremainder.AddRemainderActivity
import com.puppypedia.utils.helper.AppUtils
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.fragment_calender.*
import java.text.SimpleDateFormat
import java.util.*


class CalenderFragment : Fragment(), Observer<RestObservable> {
    var aboutResponse: CalenderReminderResponse? = null
    lateinit var v: View
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private val dateFormatMonth: SimpleDateFormat =
        SimpleDateFormat("MMM yyyy", Locale.getDefault())

    private lateinit var appointmentAdapter: AppointmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_calender, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicksHandle()
        setCalendarEvent()
    }

    private fun clicksHandle() {
        ivAdd.setOnClickListener {
            startActivity(Intent(requireContext(), AddRemainderActivity::class.java))
        }

        ivBackCal.setOnClickListener {
            compactCalendarView.scrollLeft()
        }

        ivNextCal.setOnClickListener {
            compactCalendarView.scrollRight()
        }
    }

    private fun setCalendarEvent() {
        tvMonth.setText(
            AppUtils.dateInString(
                compactCalendarView.firstDayOfCurrentMonth.time,
                "MMM yyyy"
            )
        )
        compactCalendarView.setUseThreeLetterAbbreviation(true)

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
        compactCalendarView.addEvent(ev1)
        compactCalendarView.addEvent(ev2)
        compactCalendarView.addEvent(ev3)
        /*binding.compactCalendarView.listener*/
        compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                tvMonth.setText(AppUtils.dateInString(firstDayOfNewMonth?.time!!, "MMM yyyy"))
            }
        })
    }

    /*      fun apiGetReminder() {
                viewModel.getReminderApi(requireActivity(),, true)
                viewModel.mResponse.observe(viewLifecycleOwner, this)
            }*/
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is CalenderReminderResponse) {
                    aboutResponse = it.data
                    /* weightAdapter = WeightAdapter(requireContext(), aboutResponse!!)
                        rvWeight.adapter = weightAdapter*/
                    appointmentAdapter = AppointmentAdapter(requireContext(), aboutResponse!!)
                    rvAppointments.adapter = appointmentAdapter
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
}
package com.puppypedia.ui.fragments.calender

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.AppointmentAdapter
import com.puppypedia.common_adapters.CheckChangeClickCallBack
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.addremainder.AddRemainderActivity
import com.puppypedia.ui.main.ui.category_detail.DeleteResponse
import com.puppypedia.utils.helper.AppUtils
import com.puppypedia.utils.helper.CommonMethods
import com.puppypedia.utils.helper.RecyclerItemTouchHelper
import com.puppypedia.utils.helper.others.Helper
import kotlinx.android.synthetic.main.fragment_calender.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class CalenderFragment : Fragment(), Observer<RestObservable>, CheckChangeClickCallBack,
    RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    var aboutResponse: CalenderGetReminderResponse? = null
    private lateinit var dialog: Dialog
    var reminderList: ArrayList<CalendarDataModel> = ArrayList()
    var allReminderList: ArrayList<CalendarDataModel> = ArrayList()
    lateinit var v: View
    private var reminderid = ""

    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private val dateFormatMonth: SimpleDateFormat =
        SimpleDateFormat("MMM yyyy", Locale.getDefault())
    private lateinit var appointmentAdapter: AppointmentAdapter

    var orderId = ""
    var myposition = -1

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
        // orderId = intent.getStringExtra("orderId").toString()

        /*worked by naveen singh*/
        val itemTouchHelperCallback = RecyclerItemTouchHelper(requireContext(), this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvAppointments)
//        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvNotifications)
    }

    override fun onResume() {
        super.onResume()
        apiGetReminder()
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
        viewAll.setOnClickListener {
            adapterViewCall()
        }
    }

    private fun setCalendarEvent() {
        tvMonth.text = AppUtils.dateInString(
            compactCalendarView.firstDayOfCurrentMonth.time,
            "MMM yyyy"
        )
        compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                searchedData(CommonMethods.timeStampToDate((dateClicked.time / 1000).toInt()))
                Log.e("sd", (dateClicked.date / 1000).toString())
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                tvMonth.text = AppUtils.dateInString(firstDayOfNewMonth?.time!!, "MMM yyyy")
                searchedData(CommonMethods.timeStampToDate((firstDayOfNewMonth.time / 1000).toInt()))
            }
        })
    }

    private fun apiGetReminder() {
        viewModel.getReminderApi(requireActivity(), "", true)
        viewModel.mResponse.observe(viewLifecycleOwner, this)
    }

    fun apiReminderOnOff(isRemind: String, reminderid: String) {
        viewModel.reminderOnOffApi(requireActivity(), isRemind, reminderid, true)
        viewModel.mResponse.observe(viewLifecycleOwner, this)
    }


    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is CalenderGetReminderResponse) {
                    aboutResponse = it.data
                    allReminderList.clear()
                    compactCalendarView.removeAllEvents()
                    for (item in it.data.body) {
                        allReminderList.add(
                            CalendarDataModel(
                                item.id.toString(),
                                item.name,
                                item.petName ?: "", item.time,
                                item.date,
                                item.isremind
                            )
                        )

                        compactCalendarView.addEvent(
                            Event(
                                R.color.colorPrimary,
                                CommonMethods.dateToTimestamp(item.date)
                            )
                        )

                    }
                    searchedData(CommonMethods.timeStampToDate((System.currentTimeMillis() / 1000).toInt()))
                } else if (it.data is DeleteResponse) {
                    apiGetReminder()
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

    fun searchedData(dateType: String) {
        val listData: ArrayList<CalendarDataModel> = ArrayList()
        for (item in allReminderList) {
            if (item.date == dateType) {
                listData.add(item)
            }
        }
        reminderList.clear()
        reminderList.addAll(listData)
        adapterCall()

    }

    fun adapterCall() {
        rvAppointments.adapter = AppointmentAdapter(requireContext(), reminderList, this)
    }

    fun adapterViewCall() {
        rvAppointments.adapter = AppointmentAdapter(requireContext(), allReminderList, this)
    }

    override fun onItemClick(pos: Int, value: Boolean) {
        apiReminderOnOff(if (value) "1" else "0", reminderList[pos].id)
    }


    /*worked by naveen singh*/
    @SuppressLint("NotifyDataSetChanged")
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {

        dialog = Dialog(requireContext())
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
        dialog.findViewById<TextView>(R.id.tvTitle).text = "Are you sure you want to delete"
        dialog.findViewById<AppCompatButton>(R.id.btnYes).setOnClickListener {
            dialog.dismiss()
            myposition = position
            val map = HashMap<String, String>()
            map["reminderId"] = allReminderList[position].id
            viewModel.deletePetReminder(requireActivity(), true, map)
            viewModel.mResponse.observe(viewLifecycleOwner, this)

        }
        dialog.findViewById<AppCompatButton>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()
        }
        rvAppointments.adapter?.notifyDataSetChanged()
    }
}
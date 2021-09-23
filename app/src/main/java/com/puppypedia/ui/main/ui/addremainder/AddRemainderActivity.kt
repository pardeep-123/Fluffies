package com.puppypedia.ui.main.ui.addremainder

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.ClickCallBack
import com.puppypedia.common_adapters.PetListAdapter
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.mypetprofile.PetProfileResponse
import com.puppypedia.utils.helper.AppUtils
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.activity_add_remainder.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import java.util.*

class AddRemainderActivity : AppCompatActivity(), Observer<RestObservable>, ClickCallBack {
    lateinit var context: Context
    private lateinit var mValidationClass: ValidationsClass
    var petId = ""
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    lateinit var sharedPrefUtil: SharedPrefUtil
    private val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var date: DatePickerDialog.OnDateSetListener
    private lateinit var time: TimePickerDialog.OnTimeSetListener
    var isRemind = "1"
    lateinit var adapter: PetListAdapter
    var aboutResponse: PetProfileResponse? = null
    var arrayList = ArrayList<PetProfileResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remainder)
        mValidationClass = ValidationsClass.getInstance()
        tb.tv_title.text = getString(R.string.add_remainder)
        sharedPrefUtil = SharedPrefUtil(this)
        clicksHandle()
        context = this
        apiPetList()

    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        btnSubmit.setOnClickListener {
            apiReminder()
        }
        sc_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            isRemind = if (isChecked) {
                "1"
            } else {
                " 0"
            }
        }
        edtDate.setOnClickListener {
            date =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    myCalendar[Calendar.YEAR] = year
                    myCalendar[Calendar.MONTH] = monthOfYear
                    myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                    edtDate.setText(
                        AppUtils.dateInString(
                            myCalendar.timeInMillis,
                            "yyyy-MM-dd"
                        )
                    )
                }
            datePicker(this)
        }
        edtTime.setOnClickListener {
            time = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                myCalendar[Calendar.HOUR_OF_DAY] = hour
                myCalendar[Calendar.MINUTE] = minute
                edtTime.setText(AppUtils.dateInString(myCalendar.timeInMillis, "hh:mm"))
            }
            timePicker(this)
        }
    }

    fun appointmentDialog() {
        var dialog = Dialog(this@AddRemainderActivity)

        if (!dialog.isShowing) {
            dialog.show()
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_appointment)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setGravity(Gravity.CENTER)


        val done = dialog.findViewById<Button>(R.id.btnDone)
        done.setOnClickListener {
            finish()
        }

    }
    private fun datePicker(context: Context) {
        val datePicker = DatePickerDialog(
            context, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]
        )
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()
    }
    private fun timePicker(context: Context) {
        TimePickerDialog(
            context,
            time,
            myCalendar[Calendar.HOUR_OF_DAY],
            myCalendar[Calendar.MINUTE],
            false
        ).show()
    }

    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        val date = edtDate.text.toString().trim()
        val time = edtTime.text.toString().trim()
        var check = false
        if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_name))
        else if (mValidationClass.checkStringNull(date))
            Helper.showErrorAlert(this, resources.getString(R.string.error_date))
        else if (mValidationClass.checkStringNull(time))
            Helper.showErrorAlert(this, resources.getString(R.string.error_time))
        else
            check = true
        return check
    }

    fun apiReminder() {
        if (isValid()) {
            val name = etName.text.toString().trim()
            val date = edtDate.text.toString().trim()
            val time = edtTime.text.toString().trim()
            val map = HashMap<String, String>()
            map["petid"] = petId
            map["time"] = time
            map["date"] = date
            map["name"] = name
            map["isRemind"] = isRemind
            viewModel.addReminderApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    fun apiPetList() {
        viewModel.getPetProfile(this, true)
        viewModel.mResponse.observe(this, this)
    }

    override fun onChanged(liveData: RestObservable?) {
        when {
            liveData!!.status == Status.SUCCESS -> {
                if (liveData.data is PetProfileResponse) {
                    aboutResponse = liveData.data
                    petId = aboutResponse!!.body[0].id.toString()
                    adapter = PetListAdapter(this, aboutResponse!!, this)
                    rvDogs.adapter = adapter
                }
                if (liveData.data is AddReminderResponse) {
                    appointmentDialog()
                }
            }
            liveData.status == Status.ERROR -> {
                if (liveData.data != null) {
                    Helper.showErrorAlert(this, liveData.data as String)
                } else {
                    Helper.showErrorAlert(this, liveData.error.toString())
                }
            }
        }
    }

    override fun onItemClick(pos: Int, value: String) {
        when (value) {
            "pet" -> {
                petId = aboutResponse!!.body[pos].id.toString()
            }
        }
    }

}
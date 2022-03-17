package com.fluffies.ui.main.ui.add_weight

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.fluffies.R
import com.fluffies.restApi.RestObservable
import com.fluffies.ui.main.ui.AllViewModel
import com.fluffies.utils.helper.AppUtils
import com.fluffies.utils.helper.others.Helper
import com.fluffies.utils.helper.others.SharedPrefUtil
import com.fluffies.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.activity_add_weight.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import java.util.*
import kotlin.collections.set

class AddWeightActivity : AppCompatActivity(), Observer<RestObservable> {
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass
    private val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var date: DatePickerDialog.OnDateSetListener
    private lateinit var time: TimePickerDialog.OnTimeSetListener
    val ageArrayList = ArrayList<String>()
    var age = ""
    lateinit var sharedPrefUtil: SharedPrefUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weight)
        sharedPrefUtil = SharedPrefUtil(this)
        mValidationClass = ValidationsClass.getInstance()
        tb.tv_title.text = getString(R.string.add_weight)
        clicksHandle()
        setSpinnerAge()

        number_picker.setMinValue(0)
        number_picker.setMaxValue(40)
        number_picker.setWrapSelectorWheel(true)

        np.setMinValue(0)
        np.setMaxValue(60)
        np.setWrapSelectorWheel(true)
        np.setOnValueChangedListener { picker, oldVal, newVal ->
            val et = np.getChildAt(0) as EditText
            et.setTextColor(ContextCompat.getColor(this, R.color.theme_Color))
        }
    }
    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tvDate.setOnClickListener {
            date =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    myCalendar[Calendar.YEAR] = year
                    myCalendar[Calendar.MONTH] = monthOfYear
                    myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                    tvDate.setText(
                        AppUtils.dateInString(
                            myCalendar.timeInMillis,
                            "yyyy-MM-dd"
                        )
                    )
                }
            datePicker(this)
        }

        tvTime.setOnClickListener {
            time = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                myCalendar[Calendar.HOUR_OF_DAY] = hour
                myCalendar[Calendar.MINUTE] = minute

                tvTime.setText(
                    AppUtils.dateInString(
                        myCalendar.timeInMillis,
                        "hh:mm "
                    )
                )
            }
            timePicker(this)
        }

        btnSave.setOnClickListener {
            callApi()
        }
    }
    private fun setSpinnerAge() {
        ageArrayList.add("Age")

        for (i in 1..60) {
            ageArrayList.add(i.toString() + " " + "yr")
        }
        val adapterAge = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, ageArrayList)
        spinnerAge.adapter = adapterAge
        spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
                tvSpinner.setPadding(0, 0, 0, 0)
                /*tvSpinner.typeface = ResourcesCompat.getFont(
                    this@AddWeightActivity, R.font.opensans_regular)*/
                if (pos == 0) {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@AddWeightActivity,
                            R.color.lightGrayA3A3A3
                        )
                    )
                } else {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@AddWeightActivity,
                            R.color.black,
                        )
                    )
                }
                age = ageArrayList[pos]
            }
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
        val date = tvDate.text.toString().trim()
        val time = tvTime.text.toString().trim()
        var weight = number_picker.value.toString() + "." + np.value

        var check = false
        if (weight.equals("0.0"))
            Helper.showErrorAlert(this, "Please select weight")
        else if (mValidationClass.checkStringNull(date))
            Helper.showErrorAlert(this, resources.getString(R.string.error_date))
        else if (mValidationClass.checkStringNull(time))
            Helper.showErrorAlert(this, resources.getString(R.string.error_time))
        else if (age == "" || age == "Age")
            Helper.showErrorAlert(this, "Please select age")
        else
            check = true
        return check
    }

    fun callApi() {
        if (isValid()) {
            val date = tvDate.text.toString().trim()
            val time = tvTime.text.toString().trim()
            //  val age = etAge.text.toString().trim()-
            var weight = number_picker.value.toString() + "." + np.value
            val map = HashMap<String, String>()
            map["petid"] = sharedPrefUtil.petId.toString()
            map["date"] = date
            map["time"] = time
            map["weight"] = weight
            map["age"] = age.replace("yr", "")
            viewModel.addPetWeightApi(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }
    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is AddWeightResponse) {
                    val aboutResponse: AddWeightResponse = it.data
                    finish()
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
}
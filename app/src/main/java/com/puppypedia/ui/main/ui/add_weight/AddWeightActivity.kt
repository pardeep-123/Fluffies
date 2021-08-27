package com.puppypedia.ui.main.ui.add_weight

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.puppypedia.R
import com.puppypedia.databinding.ActivityAddWeightBinding
import com.puppypedia.utils.helper.AppUtils
import java.util.*


class AddWeightActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddWeightBinding

    private val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var date: DatePickerDialog.OnDateSetListener
    private lateinit var time: TimePickerDialog.OnTimeSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.tvTitle.text = getString(R.string.add_weight)
        clicksHandle()
        setSpinnerAge()

        binding.numberPicker.setMinValue(0)
        binding.numberPicker.setMaxValue(10)
        binding.numberPicker.setWrapSelectorWheel(true)

        binding.np.setMinValue(0)
        binding.np.setMaxValue(60)
        binding.np.setWrapSelectorWheel(true)

       /* binding.np.setOnValueChangedListener { picker, oldVal, newVal ->
            val et = binding.np.getChildAt(0) as EditText
            et.setTextColor(ContextCompat.getColor(this, R.color.theme_Color))
        }*/


    }

    private fun clicksHandle() {
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.tvDate.setOnClickListener {
            date =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    myCalendar[Calendar.YEAR] = year
                    myCalendar[Calendar.MONTH] = monthOfYear
                    myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                    binding.tvDate.setText(
                        AppUtils.dateInString(
                            myCalendar.timeInMillis,
                            "dd-MMM-yyyy"
                        )
                    )
                }

            datePicker(this)
        }

        binding.tvTime.setOnClickListener {
            time = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                myCalendar[Calendar.HOUR_OF_DAY] = hour
                myCalendar[Calendar.MINUTE] = minute

                binding.tvTime.setText(
                    AppUtils.dateInString(
                        myCalendar.timeInMillis,
                        "hh:mm a"
                    )
                )


            }

            timePicker(this)
        }

        binding.btnSave.setOnClickListener {
            finish()
        }
    }

    private fun setSpinnerAge() {
        val arrayList = arrayListOf("Age", "1 yr", "2 yr")

        val adapterAge = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        binding.spinnerAge.adapter = adapterAge

        binding.spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
                /*tvSpinner.setPadding(0, 0, 0, 0)*/

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
                            R.color.black
                        )
                    )

                }

            }

        }
    }

    private fun datePicker(context: Context) {
        val datePicker = DatePickerDialog(
            context, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]
        )

        /* datePicker.datePicker.minDate = System.currentTimeMillis() - 1000*/

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
}
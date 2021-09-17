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
import androidx.appcompat.app.AppCompatActivity
import com.puppypedia.R
import com.puppypedia.common_adapters.DogsAdapter
import com.puppypedia.databinding.ActivityAddRemainderBinding
import com.puppypedia.databinding.DialogAppointmentBinding
import com.puppypedia.model.DogsModel
import com.puppypedia.utils.helper.AppUtils
import java.util.*
import kotlin.collections.ArrayList

class AddRemainderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRemainderBinding
    private lateinit var dogsAdapter: DogsAdapter

    private val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var date: DatePickerDialog.OnDateSetListener
    private lateinit var time: TimePickerDialog.OnTimeSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRemainderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.tvTitle.text = getString(R.string.add_remainder)

        clicksHandle()

        val arrayList = ArrayList<DogsModel>()
        arrayList.add(DogsModel(R.drawable.dog_profile, "Rony", true))
        arrayList.add(DogsModel(R.drawable.dog_img, "Rocky", false))

        /* dogsAdapter = DogsAdapter(requireContext(), arrayList)
         binding.rvDogs.adapter = dogsAdapter*/
    }

    private fun clicksHandle() {
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSubmit.setOnClickListener {
            appointmentDialog()
        }

        binding.edtDate.setOnClickListener {
            date =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    myCalendar[Calendar.YEAR] = year
                    myCalendar[Calendar.MONTH] = monthOfYear
                    myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                    binding.edtDate.setText(
                        AppUtils.dateInString(
                        myCalendar.timeInMillis,
                        "dd-MMM-yyyy"
                    ))
                }

            datePicker(this)
        }

        binding.edtTime.setOnClickListener {
            time = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                myCalendar[Calendar.HOUR_OF_DAY] = hour
                myCalendar[Calendar.MINUTE] = minute

                binding.edtTime.setText(
                    AppUtils.dateInString(
                        myCalendar.timeInMillis,
                        "hh:mm a"
                    )
                )


            }

            timePicker(this)
        }
    }

    private fun appointmentDialog() {
        val dialogBinding = DialogAppointmentBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.show()


        dialogBinding.btnDone.setOnClickListener {
            finish()
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
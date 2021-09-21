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
import com.puppypedia.common_adapters.DogsAdapter
import com.puppypedia.model.DogsModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.auth.forgotpassword.ForgotPasswordResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.AppUtils
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.ValidationsClass
import kotlinx.android.synthetic.main.activity_add_remainder.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import java.util.*
import kotlin.collections.ArrayList

class AddRemainderActivity : AppCompatActivity(), Observer<RestObservable> {
    private lateinit var mValidationClass: ValidationsClass
    private lateinit var dogsAdapter: DogsAdapter
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    private val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var date: DatePickerDialog.OnDateSetListener
    private lateinit var time: TimePickerDialog.OnTimeSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remainder)
        mValidationClass = ValidationsClass.getInstance()
        tb.tv_title.text = getString(R.string.add_remainder)

        clicksHandle()

        val arrayList = ArrayList<DogsModel>()
        arrayList.add(DogsModel(R.drawable.dog_profile, "Rony", true))
        arrayList.add(DogsModel(R.drawable.dog_img, "Rocky", false))

        /* dogsAdapter = DogsAdapter(this, arrayList)
         binding.rvDogs.adapter = dogsAdapter*/
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }

        btnSubmit.setOnClickListener {
            appointmentDialog()
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
                            "dd-MMM-yyyy"
                        )
                    )
                }

            datePicker(this)
        }

        edtTime.setOnClickListener {
            time = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                myCalendar[Calendar.HOUR_OF_DAY] = hour
                myCalendar[Calendar.MINUTE] = minute

                edtTime.setText(
                    AppUtils.dateInString(
                        myCalendar.timeInMillis,
                        "hh:mm a"
                    )
                )


            }

            timePicker(this)
        }
    }

    fun appointmentDialog() {
        val dialog = Dialog(this)
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
        dialog.show()

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

/*
    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        var check = false
        if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_name))
        else if (!mValidationClass.isValidEmail(email))
            Helper.showErrorAlert(this, resources.getString(R.string.error_validemail))
        else
            check = true
        return check
    }
*/

/*    fun apihome() {
        if (isValid()) {
            val email = etEmail.text.toString().trim()
            val map = HashMap<String, String>()
            map["email"] = email
            viewModel.addReminderApi(this, true,map)
            viewModel.mResponse.observe(this, this)
        }
    }*/

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is ForgotPasswordResponse) {
                    val aboutResponse: ForgotPasswordResponse = it.data
                    if (aboutResponse.code == Constants.success_code) {
                        finish()
                        // finishAffinity()
                    } else {
                        Helper.showErrorAlert(this, aboutResponse.code as String)
                    }
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
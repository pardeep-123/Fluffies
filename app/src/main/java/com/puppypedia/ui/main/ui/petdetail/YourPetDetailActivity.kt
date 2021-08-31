package com.puppypedia.ui.main.ui.petdetail

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.puppypedia.R
import com.puppypedia.databinding.ActivityYourPetDetailBinding
import com.puppypedia.ui.main.ui.home.HomeActivity
import com.puppypedia.utils.helper.ImagePickerUtility


class YourPetDetailActivity : ImagePickerUtility() {

    lateinit var binding: ActivityYourPetDetailBinding

    override fun selectedImage(imagePath: String?, code: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourPetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tb.tvTitle.text = getString(R.string.your_pet_detail)
        clicksHandle()

        setSpinnerAge()
        setSpinnerGender()
        setSpinnerWeight()
    }

    private fun clicksHandle() {
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivCamera.setOnClickListener {
            getImage(this, 0)
        }

        binding.btnPetDetail.setOnClickListener {
            dialogAddPet()

        }
    }

    fun dialogAddPet() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_new_pet)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.show()

        val btnAddNew = dialog.findViewById<Button>(R.id.Add)
        val btnLater = dialog.findViewById<Button>(R.id.later)

        btnAddNew.setOnClickListener {
            dialog.dismiss()
        }
        btnLater.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            dialog.dismiss()
            finishAffinity()
        }
    }

    private fun setSpinnerGender() {
        val arrayList = arrayListOf("Gender", "Male", "Female")

        val adapterGender = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        binding.spinnerGender.adapter = adapterGender

        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_semibold)

            }

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
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_semibold)

            }

        }
    }

    private fun setSpinnerWeight() {
        val arrayList = arrayListOf("Weight", "1 lbs", "2 lbs")

        val adapterWeight = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        binding.spinnerWeight.adapter = adapterWeight

        binding.spinnerWeight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_semibold)

            }

        }
    }
}
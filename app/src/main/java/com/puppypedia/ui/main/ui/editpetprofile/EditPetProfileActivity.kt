package com.puppypedia.ui.main.ui.editpetprofile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
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
import com.puppypedia.R
import com.puppypedia.databinding.ActivityEditPetProfileBinding
import com.puppypedia.databinding.DialogPetProfileUpdateBinding
import com.puppypedia.utils.helper.ImagePickerUtility

class EditPetProfileActivity : ImagePickerUtility() {

    lateinit var binding: ActivityEditPetProfileBinding


    override fun selectedImage(imagePath: String?, code: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPetProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clicksHandle()

        setSpinnerGender()
        setSpinnerAge()
        setSpinnerWeight()
    }

    private fun clicksHandle() {
        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.tb.tvTitle.text = getString(R.string.edit_pet_profile)


        binding.btnUpdate.setOnClickListener {
            updateSuccessfully()
        }

        binding.rivPet.setOnClickListener {
            getImage(this, 0)
        }
    }

    private fun updateSuccessfully() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = DialogPetProfileUpdateBinding.inflate(layoutInflater)
        dialog.setContentView(view.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.show()

        val done = dialog.findViewById<Button>(R.id.done)
        done.setOnClickListener {
            finish()
            dialog.dismiss()
        }
    }


    private fun setSpinnerGender() {
        val arrayList = arrayListOf("Gender", "Male", "Female")

        val adapterGender = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        binding.spinnerGender.adapter = adapterGender

        binding.spinnerGender.setSelection(1)

        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val view = (parent?.getChildAt(0) as View)
                val tvSpinner = view.findViewById<TextView>(R.id.tvSpinner)
                /*tvSpinner.setPadding(0, 0, 0, 0)*/

                if (pos == 0) {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@EditPetProfileActivity,
                            R.color.lightGrayA3A3A3
                        )
                    )
                } else {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@EditPetProfileActivity,
                            R.color.black
                        )
                    )

                }

            }

        }
    }

    private fun setSpinnerAge() {
        val arrayList = arrayListOf("Age", "1 yr", "2 yr")

        val adapterAge = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        binding.spinnerAge.adapter = adapterAge

        binding.spinnerAge.setSelection(1)

        binding.spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val view = (parent?.getChildAt(0) as View)
                val tvSpinner = view.findViewById<TextView>(R.id.tvSpinner)
                /*tvSpinner.setPadding(0, 0, 0, 0)*/

                if (pos == 0) {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@EditPetProfileActivity,
                            R.color.lightGrayA3A3A3
                        )
                    )
                } else {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@EditPetProfileActivity,
                            R.color.black
                        )
                    )

                }

            }

        }
    }

    private fun setSpinnerWeight() {
        val arrayList = arrayListOf("Weight", "1 lbs", "2 lbs")

        val adapterWeight = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        binding.spinnerWeight.adapter = adapterWeight

        binding.spinnerWeight.setSelection(1)

        binding.spinnerWeight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val view = (parent?.getChildAt(0) as View)
                val tvSpinner = view.findViewById<TextView>(R.id.tvSpinner)
                /*tvSpinner.setPadding(0, 0, 0, 0)*/

                if (pos == 0) {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@EditPetProfileActivity,
                            R.color.lightGrayA3A3A3
                        )
                    )
                } else {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@EditPetProfileActivity,
                            R.color.black
                        )
                    )

                }

            }

        }
    }
}
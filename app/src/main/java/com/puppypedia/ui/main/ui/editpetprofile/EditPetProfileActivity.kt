package com.puppypedia.ui.main.ui.editpetprofile

import android.app.Dialog
import android.content.Context
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
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.puppypedia.R
import com.puppypedia.databinding.DialogPetProfileUpdateBinding
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.utils.helper.ImagePickerUtility
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import kotlinx.android.synthetic.main.activity_edit_pet_profile.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*

class EditPetProfileActivity : ImagePickerUtility() {
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass
    var firstimage = ""
    lateinit var context: Context
    private var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
    var check = false
    override fun selectedImage(imagePath: String?, code: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pet_profile)
        context = this
        clicksHandle()

        setSpinnerGender()
        setSpinnerAge()
        // setSpinnerWeight()
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.edit_pet_profile)


        btnUpdate.setOnClickListener {
            updateSuccessfully()
        }

        rivPet.setOnClickListener {
            selectImage()
        }
    }

    private fun selectImage() {
        Album.image(this).singleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(this).title(getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                Glide.with(context).load(result[0].path).into(rivPet)
                firstimage = result[0].path
            }.onCancel {
            }.start()
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
        spinnerGender.adapter = adapterGender

        spinnerGender.setSelection(1)

        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        spinnerAge.adapter = adapterAge

        spinnerAge.setSelection(1)

        spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
    /*override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is EditPetResponse) {
                    val aboutResponse: EditPetResponse = it.data
                *//*    SharedPrefUtil.getInstance().saveAuthToken(aboutResponse.body.authKey)
                    SharedPrefUtil.getInstance().saveImage(aboutResponse.body.image)
                    SharedPrefUtil.getInstance().saveUserId(aboutResponse.body.id.toString())
                    SharedPrefUtil.getInstance().saveEmail(aboutResponse.body.email)
                    SharedPrefUtil.getInstance().saveName(aboutResponse.body.name)*//*
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
    }*/

/*    private fun setSpinnerWeight() {
        val arrayList = arrayListOf("Weight", "1 lbs", "2 lbs")

        val adapterWeight = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        spinnerWeight.adapter = adapterWeight

        spinnerWeight.setSelection(1)

        spinnerWeight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                *//*tvSpinner.setPadding(0, 0, 0, 0)*//*

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
    }*/
}
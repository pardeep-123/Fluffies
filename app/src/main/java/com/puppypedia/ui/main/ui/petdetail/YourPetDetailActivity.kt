package com.puppypedia.ui.main.ui.petdetail

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R

import com.puppypedia.restApi.RestObservable

import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.home.HomeActivity
import com.puppypedia.utils.helper.ImagePickerUtility

import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.api.widget.Widget
import com.zxy.tiny.Tiny
import kotlinx.android.synthetic.main.activity_your_pet_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class YourPetDetailActivity : ImagePickerUtility(), Observer<RestObservable> {

    private lateinit var mValidationClass: ValidationsClass

    var gender = ""
    var image = ""
    var age = 0
    var weight = 0
    val ageArrayList = arrayListOf("Age", "1 yr", "2 yr")
    val weightArrayList = arrayListOf("Weight", "1 lbs", "2 lbs")

    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun selectedImage(imagePath: String?, code: Int) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_pet_detail)
        mValidationClass = ValidationsClass.getInstance()
        SharedPrefUtil.init(this)
        tb.tv_title.text = getString(R.string.your_pet_detail)
        clicksHandle()
        setSpinnerAge()
        setSpinnerGender()
        setSpinnerWeight()
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }

        ivCamera.setOnClickListener {
            callImagePicker(this)
        }

        btSubmit.setOnClickListener {

            callSignupApi()
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
        spinnerGender.adapter = adapterGender

        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                gender = pos.toString()
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
                /*tvSpinner.setPadding(0, 0, 0, 0)*/
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_semibold
                )

            }

        }
    }

    private fun setSpinnerAge() {


        val adapterAge = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, ageArrayList)
        spinnerAge.adapter = adapterAge

        spinnerAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                age = pos
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
                /*tvSpinner.setPadding(0, 0, 0, 0)*/
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_semibold
                )

            }

        }
    }

    private fun setSpinnerWeight() {

        val adapterWeight =
            ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, weightArrayList)
        spinnerWeight.adapter = adapterWeight

        spinnerWeight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                weight = pos
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
                /*tvSpinner.setPadding(0, 0, 0, 0)*/
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_semibold
                )

            }

        }
    }


    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        val about = etAbout.text.toString().trim()
        val breed = etbreed.text.toString().trim()

        var check = false


        /*if (!mValidationClass.isNetworkConnected)
            Helper.showErrorAlert(this, resources.getString(R.string.no_internet))
        else */
        if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_name))
        else if (gender == "0")
            Helper.showErrorAlert(this, "Please select gender")
        else if (age == 0)
            Helper.showErrorAlert(this, "Please select age")
        else if (weight == 0)
            Helper.showErrorAlert(this, "Please select weight")
        else if (mValidationClass.checkStringNull(about))
            Helper.showErrorAlert(this, resources.getString(R.string.about))
        else if (mValidationClass.checkStringNull(breed))
            Helper.showErrorAlert(this, resources.getString(R.string.error_breed))
        else
            check = true
        return check
    }

    private fun callSignupApi() {
        if (isValid()) {
            val name = etName.text.toString().trim()
            val breed = etbreed.text.toString().trim()
            val about = etAbout.text.toString().trim()
            val map = HashMap<String, String>()
            map["name"] = name
            map["gender"] = if (gender.equals("1")) "0" else "1"
            map["age"] = ageArrayList[age]
            map["weight"] = weightArrayList[weight]
            map["about"] = about
            // map["breed"] = breed
            map["image"] = "hgjjjjjjg"
            viewModel.apiAddPuppy(this, true, map)
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is PetDetailResponse) {
                    val registerResponse: PetDetailResponse = it.data
                    if (registerResponse.code == Constants.success_code) {
                        /*  SharedPrefUtil.getInstance().saveImage(registerResponse.body.image)
                          SharedPrefUtil.getInstance().saveUserId(registerResponse.body.id.toString())
                          SharedPrefUtil.getInstance().saveEmail(registerResponse.body.about)
                          SharedPrefUtil.getInstance().saveName(registerResponse.body.name)
                          SharedPrefUtil.getInstance().saveName(registerResponse.body.breed)

                          SharedPrefUtil.getInstance().isLogin = true*/
/*
                        MyApplication.instance!!.setString(Constants.AuthKey, registerResponse.body.authKey
                        )*/
                        dialogAddPet()

                    } else {
                        Helper.showErrorAlert(this, registerResponse.code.toString())
                    }
                }
            }
            it.status == Status.ERROR -> {
                if (it.data != null) {
                    Helper.showErrorAlert(this, it.data.toString())
                } else {
                    Helper.showErrorAlert(this, it.error.toString())
                }
            }

        }
    }

    fun multipartImageGet(): MultipartBody.Part {
        val imageFile: MultipartBody.Part
        val options = Tiny.FileCompressOptions()
        val result =
            Tiny.getInstance().source(image).asFile().withOptions(options)
                .compressSync()
        val fileReqBody = File(result.outfile).asRequestBody("image/*".toMediaTypeOrNull())
        imageFile =
            MultipartBody.Part.createFormData(
                "image",
                System.currentTimeMillis().toString() + ".jpg",
                fileReqBody
            )
        return imageFile

    }

    private fun callImagePicker(context: Context) {
        Album.image(context)
            .singleChoice()
            .camera(true)
            .columnCount(4)
            .widget(
                Widget.newDarkBuilder(context)
                    .title(context.getString(R.string.app_name))
                    .build()
            )
            .onResult { result ->
                image = result[0].path
            }
            .onCancel {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                    .show()
            }
            .start()
    }


}



package com.fluffies.ui.main.ui.petdetail

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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.fluffies.R
import com.fluffies.restApi.RestObservable
import com.fluffies.ui.commomModel.ImageUploadResponse
import com.fluffies.ui.main.ui.AllViewModel
import com.fluffies.ui.main.ui.home.HomeActivity
import com.fluffies.utils.helper.CommonMethods
import com.fluffies.utils.helper.others.Constants
import com.fluffies.utils.helper.others.Helper
import com.fluffies.utils.helper.others.SharedPrefUtil
import com.fluffies.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
//import com.zxy.tiny.Tiny
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_your_pet_detail.*
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class YourPetDetailActivity : AppCompatActivity(), Observer<RestObservable> {
    private lateinit var mValidationClass: ValidationsClass
    private var mAlbumFiles: java.util.ArrayList<AlbumFile> = java.util.ArrayList()
    var gender = ""
    var petType = ""
    var image = ""
    var age = 0
    var auth = ""
    var weight = 0
    var onePetAdded = false
    val ageArrayList = ArrayList<String>()

    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_pet_detail)
        CommonMethods.scrollEditText(etAbout)
        mValidationClass = ValidationsClass.getInstance()
        if (intent.hasExtra("auth")) {
            auth = intent.getStringExtra("auth").toString()
        }
        if (intent.hasExtra("add")) {
            onePetAdded = true
        }
        SharedPrefUtil.init(this)
        ageArrayList.add("Age")

        for (i in 1 until 21) {
            ageArrayList.add(i.toString() + "yr")
        }
        tb.tv_title.text = getString(R.string.your_pet_detail)
        clicksHandle()
        //setSpinnerAge()
        setSpinnerGender()

        // call type spinner
        setSpinnerPetType()
        //  setSpinnerWeight()
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        ivCamera.setOnClickListener {
            //   callImagePicker(this)
        }
        btSubmit.setOnClickListener {
            callSignupApi()
        }

        ivCamera.setOnClickListener {
            mAlbumFiles = java.util.ArrayList()
            mAlbumFiles.clear()
            callImagePicker(this)
        }
    }

    private fun dialogAddPet() {
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
            etName.setText("")
            etbreed.setText("")
            etAge.setText("")
            etweight.setText("")
            etAbout.setText("")
            // age = 0
            gender = "0"
            image = ""
            spinnerGender.setSelection(0)
            //  spinnerAge.setSelection(0)
            Glide.with(this).load(R.drawable.pet_pic).into(ivPetProfile)
            dialog.dismiss()
        }
        btnLater.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            dialog.dismiss()
            //finishAffinity()
        }
    }

    private fun setSpinnerGender() {
        val arrayList = arrayListOf("Gender", "Male", "Female")

        val adapterGender = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        spinnerGender.adapter = adapterGender

        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                gender = pos.toString()
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
                if (pos == 0) {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@YourPetDetailActivity,
                            R.color.lightGrayA3A3A3
                        )
                    )
                } else {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@YourPetDetailActivity,
                            R.color.black
                        )
                    )

                }

                /*tvSpinner.setPadding(0, 0, 0, 0)*/
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_regular
                )
            }
        }
    }

    // spinner for pet type
    private fun setSpinnerPetType() {
        val arrayList = arrayListOf("Pet Type", "Dog", "Cat")

        val adapterGender = ArrayAdapter(this, R.layout.item_spinner, R.id.tvSpinner, arrayList)
        spinnerType.adapter = adapterGender

        spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                petType = pos.toString()
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
                if (pos == 0) {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@YourPetDetailActivity,
                            R.color.lightGrayA3A3A3
                        )
                    )
                } else {
                    tvSpinner.setTextColor(
                        ContextCompat.getColor(
                            this@YourPetDetailActivity,
                            R.color.black
                        )
                    )

                }

                /*tvSpinner.setPadding(0, 0, 0, 0)*/
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@YourPetDetailActivity, R.font.opensans_regular
                )
            }
        }
    }

    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        val about = etAbout.text.toString().trim()
        val breed = etbreed.text.toString().trim()
        val weight = etweight.text.toString().trim()
        val age = etAge.text.toString().trim()
        var check = false

        if (mValidationClass.checkStringNull(image))
            Helper.showErrorAlert(this, "Please select your pet image")
        else if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_name))
        else if (gender == "0")
            Helper.showErrorAlert(this, "Please select gender")
        else if (petType == "0")
            Helper.showErrorAlert(this, "Please select pet Type")
        /*   else if (weight == 0)
            Helper.showErrorAlert(this, "Please select weight")*/
        else if (mValidationClass.checkStringNull(age))
            Helper.showErrorAlert(this, resources.getString(R.string.error_age))
//        else if (mValidationClass.checkStringNull(weight))
//            Helper.showErrorAlert(this, resources.getString(R.string.error_weight))
        else if (mValidationClass.checkStringNull(breed))
            Helper.showErrorAlert(this, resources.getString(R.string.error_breed))
        else if (mValidationClass.checkStringNull(about))
            Helper.showErrorAlert(this, resources.getString(R.string.about))
        else
            check = true
        return check
    }

    private fun callSignupApi() {
        if (isValid()) {
            val map = HashMap<String, RequestBody>()
            map["folder"] = mValidationClass.createPartFromString("pets")
            viewModel.imageUpload(this, true, map, multipartImageGet())
            viewModel.mResponse.observe(this, this)
        }
    }

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is PetDetailResponse) {
                    if (!onePetAdded) {
                        onePetAdded = true
                        SharedPrefUtil.getInstance().isLogin = true
                        SharedPrefUtil.getInstance().saveAuthToken(auth)
                    }
                    val registerResponse: PetDetailResponse = it.data
                    if (registerResponse.code == Constants.success_code) {
                        if (intent.hasExtra("add")) {
                            setResult(RESULT_OK)
                            finish()
                        } else {
                            dialogAddPet()
                        }

                    } else {
                        Helper.showErrorAlert(this, registerResponse.code.toString())
                    }
                }
                if (it.data is ImageUploadResponse) {
                    val name = etName.text.toString().trim()
                    val breed = etbreed.text.toString().trim()
                    val weight = etweight.text.toString().trim()
                    val age = etAge.text.toString().trim()
                    val about = etAbout.text.toString().trim()
                    val map = HashMap<String, String>()
                    map["name"] = name
                    map["gender"] = if (gender == "1") "0" else "1"
                    map["type"] = petType
                    // map["age"] = ageArrayList[age]
                    map["weight"] = weight
                    map["age"] = age
                    map["about"] = about
                    map["breed"] = breed
                    map["image"] = it.data.body[0].image
                    viewModel.apiAddPuppy(this, true, map)
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
//        val options = Tiny.FileCompressOptions()
//        val result = Tiny.getInstance().source(image).asFile().withOptions(options)
//            .compressSync()
        val fileReqBody = File(image).asRequestBody("image/*".toMediaTypeOrNull())
        imageFile =
            MultipartBody.Part.createFormData(
                "image", System.currentTimeMillis().toString() + ".jpg",
                fileReqBody)

        return imageFile
    }

    private fun callImagePicker(context: Context) {
        Album.image(context).singleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(context).title(context.getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
                if (result.isNotEmpty()) {
                    Glide.with(this).load(result[0].path).into(ivPetProfile)
                    image = result[0].path
                }
            }
            .onCancel {
                // Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
            .start()
    }
}


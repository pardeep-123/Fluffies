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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.mypetprofile.PetProfileResponse
import com.puppypedia.utils.helper.CommonMethods
import com.puppypedia.utils.helper.others.Constants
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.SharedPrefUtil
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import com.zxy.tiny.Tiny
import kotlinx.android.synthetic.main.activity_edit_pet_profile.*
import kotlinx.android.synthetic.main.activity_edit_pet_profile.etAbout
import kotlinx.android.synthetic.main.activity_edit_pet_profile.etAge
import kotlinx.android.synthetic.main.activity_edit_pet_profile.etName
import kotlinx.android.synthetic.main.activity_edit_pet_profile.spinnerGender
import kotlinx.android.synthetic.main.activity_edit_pet_profile.tb
import kotlinx.android.synthetic.main.auth_toolbar.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class EditPetProfileActivity : AppCompatActivity(), Observer<RestObservable> {
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }
    private lateinit var mValidationClass: ValidationsClass
    var oldImage = ""
    var newImage = ""
    val ageArrayList = ArrayList<String>()
    var petType = 0
    // var age = 0
    var gender = 0
    var poz = 0
    var data: PetProfileResponse? = null
    lateinit var context: Context
    private var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
    var check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pet_profile)
        CommonMethods.scrollEditText(etAbout)
        context = this
        clicksHandle()
        mValidationClass = ValidationsClass.getInstance()
        ageArrayList.add("Age")
        for (i in 0 until 21) {
            ageArrayList.add((i + 1).toString() + "yr")
        }
        setSpinnerGender()
        //setSpinnerAge()
        etWeight.setSelection(etWeight.getText().length)
        etBreed.setSelection(etBreed.getText().length)
        etAbout.setSelection(etAbout.getText().length)


        data = (intent.getSerializableExtra("aboutResponse") as PetProfileResponse)
        // setSpinnerWeight()
        setSpinnerPetType()
        poz = intent.getStringExtra("selectedpos")!!.toInt()
        etName.setText(data!!.body[poz].name)
        etAbout.setText(data!!.body[poz].about)
        etBreed.setText(data!!.body[poz].breed)
        etAge.setText(data!!.body[poz].age.toString())
        etWeight.setText(data!!.body[poz].weight.toString())
        oldImage = data!!.body[poz].image
        Glide.with(context).load(Constants.IMAGE_URL + data!!.body[poz].image)
            .error(R.drawable.place_holder).into(rivPet)

        spinnerGender.setSelection(
            if (data!!.body[poz].gender == 0) {
                1
            } else {
                2
            }
        )

        // set type spinner value
        spinnerType.setSelection(
            if (data!!.body[poz].type ==1) {
                1

            } else {
                2
            }
        )
//        spinnerAge.setSelection(data!!.body[poz].age)

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
                petType = pos
                val v = (parent?.getChildAt(0) as View)
                val tvSpinner = v.findViewById<TextView>(R.id.tvSpinner)
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
                            R.color.black))

                }

                /*tvSpinner.setPadding(0, 0, 0, 0)*/
                tvSpinner.typeface = ResourcesCompat.getFont(
                    this@EditPetProfileActivity, R.font.opensans_regular
                )
            }
        }
    }

    private fun clicksHandle() {
        tb.iv_back.setOnClickListener {
            onBackPressed()
        }
        tb.tv_title.text = getString(R.string.edit_pet_profile)
        btnUpdate.setOnClickListener {
            apiEditPetProfile()
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
                newImage = result[0].path
            }.onCancel {
            }.start()
    }


    fun dialogEDitPet() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_pet_profile_update)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.show()

        val btnDone = dialog.findViewById<AppCompatButton>(R.id.btnDone)

        btnDone.setOnClickListener {
            finish()
            dialog.dismiss()

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
                gender = pos
            }
        }
    }

    private fun isValid(): Boolean {
        val name = etName.text.toString().trim()
        val about = etAbout.text.toString().trim()
        val weight = etWeight.text.toString().trim()
        val breed = etBreed.text.toString().trim()
        val age = etAge.text.toString().trim()
        var check = false
        if (mValidationClass.checkStringNull(name))
            Helper.showErrorAlert(this, resources.getString(R.string.error_name))
        else if (mValidationClass.checkStringNull(about))
            Helper.showErrorAlert(this, resources.getString(R.string.about))
//        else if (mValidationClass.checkStringNull(weight))
//            Helper.showErrorAlert(this, resources.getString(R.string.error_weight))
        else if (petType == 0)
            Helper.showErrorAlert(this, resources.getString(R.string.selectType))
        else if (mValidationClass.checkStringNull(breed))
            Helper.showErrorAlert(this, resources.getString(R.string.error_breed))
        else if (mValidationClass.checkStringNull(age))
            Helper.showErrorAlert(this, resources.getString(R.string.error_age))
        else
            check = true
        return check
    }

    private fun apiEditPetProfile() {
        if (isValid()) {
            if (newImage != "") {
                val map = HashMap<String, RequestBody>()
                map["folder"] = mValidationClass.createPartFromString("pets")
                viewModel.imageUpload(this, true, map, multipartImageGet())
                viewModel.mResponse.observe(this, this)
            } else {
                val name = etName.text.toString().trim()
                val about = etAbout.text.toString().trim()
                val weight = etWeight.text.toString().trim()
                val breed = etBreed.text.toString().trim()
                val age = etAge.text.toString().trim()
                val map = HashMap<String, String>()
                map["petid"] = data!!.body[poz].id.toString()
                map["name"] = name
                map["weight"] = weight
                map["about"] = about
                map["breed"] = breed
                map["age"] = age
                //  map["age"] = age.toString()
                map["gender"] = gender.toString()
                map["type"] = petType.toString()

                viewModel.editPetProfileApi(this, true, map)
                viewModel.mResponse.observe(this, this)
            }


        }
    }


    fun multipartImageGet(): MultipartBody.Part {
        val imageFile: MultipartBody.Part
        val options = Tiny.FileCompressOptions()
        val result =
            Tiny.getInstance().source(newImage).asFile().withOptions(options)
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

    override fun onChanged(it: RestObservable?) {
        when {
            it!!.status == Status.SUCCESS -> {
                if (it.data is EditPetResponse) {
                    val aboutResponse: EditPetResponse = it.data
                    //SharedPrefUtil.getInstance().saveAuthToken(aboutResponse.body.authKey)
                    SharedPrefUtil.getInstance().saveImage(aboutResponse.body.image)
                    SharedPrefUtil.getInstance().saveUserId(aboutResponse.body.id.toString())
                    SharedPrefUtil.getInstance().saveEmail(aboutResponse.body.breed)
                    SharedPrefUtil.getInstance().saveName(aboutResponse.body.name)
                    SharedPrefUtil.getInstance().saveName(aboutResponse.body.about)
                    SharedPrefUtil.getInstance().saveName(aboutResponse.body.weight.toString())
                    SharedPrefUtil.getInstance().saveName(aboutResponse.body.gender.toString())
                    SharedPrefUtil.getInstance().saveName(aboutResponse.body.age.toString())
                    dialogEDitPet()
                }
                if (it.data is ImageUploadResponse) {
                    val imageResponse: ImageUploadResponse = it.data

                    val name = etName.text.toString().trim()
                    val about = etAbout.text.toString().trim()
                    val weight = etWeight.text.toString().trim()
                    val breed = etBreed.text.toString().trim()
                    val age = etAge.text.toString().trim()
                    val map = HashMap<String, String>()
                    map["petid"] = data!!.body[poz].id.toString()
                    map["name"] = name
                    map["weight"] = weight
                    map["about"] = about
                    map["breed"] = breed
                    map["age"] = age
                    //  map["age"] = age.toString()
                    map["gender"] = gender.toString()
                    map["image"] = imageResponse.body[0].image

                    viewModel.editPetProfileApi(this, true, map)
                    viewModel.mResponse.observe(this, this)
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
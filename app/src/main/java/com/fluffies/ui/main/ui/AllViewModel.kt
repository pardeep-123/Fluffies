package com.fluffies.ui.main.ui

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fluffies.restApi.RestObservable
import com.fluffies.utils.helper.MyApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.*

class AllViewModel : ViewModel() {
    private val TAG = AllViewModel::class.java.name
    val restApiInterface = MyApplication.instance!!.provideAuthservice()
    var mResponse: MutableLiveData<RestObservable> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun imageUpload(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, RequestBody>,
        multipartImageGet: MultipartBody.Part
    ) {
        restApiInterface.fileUpload(map, multipartImageGet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // Add Health Details
    @SuppressLint("CheckResult")
    fun addHealthDetails(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, RequestBody>,
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.addHealthDetail(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // Edit Health Details
    @SuppressLint("CheckResult")
    fun editHealthDetail(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, RequestBody>,
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.editHealthDetail(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // add pictures of puppy

    @SuppressLint("CheckResult")
    fun addPicture(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.addPicture(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // add life event
    @SuppressLint("CheckResult")
    fun addLifeEvent(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.addLifeEvent(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // to delete life events

    @SuppressLint("CheckResult")
    fun delLifeEvent(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, Int>
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.delLifeEvent(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    // get picture list
    @SuppressLint("CheckResult")
    fun getPicture(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, RequestBody>,
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.getPicture(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // get Lifevent list
    @SuppressLint("CheckResult")
    fun getLifeEvent(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.getLifeEvent(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // get Background Images

    @SuppressLint("CheckResult")
    fun getBackground(
        activity: Activity,
        showLoader: Boolean
    ) {
        restApiInterface.getBackground()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun delPicture(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
//        multipartImageGet: MultipartBody.Part,
//        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.delPicture(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun apiAddPuppy(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
        // multipartImageGet: MultipartBody.Part
    ) {
        restApiInterface.addPuppy(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it!!) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun getAboutUsAPI(
        activity: Activity, showLoader: Boolean

    ) {
        restApiInterface.aboutUs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun getPrivacyPolicyAPI(
        activity: Activity, showLoader: Boolean
    ) {
        restApiInterface.privacypolicy()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun getTermsAPI(
        activity: Activity, showLoader: Boolean
    ) {
        restApiInterface.terms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun getNotificationListAPI(
        activity: Activity, showLoader: Boolean

    ) {
        restApiInterface.notiListing()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun apiNotiOnOff(activity: Activity, status: String, showLoader: Boolean) {
        restApiInterface.notiOnOff(status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun changePasswordApi(
        activity: Activity, showLoader: Boolean, map: HashMap<String, String>
    ) {
        restApiInterface.changePassword(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun getProfile(activity: Activity, showLoader: Boolean) {
        restApiInterface.apiProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun imageUploadmultile(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, RequestBody>,
        multipartImageGet: ArrayList<MultipartBody.Part?>
    ) {
        restApiInterface.fileUploadmultiple(map, multipartImageGet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun getPetProfile(activity: Activity, showLoader: Boolean) {
        restApiInterface.apiPetProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun apiPetData(
        activity: Activity,
        petId: String,
        showLoader: Boolean
    ) {
        restApiInterface.apiPetData(petId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun apiCategoryDetails(
        activity: Activity,
        petId: String,
        showLoader: Boolean
    ) {
        restApiInterface.apiCategoryDetails(petId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }
    @SuppressLint("CheckResult")
    fun getHomeDetails(activity: Activity, showLoader: Boolean) {
        restApiInterface.apiHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun editProfileApi(
        activity: Activity, showLoader: Boolean, map: HashMap<String, String>
    ) {
        restApiInterface.editProfile(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun editPetData(
        activity: Activity, showLoader: Boolean, map: HashMap<String, String>
    ) {
        restApiInterface.editPetPost(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun editUserProfileWithImageApi(
        activity: Activity, showLoader: Boolean, map: HashMap<String, RequestBody>,
        multipartImageGet: MultipartBody.Part

    ) {
        restApiInterface.editUserProfileWithImage(map, multipartImageGet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun getLogout(activity: Activity, showLoader: Boolean) {
        restApiInterface.apiLogout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun apiForgot(activity: Activity, showLoader: Boolean, map: HashMap<String, String>) {
        restApiInterface.apiforgotPassword(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun editPetProfileApi(
        activity: Activity, showLoader: Boolean, map: HashMap<String, String>
    ) {
        restApiInterface.editPetProfile(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun editPetProfileWithImageApi(
        activity: Activity, showLoader: Boolean, map: HashMap<String, RequestBody>,
        multipartImageGet: MultipartBody.Part

    ) {
        restApiInterface.editPetProfileWithImage(map, multipartImageGet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun addPetWeightApi(
        activity: Activity, showLoader: Boolean, map: HashMap<String, String>
    ) {
        restApiInterface.apiAddPetWeight(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun getWeightApi(
        activity: Activity, id: String, showLoader: Boolean
    ) {
        restApiInterface.apiGetPetWeight(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun apiDeletePet(
        activity: Activity, petid: String, post_id: String, showLoader: Boolean
    ) {
        restApiInterface.apideletePet(petid, post_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun apideleteHealthDetail(
        activity: Activity, petid: String, health_id: String, showLoader: Boolean
    ) {
        restApiInterface.apideleteHealthDetail(petid, health_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun apiWeightPet(
        activity: Activity, weightId: String, showLoader: Boolean
    ) {
        restApiInterface.apiWeightPet(weightId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun apiDeletePetImage(
        activity: Activity, petid: String, post_id: String, image_id: String, showLoader: Boolean
    ) {
        restApiInterface.apideletePetImages(petid, post_id, image_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun addPetChartApi(
        activity: Activity, datetype: String, petId: String, showLoader: Boolean
    ) {
        restApiInterface.apiPetChart(datetype, petId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun addReminderApi(
        activity: Activity, showLoader: Boolean, map: HashMap<String, String>
    ) {
        restApiInterface.apiAddReminder(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun getReminderApi(
        activity: Activity, datetime: String, showLoader: Boolean
    ) {
        restApiInterface.apiGetReminders(datetime)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    @SuppressLint("CheckResult")
    fun getHealthDetail(
        activity: Activity, showLoader: Boolean
    ) {
        restApiInterface.getHealthDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }



    @SuppressLint("CheckResult")
    fun reminderOnOffApi(
        activity: Activity, isRemind: String, reminderid: String, showLoader: Boolean
    ) {
        restApiInterface.apiReminderOnOff(isRemind, reminderid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }


    @SuppressLint("CheckResult")
    fun apiAddPuppyDescription(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
        // multipartImageGet: MultipartBody.Part
    ) {
        restApiInterface.addPuppyDescription(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it!!) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    // to delete the reminders
    @SuppressLint("CheckResult")
    fun deletePetReminder(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
        // multipartImageGet: MultipartBody.Part
    ) {
        restApiInterface.deletePetReminder(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it!!) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

}
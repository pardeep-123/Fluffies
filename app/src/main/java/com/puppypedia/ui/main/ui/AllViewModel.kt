package com.puppypedia.ui.main.ui

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.utils.helper.MyApplication
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
        multipartImageGet: MultipartBody.Part,
        multipartImageGet1: MultipartBody.Part
    ) {
        restApiInterface.addHealthDetail(map, multipartImageGet,multipartImageGet1)
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
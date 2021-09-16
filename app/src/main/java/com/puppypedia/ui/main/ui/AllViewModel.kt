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
        activity: Activity, showLoader: Boolean, map: HashMap<String, String>
    ) {
        restApiInterface.apiGetPetWeight(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

}
package com.puppypedia.ui.auth

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

class AuthViewModel : ViewModel() {
    private val TAG = AuthViewModel::class.java.name
    val restApiInterface = MyApplication.instance!!.provideAuthservice()
    var mResponse: MutableLiveData<RestObservable> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun signUpApi(
        activity: Activity,
        showLoader: Boolean,
        map: HashMap<String, String>,
    ) {
        restApiInterface.signUp(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

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
    fun loginApi(activity: Activity, showLoader: Boolean, map: HashMap<String, String>) {

        restApiInterface.login(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
            .subscribe(
                { mResponse.value = RestObservable.success(it) },
                { mResponse.value = RestObservable.error(activity, it) }
            )
    }

    /*



      @SuppressLint("CheckResult")
      fun apiChangePassword(activity: Activity, showLoader: Boolean, map: HashMap<String, String>) {
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
      fun apiForgotPassword(activity: Activity, showLoader: Boolean, map: HashMap<String, String>) {
          restApiInterface.forgotPassword(map)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
              .subscribe(
                  { mResponse.value = RestObservable.success(it) },
                  { mResponse.value = RestObservable.error(activity, it) }
              )
      }

      @SuppressLint("CheckResult")
      fun logoutApi(activity: Activity, showLoader: Boolean) {
          restApiInterface.logout()
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
              .subscribe(
                  { mResponse.value = RestObservable.success(it) },
                  { mResponse.value = RestObservable.error(activity, it) }
              )
      }


      @SuppressLint("CheckResult")
      fun profileApi(activity: Activity, showLoader: Boolean) {
          restApiInterface.profile()
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .doOnSubscribe { mResponse.value = RestObservable.loading(activity, showLoader) }
              .subscribe(
                  { mResponse.value = RestObservable.success(it) },
                  { mResponse.value = RestObservable.error(activity, it) }
              )
      }*/
}
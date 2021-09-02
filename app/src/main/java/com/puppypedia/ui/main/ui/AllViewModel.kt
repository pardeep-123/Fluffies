package com.puppypedia.ui.main.ui

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.utils.helper.MyApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllViewModel : ViewModel() {
    private val TAG = AllViewModel::class.java.name
    val restApiInterface = MyApplication.instance!!.provideAuthservice()
    var mResponse: MutableLiveData<RestObservable> = MutableLiveData()

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

}
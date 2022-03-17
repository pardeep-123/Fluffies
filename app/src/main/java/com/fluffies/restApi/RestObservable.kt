package com.fluffies.restApi

import android.annotation.TargetApi
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException

import com.last.manager.restApi.Status

import com.fluffies.R
import com.fluffies.ui.auth.login.LoginActivity
import com.fluffies.utils.helper.others.Helper
import okhttp3.ResponseBody
import java.io.IOException

class RestObservable(
    val status: Status,
    val data: Any?,
    val error: Any?
) {
    companion object {

        private var mProgressDialog: CustomProgress? = null

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        fun loading(activity: Activity, showLoader: Boolean): RestObservable {
            if (showLoader) {
                mProgressDialog = CustomProgress(activity)
                mProgressDialog!!.show()
            }
            Log.e("REST", "Loading")
            return RestObservable(
                Status.LOADING,
                null,
                null
            )
        }

        fun success(@NonNull data: Any): RestObservable {
            if (mProgressDialog != null && mProgressDialog!!.isShowing)
                mProgressDialog!!.dismiss()
            Log.e("REST", "Success")
            return RestObservable(
                Status.SUCCESS,
                data,
                null
            )


        }

        fun error(activity: Activity, @NonNull error: Throwable): RestObservable {
            Log.e("REST", "Error  ------  " + error.localizedMessage)
            if (mProgressDialog != null && mProgressDialog!!.isShowing)
                mProgressDialog!!.dismiss()

            try {
                // We had non-200 http error
                if (error is HttpException) {
                    val httpException = error
                    val response = httpException.response()
                    val errorMessage =
                        callErrorMethod(response.errorBody())
                    Log.i(ContentValues.TAG, error.message() + " // / " + errorMessage)
                    if (response.code() == 400) {
                        Helper.showErrorAlert(activity, errorMessage)
                    } else if (response.code() == 401) {
                        val intent = Intent(activity, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        activity.startActivity(intent)
                        activity.finish()
                        Helper.showToast(activity, R.string.session_expired)
                    } else {
                        Helper.showErrorAlert(activity, errorMessage)
                    }

                    return RestObservable(
                        Status.ERROR,
                        null,
                        errorMessage
                    )
                }
                // A network error happened
                if (error is IOException) {
                    Log.i(ContentValues.TAG, error.message + " / " + error.javaClass)
                    Helper.showErrorAlert(
                        activity,
                        activity.getString(R.string.unable_to_connect_server)
                    )

                    return RestObservable(
                        Status.ERROR,
                        null,
                        error
                    )
                }

                Log.i(ContentValues.TAG, error.message + " / " + error.javaClass)
            } catch (e: Exception) {
                Log.i(ContentValues.TAG, e.message.toString())
                return RestObservable(
                    Status.ERROR,
                    null,
                    error
                )
            }

            return RestObservable(
                Status.ERROR,
                null,
                error
            )
        }

        private fun callErrorMethod(responseBody: ResponseBody?): String {

            val converter = ServiceGenerator.getRetrofit()
                .responseBodyConverter<RestError>(
                    RestError::class.java,
                    arrayOfNulls<Annotation>(0)
                )
            return try {
                val errorResponse = converter.convert(responseBody!!)
                val errorMessage = errorResponse!!.msg
                errorMessage!!
            } catch (e: IOException) {
                e.toString()
            }

        }

    }
}


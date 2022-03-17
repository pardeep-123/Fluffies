package com.fluffies.restApi

import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.fluffies.utils.helper.MyApplication
import com.fluffies.utils.helper.others.Constants
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    private val httpClient = OkHttpClient.Builder()
        .readTimeout((5 * 60).toLong(), TimeUnit.SECONDS)
        .connectTimeout((5 * 60).toLong(), TimeUnit.SECONDS)
        .writeTimeout((5 * 60).toLong(), TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(provideHeaderInterceptor())
        .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)).build()

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    private val builder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @JvmStatic
    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = getRetrofit()
        return retrofit.create(serviceClass)
    }

    @JvmStatic
    fun getRetrofit(): Retrofit {
        return builder.client(httpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setPrettyPrinting().create()
                )
            )
            .build()
    }

    private fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request: Request
            if (!MyApplication.instance!!.getString(Constants.AuthKey).equals("")) {
                Log.d(
                    "TAG",
                    "auth_key ......." + MyApplication.instance!!.getString(Constants.AuthKey)
                )
                request = chain.request().newBuilder().header(
                    "auth_key", /*"Bearer "+*/
                    MyApplication.instance!!.getString(Constants.AuthKey).toString()
                ).build()
            } else {
                request = chain.request().newBuilder().build();
            }
            chain.proceed(request)
        }
    }
}
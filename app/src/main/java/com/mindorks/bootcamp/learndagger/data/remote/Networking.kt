package com.mindorks.bootcamp.learndagger.data.remote

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mindorks.bootcamp.learndagger.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {

    const val HEADER_API_KEY = "x-api-key"
    const val HEADER_ACCESS_TOKEN = "x-access-token"
    const val HEADER_USER_ID = "x-user-id"
    const   val TAG = "NETWORKING"


    private const val NETWORK_CALL_TIMEOUT = 60
    lateinit var API_KEY: String
    val gsonBuilder =  GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


    fun create(apiKey: String, baseUrl: String, cacheDir: File, cacheSize: Long): NetworkService {
        API_KEY = apiKey

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient().newBuilder()
                        .cache(Cache(cacheDir, cacheSize))
                        .addInterceptor(HttpLoggingInterceptor()
                                .apply {
                                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                    else HttpLoggingInterceptor.Level.NONE
                                })
                        .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .build())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkService::class.java)

    }

    fun offlineInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG,"offline Interceptor Called")
                var request = chain.request()
                if(!isNetworkConnected()){
                    val cacheControl  = CacheControl.Builder()
                            .maxStale(7,TimeUnit.DAYS)
                            .build()

                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .cacheControl(cacheControl)
                            .build()
                }

                return  chain.proceed(request)
            }

        }


    }

    fun networkInterceptor(): Interceptor {

        return object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                Log.d(TAG,"Network Interceptor Called")
                val response  = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                        .maxAge(5,TimeUnit.SECONDS)
                        .build()

                return  response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control",cacheControl.toString())
                        .build()
            }
        }
    }


    fun isNetworkConnected():Boolean{
        //Network connection check here
        return true;
    }
}

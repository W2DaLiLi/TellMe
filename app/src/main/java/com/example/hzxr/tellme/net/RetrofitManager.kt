package com.example.hzxr.tellme.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Created by Hzxr on 2018/2/15.
 */
object RetrofitManager {

    private var retrofit: Retrofit? = null
    private val BASEURL = "http://192.168.199.159:9090/plugins/restapi/v1/"

    fun getInstance(): Retrofit?{
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    retrofit = initRetrofit()
                }
            }
        }
        return retrofit
    }

    private fun initRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor {
            chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("Authorization","Basic YWRtaW46YWRtaW4=")//设置认证，虽然这样很不安全，但是目前就这样设置吧
                    .method(original.method(), original.body())
                    .build()
            return@addInterceptor chain.proceed(request)
        }

        return  Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持Rxjava
                .client(httpClient.build())
                .build()
    }
}
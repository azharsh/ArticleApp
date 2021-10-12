package com.example.article.di


import android.preference.PreferenceManager
import com.example.article.BuildConfig
import com.example.article.data.article.remote.ArticleApiClient
import com.example.article.data.lib.HeaderInterceptor
import com.example.article.data.lib.ParameterInterceptor
import com.example.article.utils.PrefManager


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val apiModule = module {

    single { provideOkHttpClient(get()) }

    single { provideBaseRetrofit(get()) }

    factory { provideArticleApi(get()) }


}

fun provideOkHttpClient(preferenceManager: PrefManager): OkHttpClient {
    val builder = OkHttpClient().newBuilder()

    builder.cache(null)

    //    builder.addNetworkInterceptor(ProtocolInterceptor())
    builder.addInterceptor(getHeaderInterceptor(preferenceManager))
    builder.addInterceptor(getParameterInterceptor(preferenceManager))


    //   add logging as last interceptor. remember interceptor are sequential
    builder.addInterceptor(provideLoggingInterceptor())

    builder.retryOnConnectionFailure(true)
        .readTimeout(100L, TimeUnit.SECONDS)
        .connectTimeout(100L, TimeUnit.SECONDS)
        .writeTimeout(100L, TimeUnit.SECONDS)

    return builder.build()
}

fun provideBaseRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(provideGsonConverter()).build()
}

fun provideGsonConverter(): GsonConverterFactory {
    return GsonConverterFactory.create()
}


private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.HEADERS
    // you can use the logging level BODY when you want to debug in your local machine
    // but please don't commit it
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

private fun getParameterInterceptor(preferenceManager: PrefManager): Interceptor {
    val params = HashMap<String, String>()
    //define default parameter here
    return ParameterInterceptor(params, preferenceManager)
}

private fun getHeaderInterceptor(preferenceManager: PrefManager): Interceptor {
    val headers = HashMap<String, String>()
    //define default headers here
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers, preferenceManager)
}


fun provideArticleApi(retrofit: Retrofit): ArticleApiClient = retrofit.create(ArticleApiClient::class.java)

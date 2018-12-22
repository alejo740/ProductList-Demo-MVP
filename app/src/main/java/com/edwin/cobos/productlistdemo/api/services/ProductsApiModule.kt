package com.edwin.cobos.productlistdemo.api.services

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ProductsApiModule {

    companion object {
        //private const val BASE_URL = "https://www.abercrombie.com/"
        const val BASE_URL = "http://mumstudents.org/~986160/"
    }

    @Provides
    fun provideClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .header(":authority", "www.abercrombie.com")
                .header(":method", "GET")
                .header(":scheme", "https")
                .header(":path", "/anf/nativeapp/qa/codetest/codeTest_exploreData.json")
                .header("Content-Type", "application/json")
                .header("cache-control", "no-cache")
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("accept-encoding:", "gzip, deflate, br")
                .header("accept-language:", "en,es;q=0.9,es-419;q=0.8")
                .header("upgrade-insecure-requests:", "1")
                .header("User-Agent", "Android-Product-List-App")
                .header("Accept", "application/vnd.yourapi.v1.full+json")
                .method(request.method(), request.body())
                .build()
            chain.proceed(newRequest)
        }
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(baseURL: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            //.client(client)
            .build()
    }

    @Provides
    fun provideApiService(): ProductsApiService {
        return provideRetrofit(BASE_URL, provideClient()).create(ProductsApiService::class.java)
    }
}
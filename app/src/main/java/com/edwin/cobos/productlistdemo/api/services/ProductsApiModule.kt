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
        const val BASE_URL = "https://www.abercrombie.com/"
    }

    @Provides
    fun provideClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .header("Host", "www.abercrombie.com")
                .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
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
            .client(client)
            .build()
    }

    @Provides
    fun provideApiService(): ProductsApiService {
        return provideRetrofit(BASE_URL, provideClient()).create(ProductsApiService::class.java)
    }
}
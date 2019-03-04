package com.example.albumviewer

import android.app.Application
import com.example.albumviewer.common.BASE_URL
import com.example.albumviewer.common.RETROFIT_CACHE_SIZE
import com.example.albumviewer.common.TEST_BASE_URL
import com.example.albumviewer.common.TIMEOUT
import com.example.albumviewer.di.Remote
import com.example.albumviewer.net.TypicodeService
import com.example.albumviewer.repo.DataSource
import com.example.albumviewer.repo.RemoteDataSource
import dagger.Module
import dagger.Provides
import io.appflate.restmock.RESTMockServer
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class TestNetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitCache(application: Application) = Cache(application.cacheDir, RETROFIT_CACHE_SIZE)

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .sslSocketFactory(RESTMockServer.getSSLSocketFactory(), RESTMockServer.getTrustManager())
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TEST_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTypicodeService(retrofit: Retrofit) = retrofit.create(TypicodeService::class.java)


    @Provides
    @Singleton
    @Remote
    fun provideRemoteDataSource(typicodeService: TypicodeService): DataSource = RemoteDataSource(typicodeService)

}
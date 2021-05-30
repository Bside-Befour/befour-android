package org.positive.sms.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.positive.sms.common.PsConstants
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Named("account")
    fun provideAccountServerRetrofit(
        @Named("oauth") okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(PsConstants.ACCOUNT_SERVER_BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Named("cdn")
    fun provideCdnServerRetrofit(
        @Named("certified") okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(PsConstants.CDN_SERVER_BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()
}


package com.cyrilpillai.marvelverse.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.cyrilpillai.marvelverse.BuildConfig
import com.cyrilpillai.marvelverse.core.network.converter.EnvelopingConverter
import com.cyrilpillai.marvelverse.core.network.adapter.NetworkResultCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.MessageDigest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val HEADER_API_KEY = "apikey"
    private const val HEADER_TIMESTAMP = "ts"
    private const val HEADER_HASH = "hash"

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor = Interceptor {
        it.run {
            val timestamp = System.currentTimeMillis().toString()
            proceed(
                request()
                    .newBuilder()
                    .url(
                        it.request().url.newBuilder()
                            .addQueryParameter(HEADER_API_KEY, BuildConfig.MARVEL_API_PUBLIC_KEY)
                            .addQueryParameter(HEADER_TIMESTAMP, timestamp)
                            .addQueryParameter(HEADER_HASH, getHash(timestamp))
                            .build()
                    )
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(ChuckerInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.MARVEL_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(EnvelopingConverter())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
        .build()

    /**
     * Generate a MD5 hash of the ts parameter, private key and public key
     * md5(ts+privateKey+publicKey)
     *
     * https://developer.marvel.com/documentation/authorization
     */
    private fun getHash(timestamp: String): String {
        val input =
            "$timestamp${BuildConfig.MARVEL_API_PRIVATE_KEY}${BuildConfig.MARVEL_API_PUBLIC_KEY}"
        val md = MessageDigest.getInstance("MD5")
        val hashBytes = md.digest(input.toByteArray())
        // Convert the hash bytes to a hex string
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}
/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [GithubModule.kt] created by Ji Sungbin on 21. 6. 23. 오후 9:21.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import logcat.logcat
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object GithubModule {
    private const val BaseUrl = "https://raw.githubusercontent.com"

    private val loggingInterceptor =
        HttpLoggingInterceptor { message -> logcat { message } }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun getInterceptor(vararg interceptors: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        for (interceptor in interceptors) builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @ViewModelScoped
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(getInterceptor(loggingInterceptor))
        .build()
}
/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [GithubModule.kt] created by Ji Sungbin on 21. 11. 11. 오후 5:11
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.event.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.jisungbin.logeukes.logeukes
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import team.bravepeople.devevent.event.data.EventService

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {
    private const val BaseUrl = "https://raw.githubusercontent.com"

    private val loggingInterceptor =
        HttpLoggingInterceptor { message -> logeukes("OkHttp") { message } }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun getInterceptor(vararg interceptors: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        for (interceptor in interceptors) builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @ViewModelScoped
    fun provideRetrofit(): EventService = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(getInterceptor(loggingInterceptor))
        .build()
        .create(EventService::class.java)
}

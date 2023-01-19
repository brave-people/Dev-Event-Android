/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [HttpClient.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:02
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.datasource

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal object EventsHttpClient {
    private const val MaxTimeoutMillis = 3000
    private const val BaseUrl = "https://real-brave-people.o-r.kr/front/v1/events"

    private operator fun invoke(): Fuel {
        FuelManager.instance.basePath = BaseUrl
        FuelManager.instance.timeoutInMillisecond = MaxTimeoutMillis
        FuelManager.instance.timeoutReadInMillisecond = MaxTimeoutMillis
        return Fuel
    }

    @Provides
    fun provideHttpClient(): Fuel = this()
}

/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [HttpClient.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:02
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.datasource

import android.content.Context
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.interceptors.LogRequestInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import team.brave.devevent.android.data.R


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal object FuelClient {
    private const val MaxTimeoutMillis = 3000
    private const val BaseUrl = "https://real-brave-people.o-r.kr/front/v1/events"

    private operator fun invoke(socketFactory: SSLSocketFactory): Fuel {
        with(FuelManager.instance) {
            basePath = BaseUrl
            timeoutInMillisecond = MaxTimeoutMillis
            timeoutReadInMillisecond = MaxTimeoutMillis
            this.socketFactory = socketFactory
            addRequestInterceptor(LogRequestInterceptor)
            addResponseInterceptor(LogResponseInterceptor)
        }
        return Fuel
    }

    @Provides
    fun provide(@ApplicationContext context: Context): Fuel {
        return this(getSslCertificationFactory(context))
    }
}

// https://yujuwon.tistory.com/entry/Trust-anchor-for-certification-path-not-found-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0
private fun getSslCertificationFactory(context: Context): SSLSocketFactory {
    val certificateFactory = CertificateFactory.getInstance("X.509")
    val pemStream = context.resources.openRawResource(R.raw.real_brave_people_o_r_kr)
    val certificate = pemStream.use(certificateFactory::generateCertificate)

    val keyStoreType = KeyStore.getDefaultType()
    val keyStore = KeyStore.getInstance(keyStoreType)
    keyStore.load(null, null)
    keyStore.setCertificateEntry("ca", certificate)

    val trustManagerAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
    val trustManager = TrustManagerFactory.getInstance(trustManagerAlgorithm)
    trustManager.init(keyStore)

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, trustManager.trustManagers, SecureRandom())

    return sslContext.socketFactory
}

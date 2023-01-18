/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [ServerResponseMapperTest.kt] created by Ji Sungbin on 23. 1. 2. 오후 5:31
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("NonAsciiCharacters", "OPT_IN_USAGE")

package team.brave.devevent.android.data

import io.ktor.client.HttpClient
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly
import team.brave.devevent.android.data.dummy.DummyResponse
import team.brave.devevent.android.data.mock.buildMockHttpClient
import team.brave.devevent.android.data.repository.EventRepositoryImpl
import team.brave.devevent.android.domain.repository.EventRepository

class ServerResponseMapperTest {
    private lateinit var client: HttpClient
    private val repository: EventRepository by lazy { EventRepositoryImpl(client) }

    @Before
    fun setupClient() {
        client = buildMockHttpClient(content = DummyResponse.RawData)
    }

    @Test
    fun `data 에서 domain 으로 모델 매핑`() = runTest {
        val response = repository.getAllEvents()

        expectThat(response).containsExactly(DummyResponse.DomainData)
    }

    @After
    fun closeClient() {
        client.close()
    }
}

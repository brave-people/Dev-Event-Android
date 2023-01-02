/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [MockEngine.kt] created by Ji Sungbin on 23. 1. 2. 오후 5:34
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.mock

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.jackson.jackson

@Suppress("TestFunctionName")
private fun MockEngine(status: HttpStatusCode, content: String): MockEngine {
    return MockEngine {
        respond(
            content = content,
            status = status,
            headers = headersOf(
                name = HttpHeaders.ContentType,
                value = ContentType.Application.Json.toString(),
            ),
        )
    }
}

fun buildMockHttpClient(
    status: HttpStatusCode = HttpStatusCode.OK,
    content: String,
): HttpClient {
    return HttpClient(
        engine = MockEngine(
            status = status,
            content = content,
        ),
    ) {
        expectSuccess = true
        install(plugin = ContentNegotiation) {
            jackson()
        }
    }
}

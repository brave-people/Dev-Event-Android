/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [GithubService.kt] created by Ji Sungbin on 21. 6. 23. 오후 9:26.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event.repo

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface GithubService {
    @GET("/brave-people/Dev-Event/master/README.md")
    suspend fun getEvents(): Response<ResponseBody>
}

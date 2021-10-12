/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventModule.kt] created by Ji Sungbin on 21. 7. 2. 오전 4:36.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event.repo

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventRepoModule {
    @Provides
    @Singleton
    fun provideEventRespository(
        @ApplicationContext context: Context,
        client: GithubService,
        database: EventDatabase,
    ): EventRepo = EventRepoImpl(context, client, database)
}

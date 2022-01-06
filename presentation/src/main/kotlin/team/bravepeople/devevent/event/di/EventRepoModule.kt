/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventRepoModule.kt] created by Ji Sungbin on 21. 11. 11. 오후 5:11
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.event.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import team.bravepeople.devevent.event.data.EventRepoImpl
import team.bravepeople.devevent.event.data.EventService
import team.bravepeople.devevent.event.domain.EventRepo

@Module
@InstallIn(ViewModelComponent::class)
object EventRepoModule {
    @Provides
    @ViewModelScoped
    fun provideEventRepo(client: EventService): EventRepo = EventRepoImpl(client)
}

/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [BindsModule.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:37
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.brave.devevent.android.data.repository.EventRepositoryImpl
import team.brave.devevent.android.domain.repository.EventRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun provideEventRepository(impl: EventRepositoryImpl): EventRepository
}

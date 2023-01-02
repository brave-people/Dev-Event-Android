/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventUseCaseModule.kt] created by Ji Sungbin on 23. 1. 2. 오후 7:25
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.di.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.brave.devevent.android.domain.repository.EventRepository
import team.brave.devevent.android.domain.usecase.GetAllEventsUseCase

@Module
@InstallIn(SingletonComponent::class)
object EventUseCaseModule {
    @Provides
    fun provideGetAllEventsUseCase(repository: EventRepository): GetAllEventsUseCase {
        return GetAllEventsUseCase(repository)
    }
}

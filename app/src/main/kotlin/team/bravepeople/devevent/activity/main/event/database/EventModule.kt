/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [GlobalModule.kt] created by Ji Sungbin on 21. 7. 2. 오전 4:37.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [GlobalModule.kt] created by Ji Sungbin on 21. 6. 24. 오후 11:30.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = EventDatabase.instance(context)
}
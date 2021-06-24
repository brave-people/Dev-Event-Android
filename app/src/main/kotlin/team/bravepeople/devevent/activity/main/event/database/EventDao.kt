/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventDao.kt] created by Ji Sungbin on 21. 6. 22. 오후 10:05.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EventDao {
    @Insert
    suspend fun insertAll(event: List<EventEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(event: List<EventEntity>)

    @Query("SELECT * FROM EventEntity")
    suspend fun getEvents(): List<EventEntity>
}
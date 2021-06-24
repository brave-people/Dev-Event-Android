/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventDatabase.kt] created by Ji Sungbin on 21. 6. 22. 오후 10:07.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EventEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun dao(): EventDao

    companion object {
        fun instance(context: Context): EventDatabase {
            return synchronized(EventDatabase::class) {
                Room.databaseBuilder(
                    context,
                    EventDatabase::class.java,
                    "events.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
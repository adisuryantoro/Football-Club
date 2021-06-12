package id.adisuryantoro.footballclub.remote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.adisuryantoro.footballclub.model.database.ClubDatabase

@Database(entities = [ClubDatabase::class], version = 1, exportSchema = false)
abstract class ClubRoomDatabase : RoomDatabase() {
    abstract fun clubDao(): ClubDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: ClubRoomDatabase? = null

        fun getInstance(context: Context): ClubRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, ClubRoomDatabase::class.java, "football_match_schedule")
                .build()
    }

}
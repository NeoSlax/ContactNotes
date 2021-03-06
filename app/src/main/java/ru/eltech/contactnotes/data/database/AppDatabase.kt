package ru.eltech.contactnotes.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ContactsDbModel::class,
        NotesDbModel::class
    ],
    version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shopListDao(): NoteListDao

    companion object {

        private const val DB_NAME = "database.db"
        private var INSTANCE: AppDatabase? = null
        private var LOCK = Any()

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }

        }
    }
}
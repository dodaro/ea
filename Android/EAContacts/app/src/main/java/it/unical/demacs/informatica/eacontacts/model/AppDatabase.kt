package it.unical.demacs.informatica.eacontacts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* https://developer.android.com/codelabs/kotlin-android-training-room-database?hl=en&continue=https%3A%2F%2Fcodelabs.developers.google.com%2F%3Fcat%3Dandroid#5 */
/*
Parameters:
- entities: all classes that are marked as Entity
- version: the current version of the schema, should be changed whenever you change the schema of the database
- exportSchema: to keep or not the backup of all versions of the schema
 */
@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() { // We have to extend RoomDatabase
    abstract fun contactDao(): ContactDAO // Here we define the DAOs as abstract function

    companion object {
        // A volatile variable is never cached, and it is modified/read from the main memory.
        // Any change made by one thread is visible to all other threads.
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            // We guarantee that the database is initialized once
            synchronized(this) {
                var localInstance = instance // Needed for smart cast
                if (localInstance == null) {
                    localInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "contacts-database" // The name of the database
                    ).build()
                    instance = localInstance
                }
                return localInstance
            }
        }
    }
}


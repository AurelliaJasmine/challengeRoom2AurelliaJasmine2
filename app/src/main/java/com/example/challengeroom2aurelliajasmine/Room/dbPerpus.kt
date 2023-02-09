package com.example.challengeroom2aurelliajasmine.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TbBuku::class, TbPeminjam::class], version = 1)
abstract class dbPerpus : RoomDatabase() {
    abstract fun tbBukuDao(): TbBukuDao
    abstract fun tbPeminjamDao(): TbPeminjamDao

    companion object{
        @Volatile private var instance: dbPerpus? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: builDatabase(context).also{
                instance = it
            }
        }
        private fun builDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            dbPerpus::class.java,
            "db.perpustakaan"
        ).build()
    }
}

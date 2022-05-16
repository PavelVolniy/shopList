package com.example.shoplist.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository


@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shopItemDAO(): ShopItemDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val NAME_DB = "shopItemDataBase"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    NAME_DB
                )
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}
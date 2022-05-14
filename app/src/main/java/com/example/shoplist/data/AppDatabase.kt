package com.example.shoplist.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository


@Database(entities = [ShopItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shopItemDAO(): ShopItemDAO

    companion object {
        private lateinit var shopItemDataBase: AppDatabase

        fun getAppDataBase(context: Context): AppDatabase {
            shopItemDataBase =
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()
            Log.e("test", "$DATABASE was created")
            return shopItemDataBase
        }

        fun getInstance(): AppDatabase {
            return shopItemDataBase
        }

        fun deleteDataBase(){
            shopItemDataBase
        }

        private const val DATABASE = "database"
    }
}
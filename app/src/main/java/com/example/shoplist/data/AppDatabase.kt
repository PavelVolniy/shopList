package com.example.shoplist.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ShopItemData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shopItemDAO(): ShopItemDAO
}
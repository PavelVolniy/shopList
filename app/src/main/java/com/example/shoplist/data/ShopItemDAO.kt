package com.example.shoplist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shoplist.domain.ShopItem

@Dao
interface ShopItemDAO {
    @Query("SELECT * FROM ShopItemData")
    fun getShopItemList(): List<ShopItem>

    @Query("SELECT * FROM ShopItemData WHERE shopItemId in(:id)")
    fun getShopItemById(id: Int): List<ShopItem>

    @Insert
    fun insertShopItem(vararg shopItem: ShopItem)

    @Delete
    fun deleteShopItem(shopItem: ShopItem)
}
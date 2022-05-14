package com.example.shoplist.data

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.shoplist.domain.ShopItem

@Dao
interface ShopItemDAO {
    @Query("SELECT * FROM ShopItem")
    fun getShopItemList(): List<ShopItem>
    @Query("SELECT * FROM ShopItem WHERE id IN (:shopItemID)")
    fun getShopItemById(shopItemID: Int): ShopItem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShopItem( shopItem: ShopItem)
    @Delete
    fun deleteShopItem(shopItem: ShopItem)
    @Update
    fun updateShopItem(shopItem: ShopItem)
}
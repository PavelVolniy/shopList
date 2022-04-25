package com.example.shoplist.domain

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopListRepository {
    @Insert
    fun addShopItem(shopItem: ShopItem)
    @Delete
    fun deleteShopItem(shopItem: ShopItem)
    @Update
    fun editShopItem(shopItem: ShopItem)
    @Query("SELECT * FROM ShopItem WHERE id IN (:shopItemId)")
    fun getShopItem(shopItemId: Int): ShopItem
    @Query("SELECT * FROM ShopItem")
    fun getShopList(): LiveData<List<ShopItem>>
}
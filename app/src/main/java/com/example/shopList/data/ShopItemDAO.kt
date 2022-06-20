package com.example.shopList.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopList.domain.ShopItem

@Dao
interface ShopItemDAO {
    @Query("SELECT * FROM shopItems ORDER BY enabled DESC")
    fun getShopItemList(): LiveData<List<ShopItemDbModel>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShopItem( shopItemDbModel: ShopItemDbModel)
    @Query("DELETE FROM shopItems WHERE id=:shopItemID")
    suspend fun deleteShopItem(shopItemID: Int)
    @Query("DELETE FROM shopItems")
    suspend fun deleteAllShopItems()
    @Query("SELECT * FROM shopItems WHERE id=:shopItemID LIMIT 1")
    suspend fun getShopItemById(shopItemID: Int): ShopItemDbModel
    @Query("SELECT SUM(price * count) FROM shopItems WHERE enabled = 1")
    fun getSumShopItem() : LiveData<Double>
}
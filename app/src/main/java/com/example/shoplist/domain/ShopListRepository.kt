package com.example.shoplist.domain

import androidx.lifecycle.MutableLiveData
import androidx.room.*


interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItem(shopItemId: Int): ShopItem
    suspend fun clearShopItemList()
    fun getShopList(): MutableLiveData<List<ShopItem>>
}
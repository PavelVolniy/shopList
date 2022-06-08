package com.example.shopList.domain

import androidx.lifecycle.MutableLiveData


interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItem(shopItemId: Int): ShopItem
    suspend fun clearShopItemList()
    suspend fun getSumShopItem()
    fun getShopList(): MutableLiveData<List<ShopItem>>
}
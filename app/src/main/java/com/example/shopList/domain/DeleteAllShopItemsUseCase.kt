package com.example.shopList.domain

class DeleteAllShopItemsUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun deleteAllShopItem(){
        shopListRepository.clearShopItemList()
    }
}
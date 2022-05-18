package com.example.shoplist.domain

class DeleteAllShopItemsUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun deleteAllShopItem(){
        shopListRepository.clearShopItemList()
    }
}
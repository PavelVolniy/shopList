package com.example.shopList.domain

import androidx.lifecycle.MutableLiveData

class GetSumShopItemsPriceUseCase(private val shopListRepository: ShopListRepository) {
    fun getSumShopItemsPrice(): MutableLiveData<String> {
        return shopListRepository.getSumShopItem()
    }
}
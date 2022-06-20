package com.example.shopList.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopList.data.ShopListMapper
import com.example.shopList.data.ShopListRepositoryImpl
import com.example.shopList.domain.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteAllShopItemsUseCase = DeleteAllShopItemsUseCase(repository)
    private val getSumPriceShopItemsEnabled = GetSumShopItemsPriceUseCase(repository)



    val shopList = getShopListUseCase.getShopList()
    val sumShopList = getSumPriceShopItemsEnabled.getSumShopItemsPrice()


    fun deleteShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem){
        viewModelScope.launch {
            val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }

    fun deleteAllShopItems(){
        viewModelScope.launch {
            deleteAllShopItemsUseCase.deleteAllShopItem()
        }
    }


    }

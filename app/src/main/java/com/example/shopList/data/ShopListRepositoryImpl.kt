package com.example.shopList.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopList.domain.ShopItem
import com.example.shopList.domain.ShopListRepository
import kotlin.math.log

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {
    private val shopItemDAO = AppDatabase.getInstance(application).shopItemDAO()

    private var shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0
    private var shopListLD = MutableLiveData<List<ShopItem>>()
    private lateinit var list: List<ShopItem>
    private val mapper = ShopListMapper()
    private var sum :String = "0.0"


    override suspend fun addShopItem(shopItem: ShopItem) {
        shopItemDAO.insertShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopItemDAO.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopItemDAO.insertShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopItemDAO.getShopItemById(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getSumShopItem():MutableLiveData<String> {
        return MediatorLiveData<String>().apply {
            addSource(shopItemDAO.getSumShopItem()){
                value = it.toString()
            }
        }
    }

    override suspend fun clearShopItemList() {
        shopItemDAO.deleteAllShopItems()
    }

    override fun getShopList(): MutableLiveData<List<ShopItem>> {
        return MediatorLiveData<List<ShopItem>>().apply {
            addSource(shopItemDAO.getShopItemList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }

        }
    }


}
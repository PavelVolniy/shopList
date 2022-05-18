package com.example.shoplist.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {
    private val shopItemDAO = AppDatabase.getInstance(application).shopItemDAO()

    private var shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0
    private var shopListLD = MutableLiveData<List<ShopItem>>()
    private lateinit var list: List<ShopItem>
    private val mapper = ShopListMapper()





    suspend override fun addShopItem(shopItem: ShopItem) {
        shopItemDAO.insertShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    suspend override fun deleteShopItem(shopItem: ShopItem) {
        shopItemDAO.deleteShopItem(shopItem.id)
    }

    suspend override fun editShopItem(shopItem: ShopItem) {
        shopItemDAO.insertShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    suspend override fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopItemDAO.getShopItemById(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
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
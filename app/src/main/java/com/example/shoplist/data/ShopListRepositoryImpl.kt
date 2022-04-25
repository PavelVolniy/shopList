package com.example.shoplist.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private var shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0
    private var shopListLD = MutableLiveData<List<ShopItem>>()
    private val database: AppDatabase = AppDatabase.getInstance()

    init {
        if (shopListLD == null) {
            shopListLD = database.shopItemDAO().getShopItemList()
        }
        for (i in 0 until 5) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        database.shopItemDAO().insertShopItem(shopItem)
        updateShopList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        database.shopItemDAO().deleteShopItem(shopItem)
        updateShopList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        database.shopItemDAO().updateShopItem(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return database.shopItemDAO().getShopItemById(shopItemId)
//        return shopList.find { it.id == shopItemId }
//            ?: throw RuntimeException("Element with id $shopItemId is not found")
    }

    override fun getShopList(): MutableLiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateShopList() {
        shopListLD.value = shopList.toList()
    }

}
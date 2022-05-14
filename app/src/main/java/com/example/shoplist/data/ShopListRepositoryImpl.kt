package com.example.shoplist.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository
import java.util.*
import kotlin.concurrent.thread

object ShopListRepositoryImpl : ShopListRepository {

    private var shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0
    private var shopListLD = MutableLiveData<List<ShopItem>>()
    private lateinit var list: List<ShopItem>
    private val dataBase = AppDatabase.getInstance().shopItemDAO()

    init {
        thread {
            val list = dataBase.getShopItemList()
            for (i in list){
                shopList.add(i)
                if (i.id > autoIncrementId){
                    autoIncrementId = i.id
                }
            }
        }
        updateShopList()
//        for (i in 0 until 5) {
//            val item = ShopItem("Name $i", i, Random.nextBoolean())
//            addShopItem(item)
//        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (validateName(shopItem)) {
            shopItem.id = autoIncrementId++
            shopList.add(shopItem)
        }
        thread {
            if (validateName(shopItem)){
                dataBase.insertShopItem(shopItem)
            }
        }
        updateShopList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        thread {
            AppDatabase.getInstance().shopItemDAO().deleteShopItem(shopItem)
        }
        updateShopList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
//        database.shopItemDAO().updateShopItem(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
//        return database.shopItemDAO().getShopItemById(shopItemId)
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId is not found")
    }

    override fun getShopList(): MutableLiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateShopList() {
        thread {
            val db = dataBase.getShopItemList().toString()
            Log.e("test List", db)
            Log.e("count", shopList.size.toString())
            Log.e("shopList", shopList.toString())
        }
        shopListLD.value = sortedShopItemList(shopList.toList())
    }

    private fun sortedShopItemList(list: List<ShopItem>): List<ShopItem> {
        return list.sortedBy { !it.enabled }
    }

    private fun validateName(shopItem: ShopItem): Boolean{
        val oldShopItem = shopList.find { it.name == shopItem.name }
        Log.e("check fun", oldShopItem.toString() + " max id = " + autoIncrementId)
        return if (oldShopItem != null) {
            Log.e("check validate", "false")
            false
        } else {
            true
        }
    }

}
package com.example.shopList.domain


data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    val price: Double,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}

package com.example.shoplist.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    @PrimaryKey var id: Int = UNDEFINED_ID
) {
    companion object{
        const val UNDEFINED_ID = -1
    }
}

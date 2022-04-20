package com.example.shoplist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItemData(
    @PrimaryKey val shopItemId : Int,
    @ColumnInfo(name = "nameItem") val nameItem: String?,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "enabled") val enabled: Boolean
)

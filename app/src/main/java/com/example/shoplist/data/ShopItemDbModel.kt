package com.example.shoplist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoplist.domain.ShopItem

@Entity(tableName = "shopItems")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean


//    @PrimaryKey val shopItemId : Int,
//    @ColumnInfo(name = "nameItem") val nameItem: String?,
//    @ColumnInfo(name = "count") val count: Int,
//    @ColumnInfo(name = "enabled") val enabled: Boolean
)

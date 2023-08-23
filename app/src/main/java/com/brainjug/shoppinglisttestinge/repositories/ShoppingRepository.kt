package com.brainjug.shoppinglisttestinge.repositories

import androidx.lifecycle.LiveData
import com.brainjug.shoppinglisttestinge.data.local.ShoppingItem
import com.brainjug.shoppinglisttestinge.data.remote.responses.ImageResponse
import com.brainjug.shoppinglisttestinge.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}
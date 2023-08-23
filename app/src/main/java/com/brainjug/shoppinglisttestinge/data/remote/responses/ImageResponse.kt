package com.brainjug.shoppinglisttestinge.data.remote.responses

data class ImageResponse(
    val imageResults: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)
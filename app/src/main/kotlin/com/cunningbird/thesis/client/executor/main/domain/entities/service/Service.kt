package com.cunningbird.thesis.client.executor.main.domain.entities.service

import com.squareup.moshi.Json
import java.util.*

data class Service(
    @field:Json(name = "id") var id: UUID? = null,
    @field:Json(name = "title") var title: String? = null,
    @field:Json(name = "price") var price: Double? = null,
    @field:Json(name = "description") var description: String? = null,
    @field:Json(name = "image") var image: String? = null
)
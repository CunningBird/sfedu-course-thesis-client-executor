package com.cunningbird.thesis.client.executor.main.domain.entities.appointment

import com.squareup.moshi.Json
import java.util.*

data class Appointment(
    @field:Json(name = "id") var id: UUID? = null,
    @field:Json(name = "advertName") var advertName: String? = null,
    @field:Json(name = "advertImage") var advertImage: String? = null,
    @field:Json(name = "date") var date: Date? = null,
)
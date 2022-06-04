package com.cunningbird.thesis.client.executor.main.domain.entities.appointment

import com.squareup.moshi.Json
import java.util.*

data class CreateAppointmentRequest(
    @field:Json(name = "date") var date: Date? = null,
)
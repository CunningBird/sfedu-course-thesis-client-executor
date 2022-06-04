package com.cunningbird.thesis.client.executor.main.domain.entities.appointment

import com.cunningbird.thesis.client.executor.main.domain.entities.appointment.Appointment
import com.squareup.moshi.Json

data class AppointmentList(
    @field:Json(name = "list") var list: List<Appointment>? = null,
)
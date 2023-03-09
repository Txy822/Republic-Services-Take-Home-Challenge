package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto

import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.RouteDetailEntity

data class Route(
    val id: Int,
    val name: String,
    val type: String
)

fun Route.toRoutesDetailEntity(): RouteDetailEntity = RouteDetailEntity(
    id= id,
    name = name,
    type = type
)
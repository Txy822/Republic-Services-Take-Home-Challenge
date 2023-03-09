package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.Route

@Entity(tableName = "route_detail_table")
data class RouteDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String
)

fun RouteDetailEntity.toRouteDto(): Route =Route(id = id, name = name, type = type)
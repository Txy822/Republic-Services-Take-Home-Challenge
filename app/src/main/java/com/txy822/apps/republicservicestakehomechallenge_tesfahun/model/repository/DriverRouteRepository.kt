package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.repository

import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.DriverDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.RouteDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.DriverRouteDto
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.util.Resource
import kotlinx.coroutines.flow.Flow

interface DriverRouteRepository {
    suspend fun getDriverRouteDetails(): Flow<Resource<DriverRouteDto>>

    suspend fun insertDriversDetails(drivers: List<DriverDetailEntity>)

    suspend fun insertRoutesDetails(routes: List<RouteDetailEntity>)

    suspend fun getDriverDetailByName(name: String): DriverDetailEntity

    suspend fun  getDriversDetails(): List<DriverDetailEntity>

//    suspend fun getRouteDetail(id: Int) : RouteDetailEntity

    suspend fun deleteRoutes()

    suspend fun deleteDrivers()
    suspend fun getRoutesDetails(): List<RouteDetailEntity>
    suspend fun getRouteDetailById(id: Int): Resource<RouteDetailEntity>
    suspend fun getDriversDetails2(): Resource<List<DriverDetailEntity>>
}
package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.repository

import android.util.Log
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.*
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.apiservice.DriverRouteApi
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.DriverRouteDto
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.toDriverDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.toRoutesDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DriverRouteRepositoryImpl @Inject constructor(
    private val api: DriverRouteApi,
    db: DriverRouteDatabase
) : DriverRouteRepository {

    private val driverDao = db.driverDetailDao()
    private val routesDao = db.routeDetailDao()
    override suspend fun getDriverRouteDetails(): Flow<Resource<DriverRouteDto>> {
        return flow {

            val remoteListings = try {
                api.getDriverRoutes()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            }
            remoteListings?.let { listings ->
                //clearCatFactsFromDb()
                insertDriversDetails(listings.drivers.map { it.toDriverDetailEntity()})
                insertRoutesDetails(listings.routes.map { it.toRoutesDetailEntity()})

//                val result = getRouteDetail(1)
//                Log.i("Tag", result.name + result.type)
//                println(" Result test ${result.name}")

                val drivers = getDriversDetails().map { it.toDriversDto() }
                val routes = getRoutesDetails().map { it.toRouteDto() }
                println("drivers list size : ${drivers.size}")

                println("routes  list size : ${routes.size}")

                emit(Resource.Success(
                    data =  DriverRouteDto(drivers = drivers, routes = routes)
                ))
                emit(Resource.Loading(false))
            }
            if (remoteListings == null) {
                val drivers = getDriversDetails().map { it.toDriversDto() }
                val routes = getRoutesDetails().map { it.toRouteDto() }
                println("drivers list size : ${drivers.size}")

                println("routes  list size : ${routes.size}")

                emit(Resource.Success(
                    data =  DriverRouteDto(drivers = drivers, routes = routes)
                ))
            }
        }
    }

    override suspend fun insertDriversDetails(drivers: List<DriverDetailEntity>) {
        driverDao.insertDriverDetailList(drivers)
    }

    override suspend fun insertRoutesDetails(routes: List<RouteDetailEntity>) {
        routesDao.insertRoutesList(routes)
    }

    override suspend fun getDriverDetailByName(name: String): DriverDetailEntity {

       return  driverDao.searchDriverDetail(name)
    }

    override suspend fun getDriversDetails(): List<DriverDetailEntity> {
       return  driverDao.getAllDriversFromDb()
    }
    override suspend fun getRoutesDetails(): List<RouteDetailEntity> {
        return  routesDao.getAllRoutesFromDb()
    }

//    override suspend fun getRouteDetail(id: Int): RouteDetailEntity {
//
//        return  routesDao.routeById(id)
//
//    }
    override suspend fun getDriversDetails2(): Resource<List<DriverDetailEntity>> {
        return try {
            val result = driverDao.getAllDriversFromDb()
            Resource.Success(result)

        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load routes info due to IO error"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't Load routes  info by Http error"
            )
        }
    }

    override suspend fun getRouteDetailById(id: Int): Resource<RouteDetailEntity> {
        return try {
            val result = routesDao.routeById(id)
            Resource.Success(result)

        } catch (e: IOException) {
            e.printStackTrace()

            Resource.Error(
                message = "Couldn't load routes info due to IO error"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't Load routes  info by Http error"
            )
        }
    }

    override suspend fun deleteRoutes() {
        routesDao.clearRoutesTable()
    }

    override suspend fun deleteDrivers() {
        driverDao.clearDriverDetailTable()
    }
}
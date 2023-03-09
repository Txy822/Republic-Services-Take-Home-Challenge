package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DriverDetailEntity::class, RouteDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DriverRouteDatabase: RoomDatabase() {
    abstract fun driverDetailDao():DriverDao
    abstract fun routeDetailDao():RouteDao
}
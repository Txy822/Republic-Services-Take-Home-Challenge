package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RouteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutesList(routes: List<RouteDetailEntity>)

    @Query("DELETE FROM route_detail_table")
    suspend fun clearRoutesTable()

    @Query("SELECT * FROM  route_detail_table WHERE id = :routeId")
    suspend fun routeById(routeId: Int): RouteDetailEntity

    @Query("SELECT * FROM route_detail_table")
    suspend fun getAllRoutesFromDb(): List<RouteDetailEntity>
}
package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.apiservice

import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.DriverRouteDto
import retrofit2.http.GET

interface DriverRouteApi {

    @GET(END)
    suspend fun getDriverRoutes(
    ):DriverRouteDto
    companion object {
        const val BASE_URL = "https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/"
        const val END = "data"
    }

}

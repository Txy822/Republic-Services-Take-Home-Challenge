package com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto

import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.DriverDetailEntity

data class Driver(
    val id: String,
    val name: String
)
  fun  Driver.toDriverDetailEntity(): DriverDetailEntity = DriverDetailEntity(
      id =id,
      name = name
  )

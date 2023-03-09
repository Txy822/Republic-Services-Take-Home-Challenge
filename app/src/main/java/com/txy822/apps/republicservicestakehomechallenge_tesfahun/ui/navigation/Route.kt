package com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Detail: Screen(route = "detail_screen")
}
package com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.RouteDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.detail.DriverRouteDetailScreen
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.main.DriverRouteMainScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            DriverRouteMainScreen(navController = navController)
        }
        composable("${Screen.Detail.route}/{id}",
            arguments = listOf(navArgument("id"){type= NavType.IntType})
        ) {
            DriverRouteDetailScreen(navController=navController)
        }
    }

}
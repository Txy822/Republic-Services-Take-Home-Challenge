package com.txy822.apps.republicservicestakehomechallenge_tesfahun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.main.DriverRouteViewModel
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.navigation.NavGraph
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.theme.RepublicServicesTakeHomeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepublicServicesTakeHomeChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DriverRouteApp()
                }
            }
        }
    }
}



@Composable
fun DriverRouteApp(){
    val navController = rememberNavController()
    NavGraph(navController =navController )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RepublicServicesTakeHomeChallengeTheme {
        //Greeting("Android")
    }
}
package com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.R
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.detail.TopAppBarContent
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.navigation.Screen

@Composable
fun DriverRouteMainScreen(
    navController: NavController,
) {
    val viewModel: DriverRouteViewModel = hiltViewModel()

    val driversState by viewModel.driversListState.collectAsState()
   // val routesSate by viewModel.routesListState.collectAsState()
    val isLoading by viewModel.loadingState.collectAsState()
    val error by viewModel.errorState.collectAsState()

    Column(modifier = Modifier.padding(20.dp)) {
        TopAppBarContent(viewModel = viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
        ) {
            items(driversState.size) { i ->
                val driverState = driversState[i]

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {

                            navController.navigate(route = "${Screen.Detail.route}/${driverState.id}")
                        },
                    elevation = 0.dp,
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Column {
                        Row(Modifier.padding(top = 10.dp, start = 8.dp)) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = driverState.name,
                                    fontSize = 24.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.W400
                                )
                            }
                        }
                    }
                }
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

            }

        }
    }
}

@Composable
fun TopAppBarContent(viewModel: DriverRouteViewModel) {
    TopAppBar(
        title = {
            Text(
                text = " Drivers  Name List",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        },
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 4.dp,
        actions = {
            IconButton(onClick = { viewModel.updateDriverRoutes(true)}) {
                Icon(painter = painterResource(R.drawable.ic_sort), "Sort")
            }
        }
    )
}
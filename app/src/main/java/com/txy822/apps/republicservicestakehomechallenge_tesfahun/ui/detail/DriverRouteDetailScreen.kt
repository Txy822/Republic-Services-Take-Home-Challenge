package com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.RouteDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.main.DriverRouteViewModel


@Composable
fun DriverRouteDetailScreen(
    navController: NavController,
) {

    val viewModel: DetailViewModel = hiltViewModel()
    val routesSate by viewModel.routesListState.collectAsState()
    val isLoading by viewModel.loadingState.collectAsState()
    val error by viewModel.errorState.collectAsState()

    Column(modifier = Modifier.padding(20.dp)) {
        TopAppBarContent(navController)
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),

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
                            text = routesSate.name,
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W400
                        )
                    }
                }
                Row(Modifier.padding(horizontal = 8.dp)) {
                    Box(
                        Modifier
                            .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(
                                text = routesSate.type,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            // Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                            Text(text =routesSate.id.toString() )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun TopAppBarContent(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = " Route Detail",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        },
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Go back",
                )
            }
        }
    )
}
package com.txy822.apps.republicservicestakehomechallenge_tesfahun.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.DriverDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.local.RouteDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.toDriverDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.data.remote.dto.toRoutesDetailEntity
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.model.repository.DriverRouteRepository
import com.txy822.apps.republicservicestakehomechallenge_tesfahun.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DriverRouteViewModel @Inject constructor(
    private val repo: DriverRouteRepository
) : ViewModel() {

    private val _driversListState = MutableStateFlow(emptyList<DriverDetailEntity>())
    val driversListState: StateFlow<List<DriverDetailEntity>> = _driversListState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> = _errorState.asStateFlow()

    init {
        updateDriverRoutes(false)
    }

    fun updateDriverRoutes(
        sorted: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) { //was Dispatchers.main
            repo.getDriverRouteDetails()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { data ->
                                if(sorted){
                                    val sortedData =data.drivers.sortedBy { it.name.split(" ")[1] }

                                    _driversListState.update { sortedData.map { it.toDriverDetailEntity() } }
                                }
                                else {
                                    _driversListState.update { data.drivers.map { it.toDriverDetailEntity() } }
                                }
                            }
                        }
                        is Resource.Error -> {
                            _errorState.update { "Error Occurred" }
                        }
                        is Resource.Loading -> {
                            _loadingState.update { true }
                        }
                    }
                }
        }
    }
}

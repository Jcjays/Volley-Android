package com.example.volleysample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volleysample.repo.Joke
import com.example.volleysample.repo.Repository
import com.example.volleysample.repo.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = Repository()

    private val homeMutableStateFlow = MutableStateFlow(HomeState())
    val homeStateFlow = homeMutableStateFlow.asStateFlow()

    init {
        getJoke()
    }

    fun getJoke() = viewModelScope.launch(Dispatchers.IO) {
        repository.sampleGet().collectLatest { result ->
            when (result) {
                is ResponseHandler.Failure -> homeMutableStateFlow.value =
                    HomeState(errorMessage = result.message)

                is ResponseHandler.Loading -> homeMutableStateFlow.value =
                    HomeState(isLoading = true)

                is ResponseHandler.Success -> homeMutableStateFlow.value =
                    HomeState(data = result.response)
            }
        }
    }
}

data class HomeState(
    val data: Joke? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false
)
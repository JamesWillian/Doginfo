package com.jammes.doginfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jammes.doginfo.core.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val repository: DogRepository
): ViewModel() {

    val uiState: MutableLiveData<UiState> = MutableLiveData()

    fun loadDogs(dogName: String) {
        viewModelScope.launch {
            uiState.value = UiState.Loading

            try {
                val result = repository.getDogsList(dogName)
                result.fold(
                    onSuccess = {dogItem ->
                        uiState.value = UiState.Success(dogItem)
                    },
                    onFailure = {exception ->
                        uiState.value = UiState.Error(exception)
                    }
                )
            } catch (e: Exception) {
                uiState.value = UiState.Error(e)
            }
        }
    }

    sealed class UiState {

        object Loading : UiState()
        data class Success(val dogs: List<Dog>) : UiState()
        data class Error(val exception: Throwable) : UiState()
    }

}
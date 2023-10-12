package com.jammes.doginfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.doginfo.core.model.DogDomain
import com.jammes.doginfo.core.repository.DogApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val repository: DogApiServiceImpl
): ViewModel() {

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
    lateinit var currentDogName: String

    fun getDogs(dogName: String) {
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

    fun stateOnceAndStream() : LiveData<UiState> = uiState

    fun getCurrentDog(): DogDomain? {
        val uiStateValue = uiState.value

        if (uiStateValue is UiState.Success) {
            val dogsList = uiStateValue.dogs

            return dogsList.find { it.name == currentDogName }
        }

        return null
    }

    sealed class UiState {

        object Loading : UiState()
        data class Success(val dogs: List<DogDomain>) : UiState()
        data class Error(val exception: Throwable) : UiState()
    }

}
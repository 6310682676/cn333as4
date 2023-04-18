package com.example.cn333as4.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewsModels: ViewModel(){

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private var height by mutableStateOf(8)
    private var width by mutableStateOf(8)
    private var categoryType by mutableStateOf("movie")

    init {
        resetGame()
    }

    private fun resetGame() {
        _uiState.value = AppUiState(
            heightInvalid = false,
            widthInvalid = false
        )
    }

    fun updateURL(){
        _uiState.update { currentState ->
            currentState.copy(
                url = "https://loremflickr.com/$width/$height/$categoryType"
            )
        }
    }

    fun updateUserInput(
        value: String,
        type: String
    ){
        val value = value.toIntOrNull()
        if(value == null){
            if(type.equals("height", ignoreCase = true)){
                _uiState.update { currentState ->
                    currentState.copy(
                        heightInvalid = true
                    )
                }
            }else if(type.equals("width", ignoreCase = true)){
                _uiState.update { currentState ->
                    currentState.copy(
                        widthInvalid = true
                    )
                }
            }
        }else{
            if(type.equals("height", ignoreCase = true)){
                height = value.toInt()
                _uiState.update { currentState ->
                    currentState.copy(
                        widthInvalid = false
                    )
                }
            }else if(type.equals("width", ignoreCase = true)){
                width = value.toInt()
                _uiState.update { currentState ->
                    currentState.copy(
                        widthInvalid = false
                    )
                }
            }
        }

    }
}

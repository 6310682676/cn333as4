package com.example.cn333as4.ui

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewsModels: ViewModel(){

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = AppUiState(
            heightInvalid = false,
            widthInvalid = false
        )
    }


    fun UpdateUserInput(
        value: String,
        type: String
    ){
        val value = if(value == "") "0" else value
        if(value.toInt() > 2000 || value.toInt() < 8 ){
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
        }

    }
}

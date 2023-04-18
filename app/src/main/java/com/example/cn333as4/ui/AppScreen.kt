package com.example.cn333as4.ui

//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

val choices = listOf("aaaaaa", "bbbbbb", "cccccc", "dddddd", "eeeeee", "ffffff")


@Composable
fun FirstScreen (
    modifier: Modifier = Modifier,
    gameViewModel: AppViewsModels = viewModel()
){
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Random Image",
            fontSize = 40.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(16.dp))
        var height by remember { mutableStateOf("") }
        var width by remember { mutableStateOf("") }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Enter Image Width:", modifier = Modifier.padding(end = 8.dp))
            OutlinedTextField(
                value = width,
                onValueChange = { width = it
                                gameViewModel.updateUserInput(it, "width")},
                label = { Text(text = "Image size") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        if(gameUiState.widthInvalid){

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Enter Image Height:", modifier = Modifier.padding(end = 8.dp))
            OutlinedTextField(
                value = height,
                onValueChange = {height = it
                    gameViewModel.updateUserInput(it, "height")},
                label = { Text(text = "Image size") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        if(gameUiState.heightInvalid){

        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Category")
        Spacer(modifier = Modifier.height(16.dp))
        val selectedOption = remember { mutableStateOf("") }
        Row {
            choices.take(3).forEach { option ->
                RadioButton(
                    selected = selectedOption.value == option,
                    onClick = { selectedOption.value = option }
                )
                Text(text = option)
            }
        }
        Row {
            choices.takeLast(3).forEach { option ->
                RadioButton(
                    selected = selectedOption.value == option,
                    onClick = { selectedOption.value = option }
                )
                Text(text = option)
            }
        }
    }


}
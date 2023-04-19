package com.example.cn333as4.ui

//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cn333as4.AppScreen

val choices = listOf("dog", "cat", "human", "dddddd", "eeeeee", "ffffff")


@Composable
fun FirstScreen (
    modifier: Modifier = Modifier,
    gameViewModel: AppViewsModels,
    navController: NavController
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
            Text(text = "Enter Image Width: ", modifier = Modifier.padding(end = 8.dp))
            Box(
                modifier = Modifier.size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                if(gameUiState.enterWidth){
                    if(gameUiState.widthInvalid){
                        Text(text = "\uDC4D") //wait to add wrong emoji
                    }
                    else{
                        Text(text = "\uD83D\uDC4D")
                    }
                }

            }
            OutlinedTextField(
                value = width,
                onValueChange = { width = it
                                gameViewModel.updateUserInput(it, "width")},
                label = { Text(text = "Image Width") },
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
            Box(
                modifier = Modifier.size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                if(gameUiState.enterHeight){
                    if(gameUiState.heightInvalid){
                        Text(text = "\uDC4D") //wait to add wrong emoji
                    }
                    else{
                        Text(text = "\uD83D\uDC4D")
                    }
                }
            }
            OutlinedTextField(
                value = height,
                onValueChange = {height = it
                    gameViewModel.updateUserInput(it, "height")},
                label = { Text(text = "Image Height") },
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
                    onClick = { selectedOption.value = option
                        gameViewModel.updateCategory(option)
                    }
                )
                Text(text = option)
            }
        }
        Row {
            choices.takeLast(3).forEach { option ->
                RadioButton(
                    selected = selectedOption.value == option,
                    onClick = { selectedOption.value = option
                            gameViewModel.updateCategory(option)
                    }
                )
                Text(text = option)
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        if(gameUiState.inputAllValid){
            Button(
                onClick = {
                    navController.navigate(AppScreen.Second.name)
                },
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                Text(text = "Button with border", color = Color.DarkGray)
            }
        }


    }
}

@Composable
fun SecondScreen(
    gameViewModel: AppViewsModels,
    navController: NavController
){
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "\u2B50 "+"Your Result" + " \u2B50",
            fontSize = 40.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic)

        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(gameUiState.url)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Yellow)
        )

        Button(
            onClick = {
                navController.navigate(AppScreen.Start.name)
                gameViewModel.resetGame()
            },
            border = BorderStroke(1.dp, Color.Red),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text(text = "Restart", color = Color.DarkGray)
        }


    }

}
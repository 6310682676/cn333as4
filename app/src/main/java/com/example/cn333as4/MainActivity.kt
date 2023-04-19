package com.example.cn333as4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cn333as4.ui.AppViewsModels
import com.example.cn333as4.ui.FirstScreen
import com.example.cn333as4.ui.theme.Cn333as4Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cn333as4.ui.SecondScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cn333as4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Cn333as4Theme {
        Greeting("Android")
    }
}

enum class AppScreen( val title: String) {
    Start(title = "Home"),
    Second(title = "Image"),
}
@Composable
fun App(
    modifier: Modifier = Modifier,
    gameViewModel: AppViewsModels = viewModel()
){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Start.name
    )

    Scaffold(
//        topBar = {
//            GameAppBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
//            )
//        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = AppScreen.Start.name) {
                FirstScreen(
                    gameViewModel= gameViewModel,
                    navController = navController
                )
            }

            composable(route = AppScreen.Second.name) {
                val context = LocalContext.current
                SecondScreen(
                    gameViewModel= gameViewModel,
                    navController = navController
                )
            }

        }
    }
}

@Composable
fun GameAppBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text((currentScreen.title)) },
        modifier = modifier,
        backgroundColor = Color(0, 106, 125, 232),
        contentColor = Color(255,255,255),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )


}
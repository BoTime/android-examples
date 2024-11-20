package com.botime.learning.layoutexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MyBottomNavigation(navController = navController) }) {
        Box(Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = ColumnScreen.route) {
                composable(ColumnScreen.route) {
                    ColumnScreen()
                }
                composable(RowScreen.route) {
                    RowScreen()
                }
            }
        }
    }
}

@Composable
fun MyBottomNavigation(navController: NavController) {
    val destinationList = listOf(
        ColumnScreen,
        RowScreen
    )
    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    navController.addOnDestinationChangedListener { controller, currentDestination, arguments ->
        destinationList.forEachIndexed { index, destination ->
            if (currentDestination.route == destination.route) selectedTabIndex = index
        }
    }

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.green_sage)
    ) {
        destinationList.forEachIndexed { index, _ ->
            BottomNavigationItem(selected = selectedTabIndex == index,
                label = { Text(text = destinationList[index].route) },
                onClick = {
                    navController.navigate(destinationList[index].route) {
                        popUpTo(ColumnScreen.route)
                        launchSingleTop = true
                    }
                    selectedTabIndex = index
                },
                icon = {
                    Icon(
                        destinationList[index].icon,
                        contentDescription = destinationList[index].route
                    )
                })
        }

    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}

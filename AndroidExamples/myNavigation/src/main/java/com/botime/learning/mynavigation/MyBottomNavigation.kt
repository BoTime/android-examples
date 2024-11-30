package com.botime.learning.mynavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp(startDestination: Destination, destinationsMappping: Map<Destination, @Composable() () -> Unit>) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MyBottomNavigation(
                navController = navController,
                destinationsMappping.keys.toList()
            )
        }) {
        Box(Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = startDestination.route) {
                for ((destination, screen) in destinationsMappping.entries) {
                    composable(destination.route) {
                        screen()
                    }
                }
            }
        }
    }
}

@Composable
fun MyBottomNavigation(navController: NavController, destinations: List<Destination>) {
    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    navController.addOnDestinationChangedListener { controller, currentDestination, arguments ->
        destinations.forEachIndexed { index, destination ->
            if (currentDestination.route == destination.route) selectedTabIndex = index
        }
    }

    BottomNavigation {
        destinations.forEachIndexed { index, _ ->
            BottomNavigationItem(selected = selectedTabIndex == index,
                label = { Text(text = destinations[index].route) },
                onClick = {
                    navController.navigate(destinations[index].route) {
                        popUpTo(destinations[0].route)
                        launchSingleTop = true
                    }
                    selectedTabIndex = index
                },
                icon = {
                    Icon(
                        destinations[0].icon ?: Icons.Rounded.QuestionMark,
                        contentDescription = destinations[index].route
                    )
                })
        }

    }
}
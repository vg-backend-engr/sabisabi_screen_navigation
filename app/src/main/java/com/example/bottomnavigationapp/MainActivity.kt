package com.example.bottomnavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, selectedItem = selectedItem) {
                selectedItem = it
            }
        }
    ) { paddingValues ->
        NavHostContainer(navController = navController, paddingValues = paddingValues, onItemSelected = { selectedItem = it })
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, paddingValues: PaddingValues, onItemSelected: (String) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("Home") {
            HomeScreen()
            onItemSelected("Home")
        }
        composable("Shelf") {
            ShelfScreen()
            onItemSelected("Shelf")
        }
        composable("Invoice") {
            InvoiceScreen()
            onItemSelected("Invoice")
        }
        composable("Expense") {
            ExpenseScreen()
            onItemSelected("Expense")
        }
        composable("Journal") {
            JournalScreen()
            onItemSelected("Journal")
        }
        composable("Todo") {
            TodoScreen()
            onItemSelected("Todo")
        }
        composable("Calendar") {
            CalendarScreen()
            onItemSelected("Calendar")
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, selectedItem: String, onItemSelected: (String) -> Unit) {
    val items = listOf(
        BottomNavItem("Home", Icons.Filled.Home),
        BottomNavItem("Shelf", Icons.Filled.List),
        BottomNavItem("Invoice", Icons.Filled.ShoppingCart),
        BottomNavItem("Expense", Icons.Filled.Payment),
        BottomNavItem("Journal", Icons.Filled.Event),
        BottomNavItem("Todo", Icons.Filled.List),
        BottomNavItem("Calendar", Icons.Filled.DateRange)
    )

    BottomNavigation {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.name,
                        tint = if (selectedItem == item.name) Color.Gray else Color.White // Highlighting logic
                    )
                },
                selected = selectedItem == item.name,
                onClick = {
                    navController.navigate(item.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                    onItemSelected(item.name)
                },
                label = null // Remove text labels
            )
        }
    }
}

data class BottomNavItem(val name: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun HomeScreen() {
    Text("Home Screen")
}

@Composable
fun ShelfScreen() {
    Text("Shelf Screen")
}

@Composable
fun InvoiceScreen() {
    Text("Invoice Screen")
}

@Composable
fun ExpenseScreen() {
    Text("Expense Screen")
}

@Composable
fun JournalScreen() {
    Text("Journal Screen")
}

@Composable
fun TodoScreen() {
    Text("Todo Screen")
}

@Composable
fun CalendarScreen() {
    Text("Calendar Screen")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}

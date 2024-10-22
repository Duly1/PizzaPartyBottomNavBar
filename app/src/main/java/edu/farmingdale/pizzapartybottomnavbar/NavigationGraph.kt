package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    NavHost(navController, startDestination = BottomNavigationItems.Welcome.route) {
        composable(BottomNavigationItems.Welcome.route) {
            onBottomBarVisibilityChanged(false)
            SplashScreen(navController = navController)
        }
        composable(BottomNavigationItems.PizzaScreen.route) {
            onBottomBarVisibilityChanged(true)
           PizzaPartyScreen()
        }
        composable(BottomNavigationItems.GpaAppScreen.route) {
            onBottomBarVisibilityChanged(true)
            GpaAppScreen()
        }
        composable(BottomNavigationItems.Screen3.route) {
            onBottomBarVisibilityChanged(true)
            Screen3()
        }
    }
}

// ToDo 8: This is the homework:
// add a drawer navigation as described in drawable drawermenu.png
// Improve the design and integration of the app for 5 extra credit points.

@Composable
fun DrawerContent(navController: NavHostController, closeDrawer: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Menu", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        DrawerItem("Pizza Party", BottomNavigationItems.PizzaScreen.route, navController, closeDrawer)
        DrawerItem("GPA App", BottomNavigationItems.GpaAppScreen.route, navController, closeDrawer)
        DrawerItem("Slider App", BottomNavigationItems.Screen3.route, navController, closeDrawer)
    }
}

@Composable
fun DrawerItem(label: String, route: String, navController: NavHostController, closeDrawer: () -> Unit) {
    TextButton(onClick = {
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        closeDrawer()
    }) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun TopAppBarWithDrawer(drawerState: DrawerState, scope: CoroutineScope) {
    TopAppBar(
        title = { Text("Pizza Party App") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
            }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}
package com.example.application

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.content.presentation.details.DetailsScreenRoot
import com.example.content.presentation.home.HomeScreenRoot

@Composable
fun NavigationRoot(
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = "home"
  ) {
    homeGraph(navController)
  }
}

private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
  navigation(
    startDestination = "base",
    route = "home"
  ) {
    composable(route = "base") {
      HomeScreenRoot(
        onPokemonClick = { name ->
          navController.navigate("details/$name")
        }
      )
    }
    composable(
      route = "details/{name}",
      arguments = listOf(
        navArgument("name") {
          type = NavType.StringType
        }
      )
    ) { backStackEntry ->
      DetailsScreenRoot()
    }
  }
}
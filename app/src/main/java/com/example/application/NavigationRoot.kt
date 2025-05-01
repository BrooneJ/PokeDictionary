package com.example.application

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.content.presentation.details.DetailsScreenRoot
import com.example.content.presentation.home.HomeScreenRoot

@Composable
fun NavigationRoot(
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = PokeDicScreen.Home
  ) {
    homeGraph(navController)
  }
}

private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
  navigation<PokeDicScreen.Home>(
    startDestination = PokeDicScreen.Base,
  ) {
    composable<PokeDicScreen.Base> {
      HomeScreenRoot(
        onPokemonClick = { name ->
          navController.navigate(PokeDicScreen.Details(name))
        }
      )
    }
    composable<PokeDicScreen.Details> { backStackEntry ->
      val name = backStackEntry.toRoute<PokeDicScreen.Details>()
      DetailsScreenRoot()
    }
  }
}
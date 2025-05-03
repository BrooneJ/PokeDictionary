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
import com.example.core.navigation.PokeDicScreen

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
        onPokemonClick = { pokemon ->
          navController.navigate(PokeDicScreen.Details(pokemon))
        }
      )
    }
    composable<PokeDicScreen.Details>(
      typeMap = PokeDicScreen.Details.typeMap
    ) { backStackEntry ->
      val pokemon = backStackEntry.toRoute<PokeDicScreen.Details>()
      backStackEntry.savedStateHandle.set("pokemon", pokemon.pokemon)
      DetailsScreenRoot(pokemon = pokemon.pokemon)
    }
  }
}
package com.dnkilic.warofsuits.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dnkilic.player.ui.PlayerScreen
import com.dnkilic.warofsuits.game.ui.GameScreen
import com.dnkilic.warofsuits.game.ui.GameViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WarOfSuitsNavigation(
    navController: NavHostController,
    gameViewModel: GameViewModel
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Player
    ) {
        composable(NavigationDestination.Player) {
            PlayerScreen { userName ->
                navController.navigate(NavigationDestination.Game.getDestination(userName))
            }
        }
        composable(
            route = NavigationDestination.Game.route,
            arguments = NavigationDestination.Game.argumentList
        ) { backStackEntry ->
            val userName = NavigationDestination.Game.parseArguments(backStackEntry).userName
            GameScreen(
                userName = userName,
                gameViewModel = gameViewModel
            )
        }
    }
}

object NavigationDestination {
    const val Player = "player"
    class Game {
        data class Args(val userName: String)

        companion object {
            private const val Game = "game"
            private const val UserName = "UserName"
            const val route = "$Game?$UserName={$UserName}"

            fun parseArguments(backStackEntry: NavBackStackEntry): Args {
                return Args(userName = requireNotNull(backStackEntry.arguments?.getString(UserName)))
            }

            val argumentList: MutableList<NamedNavArgument>
                get() = mutableListOf(
                    navArgument(UserName) {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )

            fun getDestination(userName: String): String {
                return "$Game?$UserName=${userName}"
            }
        }
    }
}
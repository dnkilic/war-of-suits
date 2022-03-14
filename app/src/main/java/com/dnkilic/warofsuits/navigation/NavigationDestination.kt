package com.dnkilic.warofsuits.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

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

    class GameResult {
        data class Args(val playerScore: Int, val opponentScore: Int, val playerName: String)

        companion object {
            private const val GameResult = "gameResult"
            private const val PlayerScore = "PlayerScore"
            private const val OpponentScore = "OpponentScore"
            private const val PlayerName = "PlayerName"
            const val route = "$GameResult/{$PlayerScore}/{$OpponentScore}/{$PlayerName}"

            fun parseArguments(backStackEntry: NavBackStackEntry): Args {
                return Args(
                    playerScore = requireNotNull(
                        backStackEntry.arguments?.getInt(PlayerScore)
                    ),
                    opponentScore = requireNotNull(
                        backStackEntry.arguments?.getInt(OpponentScore)
                    ),
                    playerName = requireNotNull(
                        backStackEntry.arguments?.getString(PlayerName)
                    ),
                )
            }

            val argumentList: MutableList<NamedNavArgument>
                get() = mutableListOf(
                    navArgument(PlayerScore) {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument(OpponentScore) {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument(PlayerName) {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )

            fun getDestination(playerScore: Int, opponentScore: Int, playerName: String): String {
                return "$GameResult/${playerScore}/${opponentScore}/${playerName}"
            }
        }
    }
}
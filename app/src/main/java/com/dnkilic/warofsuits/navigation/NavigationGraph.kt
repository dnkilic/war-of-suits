package com.dnkilic.warofsuits.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.plusAssign
import com.dnkilic.warofsuits.player.ui.PlayerScreen
import com.dnkilic.warofsuits.game.ui.GameScreen
import com.dnkilic.warofsuits.game.ui.GameViewModel
import com.dnkilic.warofsuits.result.model.GameResultUiState
import com.dnkilic.warofsuits.result.ui.GameResultScreen
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun WarOfSuitsNavigation(
    navController: NavHostController,
    gameViewModel: GameViewModel
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator
    ) {
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
                gameViewModel.resetGame()
                val userName = NavigationDestination.Game.parseArguments(backStackEntry).userName
                GameScreen(
                    userName = userName,
                    gameViewModel = gameViewModel,
                    onStartGame = { gameViewModel.startGame() },
                    onPlayCard = { gameViewModel.playNextCard() },
                    onResetGame = { gameViewModel.resetGame() },
                    onEndGame = { playerScore, opponentScore ->
                        navController.navigate(
                            NavigationDestination.GameResult.getDestination(
                                playerScore = playerScore,
                                opponentScore = opponentScore,
                                playerName = userName
                            )
                        )
                    }
                )
            }
            bottomSheet(
                route = NavigationDestination.GameResult.route,
                arguments = NavigationDestination.GameResult.argumentList
            ) { backStackEntry ->
                val uiState = GameResultUiState(
                    NavigationDestination.GameResult.parseArguments(backStackEntry).playerScore,
                    NavigationDestination.GameResult.parseArguments(backStackEntry).opponentScore,
                    NavigationDestination.GameResult.parseArguments(backStackEntry).playerName
                )

                GameResultScreen(
                    uiState = uiState,
                    onPlayAgain = {
                        navController.navigateUp()
                        gameViewModel.resetGame()
                    }
                )
            }
        }
    }
}
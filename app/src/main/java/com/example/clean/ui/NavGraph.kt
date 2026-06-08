package com.example.clean.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clean.domain.model.User
import com.example.clean.ui.features.home.HomeScreen
import com.example.clean.ui.features.settings.SettingsScreen
import com.example.clean.ui.features.splash.SplashScreen
import com.example.clean.ui.features.userrepository.UserRepoScreen
import com.example.clean.ui.features.userrepository.UserRepoScreenViewModel
import com.example.clean.ui.features.userrepository.UserSaver
import com.example.clean.ui.features.users.UsersListScreen
import com.example.clean.ui.features.users.UsersListViewModel

/**
 * Navigation destinations for the app
 */
object MainDestinations {
    const val SPLASH_SCREEN_ROUTE = "splash"
    const val HOME_SCREEN_ROUTE = "home"
    const val USERS_LIST_SCREEN_ROUTE = "users_list"
    const val USER_REPO_SCREEN_ROUTE = "user_repo"
    const val SETTINGS_SCREEN_ROUTE = "settings"
}

/**
 * Navigation graph for the app
 * Integrates splash, home with tabs, users list, and settings
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = MainDestinations.SPLASH_SCREEN_ROUTE
) {
    var searchedUser by rememberSaveable(stateSaver = UserSaver) { mutableStateOf(User()) }

    NavHost(navController = navController, startDestination = startDestination) {
        // Splash Screen
        composable(MainDestinations.SPLASH_SCREEN_ROUTE) {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate(MainDestinations.HOME_SCREEN_ROUTE) {
                        // Pop splash screen from back stack
                        popUpTo(MainDestinations.SPLASH_SCREEN_ROUTE) { inclusive = true }
                    }
                }
            )
        }

        // Home Screen with Tabs
        composable(MainDestinations.HOME_SCREEN_ROUTE) {
            val usersViewModel: UsersListViewModel = hiltViewModel()

            HomeScreen(
                usersListContent = {
                    UsersListScreen(
                        viewModel = usersViewModel,
                        onClick = {
                            searchedUser = it
                            navController.navigate(MainDestinations.USER_REPO_SCREEN_ROUTE)
                        }
                    )
                },
                settingsContent = {
                    SettingsScreen()
                }
            )
        }

        // Users List Screen (standalone, for backwards compatibility)
        composable(MainDestinations.USERS_LIST_SCREEN_ROUTE) {
            val viewModel: UsersListViewModel = hiltViewModel()
            UsersListScreen(
                viewModel = viewModel,
                onClick = {
                    searchedUser = it
                    navController.navigate(MainDestinations.USER_REPO_SCREEN_ROUTE)
                }
            )
        }

        // User Repository Details Screen
        composable(MainDestinations.USER_REPO_SCREEN_ROUTE) {
            val viewModel: UserRepoScreenViewModel = hiltViewModel()
            viewModel.getUserRepositories(searchedUser)

            UserRepoScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Settings Screen (standalone, for future use)
        composable(MainDestinations.SETTINGS_SCREEN_ROUTE) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
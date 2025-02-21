package com.example.finalsecure.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalsecure.ui.login.screens.HomeContent
import com.example.finalsecure.ui.login.screens.LoginContent
import com.example.finalsecure.ui.login.screens.RegisterContent
import com.example.finalsecure.ui.login.viewmodel.LoginViewModel
import com.example.finalsecure.ui.login.viewmodel.RegisterViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val loginViewModel = LoginViewModel()
    val registerViewModel = RegisterViewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.LoginScreen.route
    ) {
        composable(AppScreen.LoginScreen.route) {
            LoginContent(loginViewModel, navController)
        }

        composable(AppScreen.HomeScreen.route) {
            HomeContent(navController)
        }

        composable(AppScreen.RegisterScreen.route) {
            RegisterContent(registerViewModel, navController)
        }
    }
}
package com.example.finalsecure.navigation

sealed class AppScreen(
    val route: String
) {
    data object LoginScreen: AppScreen("LoginContent")
    data object HomeScreen: AppScreen("HomeContent")
    data object RegisterScreen: AppScreen("RegisterContent")
}
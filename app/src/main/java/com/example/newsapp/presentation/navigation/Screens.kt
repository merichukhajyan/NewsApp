package com.example.newsapp.presentation.navigation

sealed class Screen(
    val route: String,
    val name: String
) {
    object HomeScreen : Screen(route = "home_screen", name = "Home")
    object DetailsScreen : Screen(route = "details_screen", name = "Details")
}
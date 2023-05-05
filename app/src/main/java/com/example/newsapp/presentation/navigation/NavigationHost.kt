package com.example.newsapp.presentation.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.detailsScreen.DetailsScreen
import com.example.newsapp.presentation.homeScreen.HomeScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeNavigation(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.DetailsScreen.route + "/{article}"){entry ->
        val article = entry.arguments?.getParcelable<Article>("article")
            if (article != null) {
                DetailsScreen(item = article, navController = navController)
            }
        }
        }
    }
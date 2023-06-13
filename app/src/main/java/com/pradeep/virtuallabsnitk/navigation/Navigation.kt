package com.pradeep.virtuallabsnitk.navigation

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pradeep.virtuallabsnitk.onboarding.OnBoardingScreen
import com.pradeep.virtuallabsnitk.onboarding.SharedPref
import com.pradeep.virtuallabsnitk.screens.HomeScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreen.OnBoardingScreen.route) {
        composable(route = NavigationScreen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = NavigationScreen.SplashScreen.route) {
            SplashScreen(navHostController = navController)
        }
        composable(route = NavigationScreen.OnBoardingScreen.route) {
            val mySharedPref = SharedPref(LocalContext.current)
            if (mySharedPref.getFirstTimeUser()) {
                OnBoardingScreen(navController)
            } else {
                SplashScreen(navHostController = navController)
            }
        }
    }
}

sealed class NavigationScreen(val route: String) {
    object HomeScreen : NavigationScreen("homescreen")
    object SplashScreen : NavigationScreen("splashscreen")
    object OnBoardingScreen : NavigationScreen("onboardingscreen")
}
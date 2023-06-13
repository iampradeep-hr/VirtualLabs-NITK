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
import com.pradeep.virtuallabsnitk.screens.SampleExperiment
import com.pradeep.virtuallabsnitk.screens.VibrationCardList

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreen.OnBoardingScreen.route) {
        composable(route = NavigationScreen.HomeScreen.route) {
            HomeScreen(navController)
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
        composable(NavigationScreen.MachineDynamics.route){

            VibrationCardList(navController)
        }
        composable(NavigationScreen.SampleExperimentScreen.route){
            SampleExperiment()
        }

    }
}

sealed class NavigationScreen(val route: String) {
    object HomeScreen : NavigationScreen("home_screen")
    object SplashScreen : NavigationScreen("splash_screen")
    object OnBoardingScreen : NavigationScreen("onboarding_screen")
    object MachineDynamics:NavigationScreen("machine_dynamics_screen")
    object SampleExperimentScreen:NavigationScreen("sample_experiment_screen")

}
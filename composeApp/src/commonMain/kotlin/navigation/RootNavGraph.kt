package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import home.DetailsScreen
import login.LoginScreen
import onboarding.OnboardingScreen
import signup.SignUpScreen
import util.AnimationConfig

@Composable
fun RootNavGraph(onSystemBarsVisibilityChanged: (Boolean) -> Unit) {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = NavigationScreen.Splash.route
    ) {
        composable(
            route = NavigationScreen.Splash.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            OnboardingScreen(
                navController = navController,
                onSystemBarsVisibilityChanged = onSystemBarsVisibilityChanged
            )
//                navController.navigate(NavigationScreen.Login.route) {
//                    popUpTo(NavigationScreen.Splash.route) {
//                        inclusive = true
//                    }
        }

        composable(route = NavigationScreen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = NavigationScreen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
//        composable(route = NavigationScreen.HomeScreen.route) {
//            HomeScreen(navController = navController)
//        }
        composable(route = NavigationScreen.Details.route) {
            DetailsScreen(navController = navController)
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
}

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen("splash")
    data object Login : NavigationScreen("login")
    data object SignUp : NavigationScreen("signup")
    data object HomeScreen : NavigationScreen("home")
    data object Details : NavigationScreen("detail")
    data object PdfWebView : NavigationScreen("PdfWebView")
}
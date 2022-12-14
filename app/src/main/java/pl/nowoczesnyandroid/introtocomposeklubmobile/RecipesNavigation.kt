package pl.nowoczesnyandroid.introtocomposeklubmobile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class RecipeDestination(
    val route: String,
) {

    object Recipes : RecipeDestination(route = "recipes")

    object Recipe : RecipeDestination(route = "recipe/{recipeId}") {

        fun createRoute(recipeId: String): String =
            "recipe/$recipeId"
    }
}

@ExperimentalAnimationApi
@Composable
fun RecipesNavigation(
    navController: NavHostController = rememberAnimatedNavController(),
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = RecipeDestination.Recipes.route,
    ) {
        composable(route = RecipeDestination.Recipes.route) {
            RecipesList(
                onRecipeClick = { recipe ->
                    navController.navigate(RecipeDestination.Recipe.createRoute(recipeId = recipe.title))
                }
            )
        }
        composable(
            route = RecipeDestination.Recipe.route,
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                }
            )
        ) {
            val recipeTitle = requireNotNull(it.arguments?.getString("recipeId"))
            RecipeDetails(
                recipeId = recipeTitle,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

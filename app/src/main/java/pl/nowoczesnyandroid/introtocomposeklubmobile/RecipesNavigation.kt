package pl.nowoczesnyandroid.introtocomposeklubmobile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class SongDestination(
    val route: String,
) {

    object Recipes : SongDestination(route = "recipes")

    object Recipe : SongDestination(route = "recipe/{recipeId}") {

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
        startDestination = SongDestination.Recipes.route,
    ) {
        composable(route = SongDestination.Recipes.route) {
            RecipeList(
                onRecipeClick = { recipe ->
                    navController.navigate(SongDestination.Recipe.createRoute(recipeId = recipe.title))
                }
            )
        }
        composable(
            route = SongDestination.Recipe.route,
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                }
            )
        ) {
            val recipeTitle = requireNotNull(it.arguments?.getString("recipeId"))
            RecipeDetails(
                recipeTitle = recipeTitle,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

package pl.nowoczesnyandroid.introtocomposeklubmobile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RecipesList(
    recipes: List<Recipe> = defaultRecipes,
    onRecipeClick: (Recipe) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Recipes") })
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                val sharedModifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Red)

                item {
                    Text(modifier = sharedModifier, text = "Start")
                }

                items(
                    items = recipes,
                    key = { it.title },
                ) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onRecipeClick = onRecipeClick,
                    )
                }

                item {
                    Text(modifier = sharedModifier, text = "Stop")
                }
            }
        )
    }
}

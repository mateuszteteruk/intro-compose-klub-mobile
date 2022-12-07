package pl.nowoczesnyandroid.introtocomposeklubmobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCard(
    recipe: Recipe,
    onRecipeClick: (Recipe) -> Unit,
) {
    Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { onRecipeClick(recipe) },
        ) {
            Image(
                painter = painterResource(id = recipe.imageResource),
                contentDescription = recipe.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color.White, Color.Transparent),
                            center = Offset(0F, Float.POSITIVE_INFINITY),
                            radius = 1000F,
                        )
                    )
            )
            Text(
                text = recipe.title,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                style = MaterialTheme.typography.h4
            )
        }
    }
}

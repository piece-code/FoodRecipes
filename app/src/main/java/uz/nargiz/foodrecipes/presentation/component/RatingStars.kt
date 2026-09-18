package uz.nargiz.foodrecipes.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

@Composable
fun RatingStars(
    rating: Float = 4.5f,
    starSize: Dp = 20.dp
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..5) {
            when {
                i <= rating.toInt() ->
                    Image(
                        painter = painterResource(R.drawable.icon_star_filled),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(starSize)
                    )
                i - rating < 1f && i - rating > 0f ->
                    Image(
                        painter = painterResource(R.drawable.icon_star_half),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(starSize)
                    )
                else ->
                    Icon(
                        painter = painterResource(R.drawable.icon_star_filled),
                        contentDescription = null,
                        tint = Color(0xff_F5F5F7),
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(starSize)
                    )
            }
        }
    }
}

@[Preview Composable]
private fun Preview() {
    FoodRecipesTheme {
        RatingStars()
    }
}
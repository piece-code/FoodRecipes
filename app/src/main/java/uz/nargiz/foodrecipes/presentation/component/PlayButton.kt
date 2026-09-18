package uz.nargiz.foodrecipes.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .size(54.dp),
        shape = CircleShape,
        colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.onPrimary)
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_play),
            contentDescription = "play",
            tint = MaterialTheme.colorScheme.primary,
            modifier = modifier.fillMaxSize()
        )
    }
}

@[Composable Preview]
private fun Preview() {
    FoodRecipesTheme {
        PlayButton(
            onClick = {},
        )
    }
}
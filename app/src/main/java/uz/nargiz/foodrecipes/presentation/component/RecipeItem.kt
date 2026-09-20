package uz.nargiz.foodrecipes.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uz.nargiz.foodrecipes.data.source.local.Repository
import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

@Composable
fun RecipeItem(
    data: RecipeData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Column {
            AsyncImage(
                model = data.imageUrl,
                contentDescription = data.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = data.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = data.primaryCategory,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@[Preview Composable]
private fun PreviewRecipeItem() {
    FoodRecipesTheme {
        RecipeItem(
            data = Repository.recipeData,
            onClick = {}
        )
    }
}
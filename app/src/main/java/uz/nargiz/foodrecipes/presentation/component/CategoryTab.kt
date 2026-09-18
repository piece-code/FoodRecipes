package uz.nargiz.foodrecipes.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

@Composable
fun CategoryTab(
    data: CategoryData,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(top = 4.dp)
            .padding(horizontal = 6.dp)
            .width(IntrinsicSize.Max)
            .height(40.dp)
    ) {
        Text(
            text = data.nameUz,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 2.dp),
            color = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@[Preview Composable]
private fun PreviewCategoryTab() {
    FoodRecipesTheme {
        CategoryTab(
            data = CategoryData(key = "baliq", nameUz = "Baliqli taomlar", nameRu = "Рыбные блюда", icon = "🐟", count = 0),
            isSelected = true,
            onClick = {}
        )
    }
}

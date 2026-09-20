package uz.nargiz.foodrecipes.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.nargiz.foodrecipes.domain.model.AppLanguage
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

@Composable
fun CategoryTab(
    data: CategoryData,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    lang: AppLanguage
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary
                              else MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = 2.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.tertiary)
    ) {
        Text(
            text = "${data.icon} ${if (lang == AppLanguage.RU) data.nameRu else data.nameUz}",
            modifier = Modifier
                .padding(horizontal = 1.dp),
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@[Preview Composable]
private fun PreviewCategoryTab() {
    FoodRecipesTheme {
        CategoryTab(
            data = CategoryData(key = "baliq", nameUz = "Baliqli taomlar", nameRu = "Рыбные блюда", icon = "🐟", count = 0),
            isSelected = false,
            onClick = {},
            lang = AppLanguage.UZ
        )
    }
}

package uz.nargiz.foodrecipes.presentation.screen.category

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.domain.model.CategoryData
import uz.nargiz.foodrecipes.presentation.component.RecipeItem
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

class CategoryScreen (
    private val category: CategoryData
): Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel: CategoryContract.ViewModel = getViewModel<CategoryViewModel>()
        LaunchedEffect(Unit) {
            viewModel.onEventDispatcher(CategoryContract.Event.Init(category.key))
            viewModel.container.sideEffectFlow.collect {
                when(it) {
                    is CategoryContract.SideEffect.Message -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        FoodRecipesTheme {
            ScreenContent(
                category = category,
                uiState = viewModel.collectAsState().value,
                onEvent = viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
private fun ScreenContent(
    category: CategoryData,
    uiState: CategoryContract.UIState,
    onEvent: (CategoryContract.Event) -> Unit
) {
    val recipes = uiState.recipes.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(64.dp)
        ) {
            IconButton(
                onClick = { onEvent(CategoryContract.Event.Back) },
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterStart),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = "back",
                    modifier = Modifier.padding(3.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                text = "${if (uiState.language == "uz") category.nameUz else category.nameRu} ${category.icon}",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        LazyVerticalGrid(
            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
            modifier = Modifier
        ) {
            if (recipes.loadState.refresh == LoadState.Loading || recipes.loadState.append == LoadState.Loading) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 24.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            } else if (recipes.itemCount == 0) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 24.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_recipes_found),
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            items(recipes.itemCount) { index ->
                recipes[index]?.let { recipe ->
                    RecipeItem(
                        data = recipe,
                        onClick = { onEvent(CategoryContract.Event.Recipe(recipe)) },
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .padding(
                                top = 12.dp,
                                start = if (index % 2 == 0) 10.dp else 0.dp,
                                end = if (index % 2 == 1) 10.dp else 0.dp
                            )
                    )
                }
            }
        }
    }
}

@[Preview Composable]
private fun Preview() {
    FoodRecipesTheme {
        ScreenContent(
            category = CategoryData("", "Nonushta", "", "\uD83C\uDF05", 34),
            uiState = CategoryContract.UIState(),
            onEvent = {}
        )
    }
}
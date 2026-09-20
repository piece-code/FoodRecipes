package uz.nargiz.foodrecipes.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.paging.compose.collectAsLazyPagingItems
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.domain.model.AppLanguage
import uz.nargiz.foodrecipes.presentation.component.CategoryTab
import uz.nargiz.foodrecipes.presentation.component.RecipeItem
import uz.nargiz.foodrecipes.presentation.component.RecommendedItem
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel: HomeContract.ViewModel = getViewModel<HomeViewModel>()
        LaunchedEffect(Unit) {
            viewModel.container.sideEffectFlow.collect {
                when(it) {
                    is HomeContract.SideEffect.Message -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        FoodRecipesTheme {
            ScreenContent(
                uiState = viewModel.collectAsState().value,
                onEvent = viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
private fun ScreenContent(
    uiState: HomeContract.UIState,
    onEvent: (HomeContract.Event) -> Unit
) {
    val recipes = uiState.recipes.collectAsLazyPagingItems()
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, end = 16.dp)
                    .height(64.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.food_recipes),
                    modifier = Modifier.align(Alignment.CenterStart),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            onEvent(HomeContract.Event.Language(uiState.language))
                        },
                        modifier = Modifier
                            .padding(end = 12.dp),
                    ) {
                        Text(
                            text = uiState.language.uppercase(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    IconButton(
                        onClick = { onEvent(HomeContract.Event.Search()) },
                        modifier = Modifier
                            .size(36.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.img_search),
                            contentDescription = "search",
                            modifier = Modifier.padding(8.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Button(
                onClick = { onEvent(HomeContract.Event.Search(false)) },
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = stringResource(R.string.search_by_ingridents),
                    modifier = Modifier
                        .padding(horizontal = 1.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            uiState.recommended?.let {
                RecommendedItem(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    recipe = uiState.recommended,
                    onClick = { onEvent(HomeContract.Event.RecipeRecommended(uiState.recommended)) }
                )
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            LazyRow(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 16.dp)
                    .height(40.dp)
                    .fillMaxWidth(),
            ) {
                item { Spacer(modifier = Modifier.size(10.dp)) }
                items(uiState.categories) {
                    CategoryTab(
                        data = it,
                        isSelected = false,
                        onClick = { onEvent(HomeContract.Event.Category(it)) },
                        modifier = Modifier
                            .padding(horizontal = 6.dp),
                        lang = if (uiState.language == "uz") AppLanguage.UZ else AppLanguage.RU
                    )
                }
                item { Spacer(modifier = Modifier.size(10.dp)) }
            }
        }
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
        }
        items(recipes.itemCount) { index ->
            recipes[index]?.let { recipe ->
                RecipeItem(
                    data = recipe,
                    onClick = { onEvent(HomeContract.Event.Recipe(recipe)) },
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

@[Preview Composable]
private fun PreviewScreenContent() {
    FoodRecipesTheme {
        ScreenContent(
            uiState = HomeContract.UIState(),
            onEvent = {}
        )
    }
}

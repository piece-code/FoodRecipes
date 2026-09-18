package uz.nargiz.foodrecipes.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.presentation.component.CategoryTab
import uz.nargiz.foodrecipes.presentation.component.RecipeItem
import uz.nargiz.foodrecipes.presentation.component.RecommendedItem
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel: HomeContract.ViewModel = getViewModel<HomeViewModel>()
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { onEvent(HomeContract.Event.Back) },
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterStart),
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = "back",
                    modifier = Modifier.padding(3.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(
                onClick = { onEvent(HomeContract.Event.Menu) },
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterEnd),
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_menu),
                    contentDescription = "menu",
                    modifier = Modifier.padding(3.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Text(
            text = stringResource(R.string.our_recipes),
            modifier = Modifier.padding(start = 20.dp),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayLarge
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 24.dp)
                .height(40.dp)
                .fillMaxWidth(),
        ) {
            item { Spacer(modifier = Modifier.size(10.dp)) }
            items(uiState.category) {
                CategoryTab(
                    data = it,
                    isSelected = it == uiState.selectedCategory,
                    onClick = { onEvent(HomeContract.Event.Category(it)) }
                )
            }
            item { Spacer(modifier = Modifier.size(10.dp)) }
        }
        val transactions = uiState.recipes.collectAsLazyPagingItems()
        LazyRow(
            contentPadding = PaddingValues(start = 20.dp, top = 24.dp, end = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(transactions.itemCount) { index ->
                transactions[index]?.let { recipe ->
                    RecipeItem(
                        data = recipe,
                        onClick = { onEvent(HomeContract.Event.Recipe(recipe)) }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.recommended),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            IconButton(
                onClick = { onEvent(HomeContract.Event.More) }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "more",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(uiState.recommended) { recipe ->
                RecommendedItem(
                    recipe = recipe,
                    onClick = { onEvent(HomeContract.Event.RecipeRecommended(recipe)) }
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

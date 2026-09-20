package uz.nargiz.foodrecipes.presentation.screen.search

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.presentation.component.RecipeItem
import uz.nargiz.foodrecipes.presentation.component.SearchInput
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

class SearchScreen(
    private val isQuery: Boolean
): Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel: SearchContract.ViewModel = getViewModel<SearchViewModel>()
        LaunchedEffect(Unit) {
            viewModel.container.sideEffectFlow.collect {
                when(it) {
                    is SearchContract.SideEffect.Message -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        FoodRecipesTheme {
            ScreenContent(
                isQuery = isQuery,
                uiState = viewModel.collectAsState().value,
                onEvent = viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
private fun ScreenContent(
    isQuery: Boolean,
    uiState: SearchContract.UIState,
    onEvent: (SearchContract.Event) -> Unit
) {
    val query = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            IconButton(
                onClick = { onEvent(SearchContract.Event.Back) },
                modifier = Modifier
                    .padding(start = 16.dp, top = 14.dp)
                    .size(36.dp)
                    .align(Alignment.TopStart),
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
                text = stringResource(R.string.search),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        SearchInput(
            placeholder = if (isQuery) "${stringResource(R.string.search)}..." else "${stringResource(R.string.ingredients)}...",
            value = query.value,
            onValueChange = {
                query.value = it
                onEvent(SearchContract.Event.Search(isQuery = isQuery, query = it))
            },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyVerticalGrid(
            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
            modifier = Modifier
        ) {
            if (uiState.isLoading) {
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
            } else if (uiState.recipes.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 24.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.list_is_empty),
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            items(uiState.recipes.size) { index ->
                RecipeItem(
                    data = uiState.recipes[index],
                    onClick = { onEvent(SearchContract.Event.Recipe(uiState.recipes[index])) },
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
private fun Preview() {
    FoodRecipesTheme {
        ScreenContent(
            isQuery = false,
            uiState = SearchContract.UIState(),
            onEvent = {}
        )
    }
}
package uz.nargiz.foodrecipes.presentation.screen.recipe

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil3.compose.AsyncImage
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import org.orbitmvi.orbit.compose.collectAsState
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.data.source.local.Repository
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.presentation.component.AppButton
import uz.nargiz.foodrecipes.presentation.component.PlayButton
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

class RecipeScreen(
    private val recipe: RecipeDetail.Data
): Screen {
    @Composable
    override fun Content() {
        val viewModel: RecipeContract.ViewModel = getViewModel<RecipeViewModel>()
        FoodRecipesTheme {
            ScreenContent(
                recipe = recipe,
                uiState = viewModel.collectAsState().value,
                onEvent = viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
private fun ScreenContent(
    recipe: RecipeDetail.Data,
    uiState: RecipeContract.UIState,
    onEvent: (RecipeContract.Event) -> Unit
) {
    val hazeState = remember { HazeState() }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
                    .hazeSource(hazeState)
            ) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                PlayButton(
                    modifier = Modifier
                        .align(Alignment.Center),
                    onClick = { onEvent(RecipeContract.Event.Play) }
                )
            }
            Column {
                Spacer(modifier = Modifier.height(300.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeEffect(
                                state = hazeState,
                                style = HazeStyle(
                                    blurRadius = 8.dp,
                                    tint = HazeTint(MaterialTheme.colorScheme.surface)
                                )
                            )
                            .padding(start = 21.dp, end = 21.dp, bottom = 52.dp),
                        contentPadding = PaddingValues(vertical = 30.dp)
                    ) {
                        item {
                            Text(
                                text = recipe.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        item {
                            Text(
                                modifier = Modifier.padding(top = 20.dp),
                                text = recipe.description,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(top = 20.dp).height(1.dp).fillMaxWidth().background(MaterialTheme.colorScheme.onSurfaceVariant))
                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = stringResource(R.string.prep_time),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp),
                                        text = "5 ${stringResource(R.string.minute)}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = stringResource(R.string.cook_time),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp),
                                        text = "15 ${stringResource(R.string.minute)}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = stringResource(R.string.total_time),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp),
                                        text = "20 ${stringResource(R.string.minute)}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.padding(top = 20.dp).height(1.dp).fillMaxWidth().background(MaterialTheme.colorScheme.onSurfaceVariant))
                        }
                        item {
                            Text(
                                modifier = Modifier.padding(vertical = 20.dp),
                                text = stringResource(R.string.ingredients),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        item {
                            recipe.ingredients.forEach { ingredient ->
                                Text(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    text = "${ingredient.amount} ${ingredient.name}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
        IconButton(
            onClick = { onEvent(RecipeContract.Event.Back) },
            modifier = Modifier
                .padding(start = 16.dp, top = 14.dp)
                .size(36.dp)
                .align(Alignment.TopStart),
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_back),
                contentDescription = "back",
                modifier = Modifier.padding(3.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        AppButton(
            onClick = {},
            text = stringResource(R.string.view_full_list),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@[Preview Composable]
private fun Preview() {
    FoodRecipesTheme {
        ScreenContent(
            recipe = Repository.recipe,
            uiState = RecipeContract.UIState(),
            onEvent = {}
        )
    }
}
package uz.nargiz.foodrecipes.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import uz.nargiz.foodrecipes.domain.model.RecipeDetail
import uz.nargiz.foodrecipes.presentation.component.AppButton
import uz.nargiz.foodrecipes.presentation.component.RatingStars
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

class DetailScreen(
    val recipe: RecipeDetail.Data
): Screen {
    @Composable
    override fun Content() {
        val viewModel: DetailContract.ViewModel = getViewModel<DetailViewModel>()
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
    uiState: DetailContract.UIState,
    onEvent: (DetailContract.Event) -> Unit
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
                    model = "",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape),
                                    painter = painterResource(R.drawable.img_burger),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 14.dp)
                                        .weight(1f),
                                    text = "BY JANET BROKOWSKI",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                IconButton(
                                    onClick = { onEvent(DetailContract.Event.Add) },
                                    modifier = Modifier.size(36.dp),
                                    colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.primary)
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(R.drawable.icon_add),
                                        contentDescription = "add",
                                    )
                                }
                            }
                        }
                        item {
                            Text(
                                modifier = Modifier.padding(top = 20.dp),
                                text = "Ginger & Garlic Noosle Soup",
                                style = MaterialTheme.typography.displayLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        item {
                            Row(
                                modifier = Modifier.padding(top = 20.dp),
                            ) {
                                RatingStars(starSize = 16.dp)
                                Text(
                                    modifier = Modifier.padding(start = 8.dp),
                                    text = "4.5k Reviews",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                        item {
                            Text(
                                modifier = Modifier.padding(top = 20.dp),
                                text = "Ginger Garlic Noosle Soup With Bok Choy is a nutritious, comforting, and fiu-fighting twenty minute recipe made with vegetarian broth, noodles, mushrooms, and baby bok choy.",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
        IconButton(
            onClick = { onEvent(DetailContract.Event.Back) },
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
            text = stringResource(R.string.view_recipe),
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
            uiState = DetailContract.UIState(),
            onEvent = {}
        )
    }
}

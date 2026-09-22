package uz.nargiz.foodrecipes.presentation.screen.detail

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
import uz.nargiz.foodrecipes.presentation.component.PlayButton
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme
import uz.nargiz.foodrecipes.util.getAvatar
import androidx.core.net.toUri
import uz.nargiz.foodrecipes.domain.model.RecipeDetail

class DetailScreen(
    val id: Int,
): Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel: DetailContract.ViewModel = getViewModel<DetailViewModel>()
        LaunchedEffect(id) {
            viewModel.onEventDispatcher(DetailContract.Event.Load(id))
        }
        LaunchedEffect(Unit) {
            viewModel.container.sideEffectFlow.collect {
                when(it) {
                    is DetailContract.SideEffect.OpenVideo -> {
                        val intent = Intent(
                            Intent.ACTION_VIEW, it.url.toUri())
                        context.startActivity(intent)
                    }
                    is DetailContract.SideEffect.Message -> {
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
    uiState: DetailContract.UIState,
    onEvent: (DetailContract.Event) -> Unit
) {
    val hazeState = remember { HazeState() }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        if (uiState.recipe != null) {
            Box {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(330.dp)
                        .hazeSource(hazeState)
                ) {
                    AsyncImage(
                        model = uiState.recipe.imageUrl,
                        contentDescription = uiState.recipe.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    if (uiState.recipe.hasVideo) {
                        PlayButton(
                            modifier = Modifier
                                .align(Alignment.Center),
                            onClick = { onEvent(DetailContract.Event.Play) }
                        )
                    }
                }
                Column {
                    Spacer(modifier = Modifier.height(300.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .hazeEffect(
                                    state = hazeState,
                                    style = HazeStyle(
                                        blurRadius = 8.dp,
                                        tint = HazeTint(MaterialTheme.colorScheme.surface.copy(1f))
                                    )
                                ),
                            contentPadding = PaddingValues(vertical = 30.dp, horizontal = 20.dp)
                        ) {
                            item {
                                Text(
                                    text = uiState.recipe.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            item {
                                Row(
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.surface)
                                            .border(
                                                width = 2.dp,
                                                color = MaterialTheme.colorScheme.tertiary,
                                                shape = CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = uiState.recipe.author.getAvatar(),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        modifier = Modifier
                                            .padding(start = 14.dp)
                                            .weight(1f),
                                        text = uiState.recipe.author,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                            item {
                                Text(
                                    modifier = Modifier.padding(top = 16.dp),
                                    text = uiState.recipe.description,
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
                                        .padding(top = 16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = stringResource(R.string.ingredients),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            modifier = Modifier.padding(top = 8.dp),
                                            text = "${uiState.recipe.ingredients.size} ${stringResource(R.string.piece)}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = stringResource(R.string.steps),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            modifier = Modifier.padding(top = 8.dp),
                                            text = "${uiState.recipe.steps.size} ${stringResource(R.string.piece)}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = stringResource(R.string.category),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            modifier = Modifier.padding(top = 8.dp),
                                            text = uiState.recipe.primaryCategory,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.padding(top = 16.dp).height(1.dp).fillMaxWidth().background(MaterialTheme.colorScheme.onSurfaceVariant))
                            }
                            item {
                                Text(
                                    modifier = Modifier.padding(top = 20.dp, end = 16.dp),
                                    text = stringResource(R.string.ingredients),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            item {
                                uiState.recipe.ingredients.forEach { ingredient ->
                                    Row {
                                        Text(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            text = "• ",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Text(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            text = "${ingredient.amount} ${ingredient.name}",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                            item {
                                Text(
                                    modifier = Modifier.padding(top = 20.dp),
                                    text = stringResource(R.string.preparation_stages),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            item {
                                uiState.recipe.steps.forEach { step ->
                                    Text(
                                        modifier = Modifier.padding(top = 16.dp),
                                        text = step.stepLabel,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 8.dp),
                                        text = step.text,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
            IconButton(
                onClick = { onEvent(DetailContract.Event.Back) },
                modifier = Modifier
                    .padding(start = 16.dp, top = 14.dp)
                    .size(40.dp)
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

            IconButton(
                onClick = { onEvent(DetailContract.Event.Save(uiState.recipe.id)) },
                modifier = Modifier
                    .padding(end = 16.dp, top = 14.dp)
                    .size(40.dp)
                    .align(Alignment.TopEnd),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface)
            ) {
                Icon(
                    painter = painterResource(if (uiState.isSaved) R.drawable.img_saved else R.drawable.img_save),
                    contentDescription = "save",
                    modifier = Modifier.padding(8.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        if (uiState.isLoading){
            Dialog(onDismissRequest = {}) { CircularProgressIndicator(color = MaterialTheme.colorScheme.primary) }
        }
    }
}

@[Preview Composable]
private fun Preview() {
    FoodRecipesTheme {
        ScreenContent(
            uiState = DetailContract.UIState(
                recipe = RecipeDetail.Data(0, "", "", "", "", "", emptyList(), emptyList(), "", "", "yut", "", "", "", false)),
            onEvent = {}
        )
    }
}

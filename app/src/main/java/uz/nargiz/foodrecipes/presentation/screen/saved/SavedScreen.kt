package uz.nargiz.foodrecipes.presentation.screen.saved

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil3.compose.AsyncImage
import org.orbitmvi.orbit.compose.collectAsState
import uz.nargiz.foodrecipes.R
import uz.nargiz.foodrecipes.presentation.theme.FoodRecipesTheme

class SavedScreen: Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel: SavedContract.ViewModel = getViewModel<SavedViewModel>()
        LaunchedEffect(Unit) {
            viewModel.container.sideEffectFlow.collect {
                when(it) {
                    is SavedContract.SideEffect.Message -> {
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
    uiState: SavedContract.UIState,
    onEvent: (SavedContract.Event) -> Unit
) {
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
                onClick = { onEvent(SavedContract.Event.Back) },
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
                text = stringResource(R.string.saved),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        LazyColumn(
            modifier = Modifier
        ) {
            items(uiState.recipes.size) { index ->
                uiState.recipes[index].let { data ->
                    Button(
                        onClick = { onEvent(SavedContract.Event.Recipe(data)) },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 12.dp)
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
            }
            if (uiState.isLoading) {
                item {
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
                item {
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
        }
    }
}

@[Preview Composable]
private fun PreviewScreenContent() {
    FoodRecipesTheme {
        ScreenContent(
            uiState = SavedContract.UIState(),
            onEvent = {}
        )
    }
}
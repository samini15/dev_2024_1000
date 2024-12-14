@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dev_2024_1000.meal.presentation.meal_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dev_2024_1000.R
import com.example.dev_2024_1000.meal.presentation.meal_detail.components.IngredientWithMeasureItem
import com.example.dev_2024_1000.ui.theme.Dev_2024_1000Theme
import com.example.dev_2024_1000.ui.theme.LocalSpacing
import kotlinx.coroutines.Dispatchers

@Composable
fun MealDetailScreen(
    modifier: Modifier = Modifier,
    state: MealDetailState,
    onAction: (MealDetailAction) -> Unit,
    idMeal: String
) {
    val spacing = LocalSpacing.current

    // region Tabs
    val tabItems = listOf(TabItem("Instructions"), TabItem("Ingredients"))
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    // endregion Tabs

    // One-time event
    LaunchedEffect(true) {
        onAction(MealDetailAction.OnLoadMeal(idMeal))
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(state.meal.strMeal) },
                colors = TopAppBarDefaults.topAppBarColors()
                    .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(MealDetailAction.OnBackClick)
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                //.verticalScroll(scrollState),
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(state.meal.strMealThumb)
                    .dispatcher(Dispatchers.IO)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            TabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, tabItem ->
                    Tab(
                        text = { Text(tabItem.title) },
                        selected = index == selectedTabIndex,
                        onClick = {selectedTabIndex = index}
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                when (index) {
                    0 -> {
                        InstructionPage(state = state)
                    }
                    1 -> {
                        IngredientsPage(state = state)
                    }
                }
            }
        }
    }
}

@Composable
private fun InstructionPage(state: MealDetailState) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        Text(
            modifier = Modifier.padding(spacing.spaceSmall),
            text = stringResource(R.string.instructions),
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )

        Text(
            modifier = Modifier.padding(spacing.spaceSmall),
            text = state.meal.strInstructions ?: "",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(spacing.spaceSmall),
                text = "Video",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )

            TextButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(state.meal.strYoutube))
                    context.startActivity(intent)
                },
                enabled = true
            ) {
                Text(
                    modifier = Modifier.padding(spacing.spaceSmall),
                    text = state.meal.strYoutube ?: "",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }


        }
    }
}

@Composable
private fun IngredientsPage(state: MealDetailState) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(spacing.spaceSmall),
            text = "Ingredients",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceSmall),
        ) {
            items(state.meal.ingredientsWithMeasures) { (ingredient, measure) ->
                IngredientWithMeasureItem(ingredient = ingredient, measure = measure)
            }
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun MealDetailScreenPreview() {
    Dev_2024_1000Theme {
        MealDetailScreen(
            state = MealDetailState(),
            onAction = {},
            idMeal = ""
        )
    }
}

data class TabItem(
    val title: String
)
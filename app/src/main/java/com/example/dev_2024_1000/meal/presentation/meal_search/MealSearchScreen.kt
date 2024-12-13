@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dev_2024_1000.meal.presentation.meal_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_2024_1000.R
import com.example.dev_2024_1000.meal.presentation.meal_search.components.IngredientItem
import com.example.dev_2024_1000.meal.presentation.meal_search.components.MealItem
import com.example.dev_2024_1000.ui.theme.Dev_2024_1000Theme
import com.example.dev_2024_1000.ui.theme.LocalSpacing

@Composable
fun MealSearchScreen(
    modifier: Modifier = Modifier,
    state: MealSearchState,
    onAction: (MealSearchAction) -> Unit
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()

    // BottomSheet
    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        /*topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.meals)) },
                scrollBehavior = scrollBehaviour,
                colors = TopAppBarDefaults.topAppBarColors()
                    .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            )
        }*/
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = spacing.spaceMedium, end = spacing.spaceMedium),
                query = state.searchQuery,
                onQueryChange = { onAction(MealSearchAction.OnSearchQueryChanged(it)) },
                active = false,
                onActiveChange = { onAction(MealSearchAction.OnSearchActiveChanged(it)) },
                placeholder = {
                    Text(stringResource(R.string.search_meal))
                },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            onAction(MealSearchAction.OnSearchMeal)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isBottomSheetOpen = true
                            onAction(MealSearchAction.OnFilterByIngredientClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                },
                onSearch = {
                    onAction(MealSearchAction.OnSearchMeal)
                },
                tonalElevation = 20.dp
            ) {
                onAction(MealSearchAction.OnSearchMeal)
                keyboardController?.hide()
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = spacing.spaceMedium,
                        start = spacing.spaceMedium,
                        end = spacing.spaceMedium
                    ),
                onClick = {
                    onAction(MealSearchAction.OnRandomMealClick)
                }
            ) {
                Text(stringResource(R.string.show_random_meal))
            }

            LazyColumn {
                items(state.meals) { meal ->
                    MealItem(
                        mealUI = meal,
                        onClick = { /*TODO*/ }
                    )
                }
            }

            if (isBottomSheetOpen) {
                ModalBottomSheet(
                    onDismissRequest = { isBottomSheetOpen = false },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing.spaceMedium),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.filter_by_ingredient),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        Text(
                            text = stringResource(R.string.select_specific_ingredient),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        LazyColumn {
                            items(state.ingredients) { ingredient ->
                                IngredientItem(ingredientUi = ingredient) {
                                    onAction(MealSearchAction.OnIngredientSelected(ingredient.strIngredient))
                                    isBottomSheetOpen = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun MealSearchScreenPreview() {
    Dev_2024_1000Theme {
        MealSearchScreen(state = MealSearchState(), onAction = {})
    }
}
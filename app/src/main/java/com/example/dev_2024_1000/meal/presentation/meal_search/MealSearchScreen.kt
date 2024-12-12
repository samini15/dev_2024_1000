@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dev_2024_1000.meal.presentation.meal_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.dev_2024_1000.R
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.meals)) },
                scrollBehavior = scrollBehaviour,
                colors = TopAppBarDefaults.topAppBarColors()
                    .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
                text = stringResource(R.string.please_enter_your_meal_name_to_search)
            )

            SearchBar(
                modifier = Modifier
                    .padding(start = spacing.spaceMedium),
                query = state.searchQuery,
                onQueryChange = { onAction(MealSearchAction.OnSearchQueryChanged(it)) },
                active = false,
                onActiveChange = { onAction(MealSearchAction.OnSearchActiveChanged(it)) },
                placeholder = {
                    Text(stringResource(R.string.search_meal))
                },
                leadingIcon = {
                    IconButton(onClick = { onAction(MealSearchAction.OnSearchMeal) }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                },
                onSearch = { onAction(MealSearchAction.OnSearchMeal) },
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
                onClick = { /*TODO*/ }
            ) {
                Text(stringResource(R.string.filter_by_ingredient))
            }

            Text(
                modifier = Modifier.padding(horizontal = spacing.spaceMedium),
                text = stringResource(R.string.click_on_the_button_above_to_filter_based_on_a_specific_ingredient),
                color = MaterialTheme.colorScheme.onBackground,
                fontStyle = FontStyle.Italic,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = spacing.spaceMedium,
                        start = spacing.spaceMedium,
                        end = spacing.spaceMedium
                    ),
                onClick = { /*TODO*/ }
            ) {
                Text(stringResource(R.string.show_random_meal))
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
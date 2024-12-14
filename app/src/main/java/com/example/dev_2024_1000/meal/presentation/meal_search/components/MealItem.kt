package com.example.dev_2024_1000.meal.presentation.meal_search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dev_2024_1000.meal.presentation.model.MealUI
import com.example.dev_2024_1000.ui.theme.Dev_2024_1000Theme
import com.example.dev_2024_1000.ui.theme.LocalSpacing
import kotlinx.coroutines.Dispatchers

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    mealUI: MealUI,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    ElevatedCard(
        modifier = Modifier.padding(spacing.spaceMedium),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)
    ) {
        Column (
            modifier = modifier
                .clickable(onClick = { onClick() }),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(mealUI.strMealThumb)
                    .dispatcher(Dispatchers.IO)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = spacing.spaceMedium, topEnd = spacing.spaceMedium))
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.padding(spacing.spaceSmall),
                text = mealUI.strMeal,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun MealItemPreview() {
    Dev_2024_1000Theme {
        MealItem(
            mealUI = MealUI(
                idMeal = "1",
                strMeal = "Spicy Arrabiata Penne",
                strMealThumb = "https://cdn.pixabay.com/photo/2017/01/29/14/19/kermit-2018085_1280.jpg"
            )
        ) {

        }

    }
}
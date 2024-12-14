package com.example.dev_2024_1000.meal.presentation.meal_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dev_2024_1000.BuildConfig
import com.example.dev_2024_1000.ui.theme.LocalSpacing
import kotlinx.coroutines.Dispatchers

@Composable
fun IngredientWithMeasureItem(
    modifier: Modifier = Modifier,
    ingredient: String,
    measure: String
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("${BuildConfig.INGREDIENT_IMAGE_URL}$ingredient.png")
                .dispatcher(Dispatchers.IO)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(spacing.spaceMedium))
                .size(150.dp),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall)
        ) {
            Text(
                modifier = Modifier.padding(start = spacing.spaceMedium),
                text = ingredient,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )

            Text(
                modifier = Modifier.padding(start = spacing.spaceMedium),
                text = measure,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
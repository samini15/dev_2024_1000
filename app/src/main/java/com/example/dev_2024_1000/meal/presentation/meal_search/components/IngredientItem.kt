package com.example.dev_2024_1000.meal.presentation.meal_search.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dev_2024_1000.meal.presentation.model.IngredientUi
import com.example.dev_2024_1000.ui.theme.Dev_2024_1000Theme
import com.example.dev_2024_1000.ui.theme.LocalSpacing
import kotlinx.coroutines.Dispatchers

@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    ingredientUi: IngredientUi,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(ingredientUi.constructIngredientThumbUrl())
                .dispatcher(Dispatchers.IO)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(spacing.spaceMedium))
                .size(150.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            modifier = Modifier.padding(start = spacing.spaceMedium).align(Alignment.CenterVertically),
            text = ingredientUi.strIngredient,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview
@PreviewLightDark
@Composable
private fun IngredientItemPreview() {
    Dev_2024_1000Theme {
        IngredientItem(ingredientUi = IngredientUi(idIngredient = "0", strIngredient = "Beef")) { }
    }
}
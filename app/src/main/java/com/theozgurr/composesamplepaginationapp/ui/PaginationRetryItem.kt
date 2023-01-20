package com.theozgurr.composesamplepaginationapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@Composable
fun PaginationRetryItem(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        elevation = 12.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onRetryClick
                ),
            imageVector = Icons.Rounded.Refresh,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.Blue)
        )
    }
}
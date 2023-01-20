package com.theozgurr.composesamplepaginationapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.theozgurr.composesamplepaginationapp.ui.theme.Purple700

@Composable
fun PaginationLoadingItem(
    modifier: Modifier = Modifier,
    circularProgressSize: Dp = 32.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = circularProgressSize),
            color = Purple700,
            strokeWidth = 4.dp
        )
    }
}
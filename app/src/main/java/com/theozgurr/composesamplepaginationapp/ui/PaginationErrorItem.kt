package com.theozgurr.composesamplepaginationapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PaginationErrorItem(
    modifier: Modifier = Modifier,
    onTryAgainClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = modifier,
            onClick = onTryAgainClick
        ) {
            Text(text = "Try Again")
        }
    }
}
package com.example.localnotification.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreenContent(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
            .safeContentPadding(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onClick) {
            Text("通知を表示")
        }
    }
}
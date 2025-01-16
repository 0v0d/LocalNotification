package com.example.localnotification.view

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.localnotification.viewmodel.HomeViewModel
import com.example.localnotification.ui.theme.LocalNotificationTheme
import com.example.localnotification.view.component.HomeScreenContent
import com.example.localnotification.view.component.RequestPermissionContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    when {
        permissionState.status.isGranted -> {
            HomeScreenContent(onClick = {
                viewModel.sendNotification("タイトル", "本文")
            })
        }

        permissionState.status.shouldShowRationale -> {
            RequestPermissionContent(
                message = "通知を表示するために権限が必要です。",
                onClick = { permissionState.launchPermissionRequest() }
            )
        }

        else -> {
            RequestPermissionContent(
                message = "通知権限が拒否されています。許可してください。",
                buttonText = "設定を開く",
                onClick = {
                    openSettings(context)
                }
            )
        }
    }
}

private fun openSettings(context: Context) {
    context.startActivity(
        Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = android.net.Uri.parse("package:${context.packageName}")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LocalNotificationTheme {
        HomeScreen()
    }
}
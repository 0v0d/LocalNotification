package com.example.localnotification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.localnotification.ui.theme.LocalNotificationTheme
import com.example.localnotification.view.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalNotificationTheme {
                HomeScreen()
            }
        }
    }
}

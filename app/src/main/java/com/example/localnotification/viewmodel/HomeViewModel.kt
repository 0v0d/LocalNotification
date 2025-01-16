package com.example.localnotification.viewmodel

import androidx.lifecycle.ViewModel
import com.example.localnotification.LocalNotificationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val notificationManager: LocalNotificationManager
) : ViewModel() {
    fun sendNotification(title: String, content: String) {
        notificationManager.showNotification(title, content)
    }
}


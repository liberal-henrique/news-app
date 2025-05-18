package com.example.myapp.presentation.ui.component

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myapp.presentation.common.BiometricPromptManager
import com.example.myapp.presentation.navigation.CustomNavHost
import com.example.myapp.presentation.viewmodel.NewsViewModel

@Composable
fun BiometricScreen(
    promptManager: BiometricPromptManager,
    newsViewModel: NewsViewModel
) {

    val biometricResult by promptManager.promptResults.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        promptManager.showBiometricPrompt(
            title = "Confirme sua identidade",
            description = "Use biometria ou PIN para continuar"
        )
    }

    when(biometricResult) {
        is BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
            val navController = rememberNavController()
            CustomNavHost(
                navController = navController,
                newsViewModel = newsViewModel
            )
        }
        is BiometricPromptManager.BiometricResult.AuthenticationFailed,
        is BiometricPromptManager.BiometricResult.AuthenticationError -> {
            RetryScreen {
                promptManager.showBiometricPrompt(
                    title = "Try again",
                    description = "Biometric Authentication"
                )
            }
        }
        is BiometricPromptManager.BiometricResult.AuthenticationNotSet,
        is BiometricPromptManager.BiometricResult.FeatureUnavailable,
        is BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
            Text("Device don't support Biometric Authentication")
        }

        null -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
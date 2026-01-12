package com.paylastikca.presentation.view.commonUI.components.verification.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun CountdownEffect(
    isActive: Boolean,
    secondsLeft: Int,
    onTick: (Int) -> Unit,
    onTimeout: () -> Unit
) {
    LaunchedEffect(isActive) {
        if (!isActive) return@LaunchedEffect

        var s = secondsLeft
        while (s > 0) {
            delay(1000)
            s -= 1
            onTick(s)
        }
        onTimeout()
    }
}

package com.paylastikca.presentation.view.common_ui.components.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun rememberAndroidSystemBarsController(): SystemBarsController {
    val systemUiController = rememberSystemUiController()
    return remember(systemUiController) {
        object : SystemBarsController {
            override fun setSystemBarsColor(color: Color, darkIcons: Boolean) {
                systemUiController.setSystemBarsColor(color = color, darkIcons = darkIcons)
            }
        }
    }
}

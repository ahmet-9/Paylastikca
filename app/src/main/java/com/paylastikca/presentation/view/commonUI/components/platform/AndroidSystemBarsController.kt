package com.paylastikca.presentation.view.commonUI.components.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun AndroidRememberSystemBarsController(): SystemBarsController {
    val systemUiController = rememberSystemUiController()
    return remember(systemUiController) {
        object : SystemBarsController {
            override fun setSystemBarsColor(color: Color, darkIcons: Boolean) {
                systemUiController.setSystemBarsColor(color = color, darkIcons = darkIcons)
            }
        }
    }
}

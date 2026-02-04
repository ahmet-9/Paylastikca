package com.paylastikca.presentation.view.common_ui.components.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

interface SystemBarsController {
    fun setSystemBarsColor(color: Color, darkIcons: Boolean)
}

@Composable
fun rememberSystemBarsController(): SystemBarsController {
    return rememberAndroidSystemBarsController()
}

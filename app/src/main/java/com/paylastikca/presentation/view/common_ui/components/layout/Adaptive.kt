package com.paylastikca.presentation.view.common_ui.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.paylastikca.ui.UiDimens

data class AdaptiveUi(
    val isTablet: Boolean,
    val pageHorizontalPadding: Dp,
    val maxContentWidth: Dp?
)

@Composable
fun rememberAdaptive(): AdaptiveUi {
    val cfg = LocalConfiguration.current
    val isTablet = cfg.smallestScreenWidthDp >= 600

    return remember(cfg.smallestScreenWidthDp) {
        AdaptiveUi(
            isTablet = isTablet,

            pageHorizontalPadding = if (isTablet) UiDimens.xl else UiDimens.l,

            maxContentWidth = if (isTablet) 600.dp else null
        )
    }
}

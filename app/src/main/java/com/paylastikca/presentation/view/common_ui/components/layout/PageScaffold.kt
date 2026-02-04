package com.paylastikca.presentation.view.common_ui.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.navigation.BottomBar

@Composable
fun PageScaffold(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    currentRoute: String? = null,
    navController: NavController? = null,
    topBar: @Composable (() -> Unit)? = null,


    applySafeInsets: Boolean = true,
    removeTopInset: Boolean = true,


    applyBottomBarInsets: Boolean = false,

    bottomBarContainerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        containerColor = containerColor,
        topBar = { topBar?.invoke() },
        bottomBar = {
            if (currentRoute != null && navController != null) {
                BottomBar(
                    selectedRoute = currentRoute,
                    containerColor = bottomBarContainerColor,
                    applyInsets = applyBottomBarInsets,
                    onItemClick = { item ->
                        if (item.route == currentRoute) return@BottomBar

                        navController.navigate(item.route) {
                            popUpTo("homePageMainScreen") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },
        contentWindowInsets =
            if (applySafeInsets) WindowInsets.safeDrawing
            else WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->

        val layoutDir = LocalLayoutDirection.current

        val fixedPadding = if (removeTopInset) {
            PaddingValues(
                start = innerPadding.calculateStartPadding(layoutDir),
                top = 0.dp,
                end = innerPadding.calculateEndPadding(layoutDir),
                bottom = innerPadding.calculateBottomPadding()
            )
        } else {
            innerPadding
        }

        Box(modifier = modifier.fillMaxSize()) {
            content(fixedPadding)
        }
    }
}

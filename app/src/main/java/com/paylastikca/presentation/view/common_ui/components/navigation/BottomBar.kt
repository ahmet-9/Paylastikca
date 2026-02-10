package com.paylastikca.presentation.view.common_ui.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.paylastikca.app.R

data class BottomBarItem(
    val iconRes: Int,
    val contentDescription: String,
    val route: String
)

@Composable
fun BottomBar(
    selectedRoute: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    iconYOffset: Dp = 0.dp,

    onItemClick: (BottomBarItem) -> Unit,


    applyInsets: Boolean = false
) {
    val colors = MaterialTheme.colorScheme

    val items = listOf(
        BottomBarItem(R.drawable.homeiconmenu, "HomeMainScreen", "HomeMainScreen"),
        BottomBarItem(R.drawable.cooperationiconmenu, "SharingMainScreen", "SharingMainScreen"),
        BottomBarItem(R.drawable.notificationiconmenu, "NotificationMainScreen", "NotificationMainScreen"),
        BottomBarItem(R.drawable.profileiconmenu, "ProfileMainScreen", "ProfileMainScreen")
    )

    NavigationBar(
        containerColor = containerColor,
        tonalElevation = 0.dp,
        modifier = modifier.height(70.dp),


        windowInsets = if (applyInsets) NavigationBarDefaults.windowInsets else WindowInsets(0)
    ) {
        items.forEach { item ->
            val selected = item.route == selectedRoute

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Box(modifier = Modifier.offset(y = iconYOffset)) {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.contentDescription,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                },
                label = null,
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.onPrimary,
                    unselectedIconColor = colors.primary,
                    indicatorColor = colors.primary
                )
            )
        }
    }
}

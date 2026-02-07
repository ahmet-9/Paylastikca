package com.paylastikca.presentation.view.profilescreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.platform.rememberSystemBarsController
import com.paylastikca.ui.UiDimens

@Composable
fun NotificationSettingsScreen(
    onBack: () -> Unit = {},
    onNotificationPermissionChanged: (Boolean) -> Unit = {}
) {
    var notificationsEnabled by remember { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()


    val systemBars = rememberSystemBarsController()
    SideEffect {
        systemBars.setSystemBarsColor(
            color = colors.surfaceVariant,
            darkIcons = true
        )
    }

    PageScaffold(
        containerColor = colors.surfaceVariant
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            BackButton(
                title = "Bildirimler",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 7.dp),
                onBackClick = onBack
            )

            Spacer(modifier = Modifier.padding(top = 14.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = UiDimens.xs, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                RoundedContainer {
                    SwitchRow(
                        title = "Bildirimlere izin ver",
                        checked = notificationsEnabled,
                        onCheckedChange = {
                            notificationsEnabled = it
                            onNotificationPermissionChanged(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RoundedContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Surface(
        shape = RoundedCornerShape(UiDimens.rM),
        color = colors.surface,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            content = content
        )
    }
}

@Composable
private fun SwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = UiDimens.l, vertical = UiDimens.s),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
            color = colors.onSurface
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = colors.onPrimary,
                checkedTrackColor = colors.primary,
                uncheckedThumbColor = colors.outline,
                uncheckedTrackColor = colors.surfaceVariant,
                checkedBorderColor = colors.primary,
                uncheckedBorderColor = colors.outline
            )
        )
    }
}

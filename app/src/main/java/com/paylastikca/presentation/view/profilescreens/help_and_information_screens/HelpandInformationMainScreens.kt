package com.paylastikca.presentation.view.profilescreens.help_and_information_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.platform.rememberSystemBarsController
import com.paylastikca.ui.UiDimens

@Composable
fun HelpandInformationMainScreens(
    onBack: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onHelpFaqClick: () -> Unit = {},
    onContactClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val s = adaptive.scale

    val systemBars = rememberSystemBarsController()
    SideEffect {
        systemBars.setSystemBarsColor(color = colors.surfaceVariant, darkIcons = true)
    }

    PageScaffold(
        containerColor = colors.surfaceVariant,
        topBar = {
            BackButton(
                title = "Yardım ve bilgilendirme",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = adaptive.pageHorizontalPadding),
                onBackClick = onBack,
                scale = s
            )
        },
        removeTopInset = false
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            Spacer(modifier = Modifier.height((UiDimens.m.value * s).dp))

            RoundedContainer {
                NavigationRow(title = "Hakkımızda", scale = s, onClick = onAboutClick)
                DividerLine(scale = s)
                NavigationRow(title = "Gizlilik politikası", scale = s, onClick = onPrivacyPolicyClick)
                DividerLine(scale = s)
                NavigationRow(title = "Kullanım şartları", scale = s, onClick = onTermsClick)
                DividerLine(scale = s)
                NavigationRow(title = "Yardım & SSS", scale = s, onClick = onHelpFaqClick)
                DividerLine(scale = s)
                NavigationRow(title = "Bize ulaşın", scale = s, onClick = onContactClick)
            }

            Spacer(modifier = Modifier.height((UiDimens.xl.value * s).dp))
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
private fun NavigationRow(
    title: String,
    scale: Float,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    val rowMinH = (56f * scale).dp
    val padH = (16f * scale).dp
    val padV = (10f * scale).dp
    val textSize = (16f * scale).sp
    val arrowSize = (18f * scale).dp

    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .heightIn(min = rowMinH)
            .padding(horizontal = padH, vertical = padV),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = textSize,
            fontWeight = FontWeight.Normal,
            color = colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = colors.onSurfaceVariant,
            modifier = Modifier.size(arrowSize)
        )
    }
}

@Composable
private fun DividerLine(scale: Float) {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.outlineVariant,
        thickness = 1.dp,
        modifier = Modifier.padding(start = (16f * scale).dp)
    )
}

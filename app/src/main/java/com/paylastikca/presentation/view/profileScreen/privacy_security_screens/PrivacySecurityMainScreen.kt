package com.paylastikca.presentation.view.profileScreen.privacy_security_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.presentation.view.commonUI.components.navigation.PageHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacySecurityMainScreen(
    onBack: () -> Unit = {},
    onTwoFactorClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onDeleteAccountClick: () -> Unit = {},
    onLocationShareChanged: (Boolean) -> Unit = {}
) {

    var locationShareEnabled by remember { mutableStateOf(false) }

    val colors = MaterialTheme.colorScheme

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.surfaceVariant
        )
    }

    Scaffold(
        containerColor = colors.surfaceVariant,
        topBar = {
            PageHeader(
                modifier = Modifier.padding(top = 25.dp, start = 7.dp),
                title = "Gizlilik & Güvenlik",
                onBackClick = onBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SectionLabel(text = "Gizlilik")

            RoundedContainer {
                SwitchRow(
                    title = "Konum paylaşımı",
                    checked = locationShareEnabled,
                    onCheckedChange = {
                        locationShareEnabled = it
                        onLocationShareChanged(it)
                    }
                )
            }

            Spacer(Modifier.height(8.dp))

            SectionLabel(text = "Güvenlik")

            RoundedContainer {
                NavigationRow(
                    title = "İki Adımlı Doğrulama",
                    onClick = onTwoFactorClick
                )
                DividerLine()
                NavigationRow(
                    title = "Şifre değiştir",
                    onClick = onChangePasswordClick
                )
                DividerLine()
                NavigationRow(
                    title = "Hesabı sil",
                    onClick = onDeleteAccountClick
                )
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    val colors = MaterialTheme.colorScheme
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = colors.onSurfaceVariant,
        modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
    )
}

@Composable
private fun RoundedContainer(content: @Composable ColumnScope.() -> Unit) {
    val colors = MaterialTheme.colorScheme
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = colors.surface,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth(), content = content)
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
            .padding(horizontal = 16.dp, vertical = 8.dp),
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

@Composable
private fun NavigationRow(
    title: String,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .heightIn(min = 56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
            color = colors.onSurface
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = colors.onSurfaceVariant
        )
    }
}

@Composable
private fun DividerLine() {
    Divider(
        color = MaterialTheme.colorScheme.outlineVariant,
        thickness = 1.dp,
        modifier = Modifier.padding(start = 16.dp)
    )
}

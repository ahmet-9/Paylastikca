package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.paylastikca.presentation.view.commonUI.components.PageHeader

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

    Scaffold(
        containerColor = Color(0xFFF6F6F6),
        topBar = {
            PageHeader(
                modifier = Modifier.padding(top = 25.dp, start = 7.dp),
                title = "gizlilik & güvenlik",
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
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = Color(0xFFB0B0B0),
        modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
    )
}

@Composable
private fun RoundedContainer(content: @Composable ColumnScope.() -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
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
            color = Color(0xFF111111)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFFF7200),
                checkedTrackColor = Color(0xFFFFB74D),
                uncheckedThumbColor = Color(0xFFBDBDBD),
                uncheckedTrackColor = Color(0xFFE0E0E0)
            )
        )
    }
}

@Composable
private fun NavigationRow(
    title: String,
    onClick: () -> Unit
) {
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
            color = Color(0xFF111111)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Color(0xFFB0B0B0)
        )
    }
}

@Composable
private fun DividerLine() {
    Divider(
        color = Color(0xFFEDEDED),
        thickness = 1.dp,
        modifier = Modifier.padding(start = 16.dp)
    )
}

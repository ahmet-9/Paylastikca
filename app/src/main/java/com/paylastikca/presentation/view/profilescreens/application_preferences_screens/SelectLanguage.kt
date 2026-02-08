package com.paylastikca.presentation.view.profilescreens.application_preferences_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.platform.rememberSystemBarsController
import com.paylastikca.ui.UiDimens

@Composable
fun SelectLanguage(
    currentLanguageCode: String,
    onLanguageSelected: (LanguageOption) -> Unit,
    onBack: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val s = adaptive.scale

    var query by remember { mutableStateOf("") }

    val systemBars = rememberSystemBarsController()
    SideEffect {
        systemBars.setSystemBarsColor(colors.background, darkIcons = true)
    }

    val filteredLanguages = remember(query) {
        AppLanguages.filter {
            it.label.contains(query, ignoreCase = true)
        }
    }

    PageScaffold(
        containerColor = colors.background,
        topBar = {
            BackButton(
                title = "Dil seç",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = adaptive.pageHorizontalPadding),


                onBackClick = onBack,
                scale = adaptive.scale
            )
        },
        removeTopInset = false
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = (52f * s).dp)
                    .padding(bottom = UiDimens.m),
                placeholder = { Text(
                    text = "Ara",
                    fontSize = (16f * s).sp
                ) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null,
                        modifier = Modifier.size((20f * s).dp))
                },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = (16f * s).sp
                ),
                singleLine = true
            )

            LazyColumn {
                items(filteredLanguages) { lang ->
                    LanguageRow(
                        language = lang,
                        selected = lang.code == currentLanguageCode,
                        scale = s,
                        onClick = {
                            onLanguageSelected(lang)

                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LanguageRow(
    language: LanguageOption,
    selected: Boolean,
    scale: Float,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .heightIn(min = (52f * scale).dp)
            .padding(horizontal = UiDimens.s),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language.label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = (18f * scale).sp,
            color = colors.onSurface,
            modifier = Modifier.weight(1f)
        )

        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.primary,
                unselectedColor = colors.outline
            )
        )
    }
}

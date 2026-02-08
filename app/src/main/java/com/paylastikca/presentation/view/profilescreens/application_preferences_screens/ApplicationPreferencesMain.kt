package com.paylastikca.presentation.view.profilescreens.application_preferences_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.platform.rememberSystemBarsController
import com.paylastikca.ui.UiDimens

enum class ThemePreference {
    LIGHT, DARK, SYSTEM
}

@Composable
fun ApplicationPreferencesMain(
    navController: NavController,
    onBack: () -> Unit = {},
    initialTheme: ThemePreference = ThemePreference.LIGHT,
    onThemeChange: (ThemePreference) -> Unit = {},
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    var selectedLanguage by remember { mutableStateOf(LanguageOption("tr", "Türkçe")) }

    val entry = navController.currentBackStackEntry
    val handle = entry?.savedStateHandle

    val newCode = handle?.get<String>("selectedLanguageCode")
    val newLabel = handle?.get<String>("selectedLanguageLabel")

    LaunchedEffect(newCode, newLabel) {
        if (!newCode.isNullOrBlank() && !newLabel.isNullOrBlank()) {
            selectedLanguage = LanguageOption(newCode, newLabel)


            handle.remove<String>("selectedLanguageCode")
            handle.remove<String>("selectedLanguageLabel")
        }
    }


    var theme by remember { mutableStateOf(initialTheme) }

    val systemBars = rememberSystemBarsController()
    SideEffect {
        systemBars.setSystemBarsColor(
            color = colors.surfaceVariant,
            darkIcons = true
        )
    }

    PageScaffold(
        containerColor = colors.surfaceVariant,
        topBar = {
            BackButton(
                title = "Uygulama Tercihleri",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = adaptive.pageHorizontalPadding),
                onBackClick = onBack
            )
        },
        removeTopInset = false
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {
            Spacer(Modifier.height(UiDimens.s))

            SectionLabel("Tema")

            SettingsCard {

                ThemeRadioRow(
                    title = "Sistem Varsayılanı",
                    selected = theme == ThemePreference.SYSTEM,
                    onSelect = {
                        theme = ThemePreference.SYSTEM
                        onThemeChange(theme)
                    }
                )
                SettingsDivider()

                ThemeRadioRow(
                    title = "Açık",
                    selected = theme == ThemePreference.LIGHT,
                    onSelect = {
                        theme = ThemePreference.LIGHT
                        onThemeChange(theme)
                    }
                )
                SettingsDivider()

                ThemeRadioRow(
                    title = "Kapalı",
                    selected = theme == ThemePreference.DARK,
                    onSelect = {
                        theme = ThemePreference.DARK
                        onThemeChange(theme)
                    }
                )



            }

            Spacer(Modifier.height(UiDimens.l))

            SectionLabel("Dil")

            LanguagePillRow(
                language = selectedLanguage.label,
                onChangeClick = {
                    navController.navigate("selectLanguage/${selectedLanguage.code}")
                }
            )



            Spacer(Modifier.height(UiDimens.xl))
        }
    }
}

/* ---------------- Components ---------------- */

@Composable
private fun SectionLabel(text: String) {
    val colors = MaterialTheme.colorScheme
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = colors.onSurfaceVariant,
        modifier = Modifier.padding(start = UiDimens.xs, bottom = UiDimens.xs)
    )
}


@Composable
private fun SettingsCard(content: @Composable ColumnScope.() -> Unit) {
    val colors = MaterialTheme.colorScheme
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = colors.surface,
        shape = RoundedCornerShape(UiDimens.rM),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, colors.outlineVariant)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), content = content)
    }
}

@Composable
private fun SettingsDivider() {
    val colors = MaterialTheme.colorScheme
    HorizontalDivider(
        color = colors.outlineVariant,
        thickness = 1.dp,
        modifier = Modifier.padding(start = UiDimens.l)
    )
}

@Composable
private fun ThemeRadioRow(
    title: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .heightIn(min = 52.dp)
            .padding(horizontal = UiDimens.l, vertical = UiDimens.s),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = colors.onSurface,
            modifier = Modifier.weight(1f)
        )

        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.primary,
                unselectedColor = colors.outline
            )
        )
    }
}

@Composable
private fun LanguagePillRow(
    language: String,
    onChangeClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = colors.surface,
        shape = RoundedCornerShape(UiDimens.rM),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, colors.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 52.dp)
                .padding(horizontal = UiDimens.l, vertical = UiDimens.s),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = language,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.width(UiDimens.xs))
                Text(
                    text = "✓",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.primary,
                    fontWeight = FontWeight.Black
                )
            }

            TextButton(
                onClick = onChangeClick,
                contentPadding = PaddingValues(horizontal = UiDimens.s, vertical = 0.dp),
                colors = ButtonDefaults.textButtonColors(contentColor = colors.primary)
            ) {
                Text("Değiştir")
            }
        }
    }
}

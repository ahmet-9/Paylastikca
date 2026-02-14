package com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.ScrollPage
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.templates.TabbedListTemplate
import com.paylastikca.presentation.view.common_ui.components.templates.SharingItemCard
import com.paylastikca.presentation.view.common_ui.components.templates.ChipTone
import com.paylastikca.ui.UiDimens

@Composable
fun HelpActiveListScreen(
    navController: NavController,
    startTabIndex: Int = 0 // 0: aktif, 1: geçmiş
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    var tab by remember { mutableStateOf(startTabIndex.coerceIn(0, 1)) }

    PageScaffold(containerColor = colors.background) { innerPadding ->

        ScrollPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            BackButton(
                title = "Yardım listem",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.xl.scaled(scale)),
                onBackClick = { navController.popBackStack() },
                scale = scale
            )

            Spacer(Modifier.height(UiDimens.m.scaled(scale)))

            TabbedListTemplate(
                title = "",
                leftTab = "Aktif Yardımlar",
                rightTab = "Yardım Geçmişi",
                selectedTabIndex = tab,
                onTabChange = { tab = it },
                scale = scale
            ) {

                Spacer(Modifier.height(UiDimens.m.scaled(scale)))

                if (tab == 0) {
                    // --- AKTİF ---
                    SharingItemCard(
                        title = "Yer halısı",
                        desc = "2.el temiz yer halısı lorem ipsum...",
                        categoryLabel = "KATEGORİ: Halı",
                        rightTopChipText = "Talep var",
                        rightTopChipTone = ChipTone.Positive,
                        rightButtonText = "İsteği gör",
                        onRightButtonClick = { navController.navigate("HelpSeeRequestScreen") },
                        showDelete = true,
                        onDelete = { navController.navigate("DeleteHelpScreen") },
                        scale = scale
                    )

                    Spacer(Modifier.height(10.dp.scaled(scale)))

                    SharingItemCard(
                        title = "Yer halısı",
                        desc = "2.el temiz yer halısı lorem ipsum...",
                        categoryLabel = "KATEGORİ: Halı",
                        rightTopChipText = "Talep yok",
                        rightTopChipTone = ChipTone.Neutral,
                        rightButtonText = null,
                        onRightButtonClick = null,
                        showDelete = true,
                        onDelete = { navController.navigate("DeleteHelpScreen") },
                        scale = scale
                    )
                } else {
                    // --- GEÇMİŞ ---
                    SharingItemCard(
                        title = "Yer halısı",
                        desc = "2.el temiz yer halısı lorem ipsum...",
                        categoryLabel = "KATEGORİ: Halı",
                        rightTopChipText = null,
                        rightTopChipTone = ChipTone.Neutral,
                        rightButtonText = "Puan Ver",
                        onRightButtonClick = { navController.navigate("HelpGivePointsScreen") },
                        showDelete = false,
                        onDelete = null,
                        scale = scale
                    )

                    Spacer(Modifier.height(10.dp.scaled(scale)))

                    SharingItemCard(
                        title = "Yer halısı",
                        desc = "2.el temiz yer halısı lorem ipsum...",
                        categoryLabel = "KATEGORİ: Halı",
                        rightTopChipText = null,
                        rightTopChipTone = ChipTone.Neutral,
                        rightButtonText = "Değerlendirildi",
                        onRightButtonClick = null,
                        showDelete = false,
                        onDelete = null,
                        scale = scale
                    )
                }
            }

            Spacer(Modifier.height(UiDimens.xl.scaled(scale)))
        }
    }
}

package com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.ChipTone
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.SharingItemCardTemplate
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.TabbedListTemplate
import com.paylastikca.ui.UiDimens

@Composable
fun HelpActiveListScreen(
    navController: NavController,
    startTabIndex: Int = 0
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    var tab by rememberSaveable {
        mutableIntStateOf(startTabIndex.coerceIn(0, 1))
    }

    val topOverlaySpace = 36.dp.scaled(scale)

    val contentModifier = remember(adaptive.maxContentWidth, adaptive.pageHorizontalPadding) {
        Modifier
            .fillMaxWidth()
            .then(
                if (adaptive.maxContentWidth != null) {
                    Modifier
                        .widthIn(max = adaptive.maxContentWidth)
                        .padding(horizontal = adaptive.pageHorizontalPadding)
                } else {
                    Modifier.padding(horizontal = adaptive.pageHorizontalPadding)
                }
            )
    }

    PageScaffold(containerColor = colors.background) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = contentModifier.align(Alignment.TopCenter)
            ) {

                BackButton(
                    title = "Yardım listem",
                    modifier = Modifier.fillMaxWidth(),
                    onBackClick = { navController.popBackStack() },
                    scale = scale
                )

                TabbedListTemplate(
                    title = "",
                    leftTab = "Aktif Yardımlar",
                    rightTab = "Yardım Geçmişi",
                    selectedTabIndex = tab,
                    onTabChange = { tab = it },
                    scale = scale
                ) {
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(
                        top = UiDimens.m.scaled(scale),
                        bottom = UiDimens.xl.scaled(scale)
                    ),
                    verticalArrangement = Arrangement.spacedBy(36.dp.scaled(scale))
                ) {

                    if (tab == 0) {
                        items(
                            items = List(20) { it },
                            key = { it }
                        ) { i ->
                            val isDemand = i % 2 == 0

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = topOverlaySpace)
                            ) {
                                SharingItemCardTemplate(
                                    title = "Yer halısı",
                                    desc = "2.el temiz yer halısı lorem ipsum...",
                                    categoryLabel = "KATEGORİ: Halı",
                                    rightTopChipText = if (isDemand) "Talep var" else "Talep yok",
                                    rightTopChipTone = if (isDemand) ChipTone.Positive else ChipTone.Neutral,
                                    rightButtonText = if (isDemand) "İsteği gör" else null,
                                    onRightButtonClick = if (isDemand) ({
                                        navController.navigate("HelpSeeRequestScreen")
                                    }) else null,
                                    showDelete = true,
                                    onDelete = { navController.navigate("DeleteHelpScreen") },
                                    scale = scale
                                )
                            }
                        }

                    } else {
                        items(
                            items = List(20) { it },
                            key = { it }
                        ) { i ->
                            val canRate = i % 2 == 0

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = topOverlaySpace)
                            ) {
                                SharingItemCardTemplate(
                                    title = "Yer halısı",
                                    desc = "2.el temiz yer halısı 54586lorem ipsum...gdssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssv",
                                    categoryLabel = "KATEGORİ: Halı",
                                    rightTopChipText = null,
                                    rightTopChipTone = ChipTone.Neutral,
                                    rightButtonText = if (canRate) "Puan Ver" else "Değerlendirildi",
                                    onRightButtonClick = if (canRate) ({
                                        navController.navigate("HelpGivePointsScreen")
                                    }) else null,
                                    showTopRightCheck = !canRate,
                                    showDelete = false,
                                    onDelete = null,
                                    scale = scale
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
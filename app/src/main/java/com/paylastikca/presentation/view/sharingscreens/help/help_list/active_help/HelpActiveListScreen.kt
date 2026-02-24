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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.DeleteConfirmationDialogTemplate
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.DeleteSuccessDialog
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.ChipTone
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.SharingItemCardTemplate
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.TabbedListTemplate
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template.RatingNavKeys
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template.RatingSuccessDialogTemplate
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template.RatingTargetType
import com.paylastikca.ui.UiDimens
import kotlinx.coroutines.flow.collectLatest

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

    var showDeleteConfirm by rememberSaveable { mutableStateOf(false) }
    var showDeleteSuccess by rememberSaveable { mutableStateOf(false) }


    var pendingDeleteId by rememberSaveable { mutableStateOf<String?>(null) }
    //rating_template
    var showRatingSuccess by rememberSaveable { mutableStateOf(false) }
    var lastRating by rememberSaveable { mutableIntStateOf(0) }
    var lastUser by rememberSaveable { mutableStateOf("") }
    var lastTarget by rememberSaveable { mutableStateOf("") } // "help"/"request"/"swap"


    val entry = navController.currentBackStackEntry
    LaunchedEffect(entry) {
        val handle = entry?.savedStateHandle ?: return@LaunchedEffect

        handle.getStateFlow<Int?>(RatingNavKeys.VALUE, null)
            .collectLatest { value ->
                if (value != null) {

                    tab = 1

                    lastRating = value
                    lastUser = handle.get<String>(RatingNavKeys.USER) ?: ""
                    lastTarget = handle.get<String>(RatingNavKeys.TARGET) ?: ""

                    showRatingSuccess = true

                    handle[RatingNavKeys.VALUE] = null
                    handle[RatingNavKeys.USER] = null
                    handle[RatingNavKeys.TARGET] = null
                }
            }
    }
//rating_template


    val topOverlaySpace = 36.dp.scaled(scale)

    PageScaffold(containerColor = colors.background) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .let { base ->
                        if (adaptive.maxContentWidth != null) {
                            base
                                .widthIn(max = adaptive.maxContentWidth)
                                .padding(horizontal = adaptive.pageHorizontalPadding)
                        } else {
                            base.padding(horizontal = adaptive.pageHorizontalPadding)
                        }
                    }
                    .align(Alignment.TopCenter)
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
                        items(List(20) { it }) { i ->
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
                                    onDelete = {
                                        pendingDeleteId = "help_$i"
                                        showDeleteConfirm = true
                                    },
                                    scale = scale
                                )
                            }
                        }
                    } else {
                        items(List(20) { it }) { i ->
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
            //rating_template

            if (showRatingSuccess) {
                RatingSuccessDialogTemplate(
                    targetType = when (lastTarget.lowercase()) {
                        "help" -> RatingTargetType.Help
                        "request" -> RatingTargetType.Request
                        "swap" -> RatingTargetType.Swap
                        else -> RatingTargetType.Help
                    },
                    userName = lastUser.ifBlank { "Kullanıcı" },
                    rating = lastRating.coerceIn(1, 5),
                    buttonText = "Tamam",
                    onButtonClick = { showRatingSuccess = false },
                    scale = scale
                )
            }
            //rating_template

            if (showDeleteConfirm) {
                DeleteConfirmationDialogTemplate(
                    title = "Yardımı silmek istediğinizden\nemin misiniz!",
                    message = "sf",
                    confirmText = "Sil",
                    cancelText = "Vazgeç",
                    onCancel = {
                        showDeleteConfirm = false
                        pendingDeleteId = null
                    },
                    onConfirm = {
                        showDeleteConfirm = false


                        showDeleteSuccess = true
                    },
                    scale = scale
                )
            }

            if (showDeleteSuccess) {
                DeleteSuccessDialog(
                    title = "Yardım Silindi",
                    message = "",
                    buttonText = "Tamam",
                    onButtonClick = {
                        showDeleteSuccess = false
                        pendingDeleteId = null
                    },
                    scale = scale
                )
            }



        }
    }
}
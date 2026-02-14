package com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.templates.ConfirmActionDialogTemplate

@Composable
fun DeleteHelpScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    PageScaffold(containerColor = colors.background) {

        ConfirmActionDialogTemplate(
            title = "Yardımı silmek istediğinizden\nemin misiniz!",
            message = "",
            cancelText = "vazgeç",
            confirmText = "Sil",
            onCancel = { navController.popBackStack() },
            onConfirm = {
                // backend gelince burada delete API çağrısı olacak
                // şimdilik success popup
                navController.navigate("HelpDeletionCompletedScreen") {
                    popUpTo("DeleteHelpScreen") { inclusive = true }
                }
            },
            scale = scale
        )
    }
}

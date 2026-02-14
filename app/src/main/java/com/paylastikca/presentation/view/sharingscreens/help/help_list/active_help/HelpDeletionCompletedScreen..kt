package com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.templates.SuccessDialogTemplate

@Composable
fun HelpDeletionCompletedScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    PageScaffold(containerColor = colors.background) {

        SuccessDialogTemplate(
            title = "Yardım Silindi",
            message = "Yardım başarıyla silindi.",
            buttonText = "Tamam",
            onButtonClick = {
                navController.popBackStack()
            },
            scale = scale
        )
    }
}

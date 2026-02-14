package com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.templates.SuccessDialogTemplate

@Composable
fun HelpPointsGivenCompletedScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    PageScaffold(containerColor = colors.background) {

        SuccessDialogTemplate(
            title = "Puan Gönderildi",
            message = "Puanın başarıyla gönderildi.",
            buttonText = "Tamam",
            onButtonClick = {
                // popup kapat -> puan ekranına dön
                navController.popBackStack()
            },
            scale = scale
        )
    }
}

package com.paylastikca.presentation.view.sharingscreens.help.submit_help

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.EmojiTemplateScreen


@Composable
fun HelpThankYouScreen(navController : NavController) {

    EmojiTemplateScreen(
        emojiRes = R.drawable.happyemoji,
        topRightDecoRes = R.drawable.rigttopline,
        bottomLeftDecoRes = R.drawable.leftbottomline,
        title = "Teşekkür ederiz!",
        primaryButtonText = "Yardım listem",
        onPrimaryClick = { navController.popBackStack() },
        secondaryText = "Ana sayfaya dön",
        onSecondaryClick = {  }
    )

}

package com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.HelpActiveListScreen

@Composable
fun HelpHistoryListScreen(navController: NavController) {
    HelpActiveListScreen(
        navController = navController,
        startTabIndex = 1
    )
}
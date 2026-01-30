package com.paylastikca.presentation.view.sharingScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.paylastikca.presentation.view.commonUI.components.layout.FixedPage
import com.paylastikca.presentation.view.commonUI.components.layout.PageScaffold
import com.paylastikca.ui.UiDimens

@Composable
fun SharingMAİNScreen(navController: NavController) {

    PageScaffold(
        containerColor = MaterialTheme.colorScheme.background,
        currentRoute = "SharingMAİNScreen",
        navController = navController
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = UiDimens.l
        ) {}

    }
}
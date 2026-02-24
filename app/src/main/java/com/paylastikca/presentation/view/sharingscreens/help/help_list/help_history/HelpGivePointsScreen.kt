package com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template.RatingNavKeys
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template.RatingTargetType
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template.RatingTemplate
import com.paylastikca.ui.UiDimens

@Composable
fun HelpGivePointsScreen(
    navController: NavController
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    var rating by rememberSaveable { mutableIntStateOf(0) }

    PageScaffold(containerColor = colors.background) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            BackButton(
                title = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.s.scaled(scale)),
                onBackClick = { navController.popBackStack() },
                scale = scale
            )

            Spacer(Modifier.height(UiDimens.l.scaled(scale)))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                RatingTemplate(
                    targetType = RatingTargetType.Help,
                    userName = "Ahmet badem",
                    badgeLabel = null,
                    infoLine = null,
                    itemTitle = "Yer halısı",
                    itemDesc = "2.el temiz yer halısı ...",
                    itemCategoryLabel = "KATEGORİ: Halı",
                    rating = rating,
                    onRatingChange = { rating = it },
                    primaryButtonText = "Gönder",
                    onSubmit = {
                        val safeRating = rating.coerceIn(1, 5)

                        val prev = navController.previousBackStackEntry?.savedStateHandle
                        prev?.set(RatingNavKeys.VALUE, safeRating)
                        prev?.set(RatingNavKeys.TARGET, "help")
                        prev?.set(RatingNavKeys.USER, "Ahmet badem")

                        navController.popBackStack()
                    },
                    scale = scale,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(UiDimens.xl.scaled(scale)))
        }


    }
}
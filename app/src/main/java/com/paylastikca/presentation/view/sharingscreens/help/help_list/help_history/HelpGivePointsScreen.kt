package com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.ScrollPage
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.ui.UiDimens

@Composable
fun HelpGivePointsScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    var rating by remember { mutableStateOf(4) }

    PageScaffold(containerColor = colors.background) { innerPadding ->

        ScrollPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            BackButton(
                title = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.xl.scaled(scale)),
                onBackClick = { navController.popBackStack() },
                scale = scale
            )

            Spacer(Modifier.height(UiDimens.m.scaled(scale)))

            // Avatar
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp.scaled(scale))
                        .clip(RoundedCornerShape(16.dp.scaled(scale)))
                        .background(colors.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🙂", fontSize = 34.sp.scaled(scale))
                }
            }

            Spacer(Modifier.height(14.dp.scaled(scale)))

            // Name
            Text(
                text = "Ahmet badem",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp.scaled(scale),
                fontWeight = FontWeight.Black,
                color = colors.onSurface,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(Modifier.height(10.dp.scaled(scale)))

            // Item card (tasarımın ortadaki bej kartı)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp.scaled(scale)))
                    .background(colors.primaryContainer.copy(alpha = 0.55f))
                    .padding(14.dp.scaled(scale))
            ) {
                Text(
                    text = "Yer halısı",
                    fontSize = 14.sp.scaled(scale),
                    fontWeight = FontWeight.Black,
                    color = colors.onSurface
                )
                Spacer(Modifier.height(4.dp.scaled(scale)))
                Text(
                    text = "2.el temiz yer halısı lorem ipsum...",
                    fontSize = 11.sp.scaled(scale),
                    color = colors.onSurfaceVariant
                )
                Spacer(Modifier.height(8.dp.scaled(scale)))
                Text(
                    text = "KATEGORİ: Halı",
                    fontSize = 11.sp.scaled(scale),
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface
                )
            }

            Spacer(Modifier.height(18.dp.scaled(scale)))

            // Stars
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 1..5) {
                    val filled = i <= rating
                    IconButton(
                        onClick = { rating = i },
                        modifier = Modifier.size(52.dp.scaled(scale))
                    ) {
                        Icon(
                            imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.StarOutline,
                            contentDescription = "Star $i",
                            tint = if (filled) colors.primary else colors.outline,
                            modifier = Modifier.size(36.dp.scaled(scale))
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp.scaled(scale)))

            // Submit
            Button(
                onClick = {
                    // backend gelince: submit rating
                    navController.navigate("HelpPointsGivenCompletedScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp.scaled(scale)),
                shape = RoundedCornerShape(14.dp.scaled(scale)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary
                )
            ) {
                Text(
                    text = "Gönder",
                    fontSize = 16.sp.scaled(scale),
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(Modifier.height(UiDimens.xl.scaled(scale)))
        }
    }
}

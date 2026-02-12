package com.paylastikca.presentation.view.sharingscreens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.ui.UiDimens

@Immutable
private data class DmBoxItemUi(
    val id: String,
    val name: String,
    val subtitle: String,
    val badge: String,
    val timeLeftText: String
)

@Composable
fun DmBoxScreen(
    navController: NavController
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val s = adaptive.scale

    val list = remember {
        listOf(
            DmBoxItemUi("1", "Ahmet Badem", "Yer halısı", "Takas", "23 saat kaldı"),
            DmBoxItemUi("2", "Ahmet Badem", "Yer halısı", "Yardım", "23 saat kaldı"),
            DmBoxItemUi("3", "Ahmet Badem", "Yer halısı", "Talep", "23 saat kaldı"),
            DmBoxItemUi("4", "Ahmet Badem", "Yer halısı", "Takas", "23 saat kaldı")
        )
    }

    PageScaffold(
        containerColor = colors.background
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            BackButton(
                title = "Sohbetler",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.xl.scaled(s)),
                onBackClick = { navController.popBackStack() },
                scale = s
            )

            Spacer(Modifier.height(UiDimens.m.scaled(s)))

            Surface(
                color = colors.background,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = UiDimens.xs.scaled(s))
                ) {
                    items(list, key = { it.id }) { item ->

                        DmBoxListRow(
                            item = item,
                            scale = s,
                            onClick = {
                                navController.navigate("DmBoxChatScreen/${item.id}")

                            }
                        )
/*
                        Divider(
                            color = colors.outlineVariant,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = (76.dp).scaled(s))
                        )
                        */
                    }
                }
            }
        }
    }
}

@Composable
private fun DmBoxListRow(
    item: DmBoxItemUi,
    scale: Float,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    val rowPadV = UiDimens.m.scaled(scale)
    val avatarSize = (82.dp).scaled(scale)
    val badgeH = (26.dp).scaled(scale)
    val badgePadH = (10.dp).scaled(scale)

    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = UiDimens.xs.scaled(scale), vertical = rowPadV),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape)
                .background(colors.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Avatar",
                tint = colors.onSurfaceVariant,
                modifier = Modifier.size((46.dp).scaled(scale))
            )
        }

        Spacer(Modifier.width(UiDimens.m.scaled(scale)))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            androidx.compose.foundation.layout.Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    fontSize = (18.sp).scaled(scale),
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.width(UiDimens.s.scaled(scale)))

                Box(
                    modifier = Modifier
                        .defaultMinSize(minHeight = badgeH)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colors.primary)
                        .padding(horizontal = badgePadH, vertical = 0.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.badge,
                        color = colors.onPrimary,
                        fontSize = (12.sp).scaled(scale),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }

            Spacer(Modifier.height((4.dp).scaled(scale)))

            androidx.compose.foundation.layout.Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.subtitle,
                    fontSize = (14.sp).scaled(scale),
                    color = colors.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.width(UiDimens.s.scaled(scale)))

                Text(
                    text = item.timeLeftText,
                    fontSize = (11.sp).scaled(scale),
                    color = colors.onSurfaceVariant,
                    maxLines = 1
                )
            }
        }
    }
}




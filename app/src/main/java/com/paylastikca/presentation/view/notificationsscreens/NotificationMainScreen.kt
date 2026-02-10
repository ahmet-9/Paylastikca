package com.paylastikca.presentation.view.notificationsscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive

// ---------- SCREEN ----------
@Composable
fun NotificationMainScreen(navController: NavController) {
    val notifications = fakeNotifications(30)
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = colors.surfaceVariant)
    }

    PageScaffold(
        containerColor = colors.surfaceVariant,
        currentRoute = "NotificationMainScreen",
        navController = navController,
        bottomBarContainerColor = colors.surfaceVariant,
        applySafeInsets = true,
        removeTopInset = true,

    ) { innerPadding ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = adaptive.pageHorizontalPadding),
            contentAlignment = if (adaptive.maxContentWidth != null)
                Alignment.TopCenter
            else
                Alignment.TopStart
        ) {
            val contentModifier = if (adaptive.maxContentWidth != null) {
                Modifier
                    .fillMaxWidth()
                    .widthIn(max = adaptive.maxContentWidth)
            } else {
                Modifier.fillMaxWidth()
            }

            LazyColumn(
                modifier = contentModifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    NotificationsTopBar()
                    Spacer(Modifier.height(12.dp))
                }

                items(notifications, key = { it.id }) { item ->
                   NotificationCard(
                        item = item,
                        onClick = { /* TODO: detay / deep link */ }
                    )
                }


            }
        }
    }
}

// ---------- TOP BAR ----------
@Composable
private fun NotificationsTopBar() {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bildirimler",
            style = MaterialTheme.typography.titleLarge,
            color = colors.onBackground
        )

        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .width(190.dp)
                .height(3.dp)
                .background(colors.outline, RoundedCornerShape(100))
        )
    }
}

// ---------- CARD ----------
@Composable
private fun NotificationCard(
    item: UiNotification,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val shape = RoundedCornerShape(16.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, shape)
            .clickable(onClick = onClick),
        shape = shape,
        color = colors.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(colors.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    tint = colors.onPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = item.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(Modifier.width(10.dp))

            Text(
                text = item.timeText,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.tertiary
            )
        }
    }
}

// ---------- MODEL + SAMPLE DATA ----------
private data class UiNotification(
    val id: String,
    val title: String,
    val body: String,
    val timeText: String
)

private fun fakeNotifications(count: Int): List<UiNotification> {
    return List(count) { index ->
        UiNotification(
            id = (index + 1).toString(),
            title = "Bildirim başlığı ${index + 1}",
            body = "Bildirimın açıklaması bu.\nBildirimın açıklaması bu.",
            timeText = "${index + 1} dk."
        )
    }
}

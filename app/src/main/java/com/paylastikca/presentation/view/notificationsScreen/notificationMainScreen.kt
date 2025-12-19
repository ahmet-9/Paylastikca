
package com.paylastikca.presentation.view.notificationsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// ---------- SCREEN ----------
@Composable
fun notificationMainScreen() {
    val notifications = fakeNotifications(30)

    Scaffold(
        containerColor = Color(0xFFF6F6F6),
        bottomBar = {
            com.paylastikca.presentation.view.commonUI.components.BottomBar()
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            NotificationsTopBar()

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(notifications, key = { it.id }) { item ->
                    NotificationCard(item = item, onClick = { /* TODO: detay / deep link */ })
                }

                item { Spacer(Modifier.height(72.dp)) }
            }
        }
    }
}

// ---------- TOP BAR ----------
@Composable
private fun NotificationsTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()

            .padding(top = 18.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bildirimler",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .width(190.dp)
                .height(4.dp)
                .background(Color(0xFF6B6B6B), RoundedCornerShape(100))
        )
    }
}

// ---------- CARD ----------
@Composable
private fun NotificationCard(
    item: UiNotification,
    onClick: () -> Unit
) {
    val orange = Color(0xFFFF8A00)
    val timeGreen = Color(0xFF2E7D32)
    val shape = RoundedCornerShape(16.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, shape)
            .clickable(onClick = onClick),
        shape = shape,
        color = Color.White
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
                    .background(orange, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = item.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF4A4A4A),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(Modifier.width(10.dp))

            Text(
                text = item.timeText,
                style = MaterialTheme.typography.bodyMedium,
                color = timeGreen
            )
        }
    }
}

// ---------- BOTTOM BAR ----------
private enum class BottomTab { Home, Share, Notifications, Profile }



@Composable
private fun BottomIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    orange: Color,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (isSelected) orange else orange.copy(alpha = 0.75f),
        modifier = Modifier
            .size(34.dp)
            .clickable(onClick = onClick)
    )
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










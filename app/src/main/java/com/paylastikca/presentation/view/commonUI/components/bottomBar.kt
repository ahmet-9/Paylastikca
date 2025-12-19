package com.paylastikca.presentation.view.commonUI.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R

@Composable
fun BottomBar() {

    var selectedIndex by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier.height(70.dp),
        windowInsets = WindowInsets(0)
    ) {
        val items = listOf(
            Pair(R.drawable.homeiconmenu, "Ana Sayfa"),
            Pair(R.drawable.cooperationiconmenu, "Yardımlaş"),
            Pair(R.drawable.notificationiconmenu, "Bildirim"),
            Pair(R.drawable.profileiconmenu, "Profil")
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },



                icon = {
                    Icon(
                        painter = painterResource(id = item.first),
                        contentDescription = item.second,
                        modifier = Modifier.size(35.dp)
                    )
                },
                label = { Text(" ", fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color(0xFFE67E00),
                    indicatorColor = Color(0xFFE67E00)
                )
            )
        }
    }
}
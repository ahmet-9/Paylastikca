package com.paylastikca.presentation.view.sharingscreens.help.submit_help

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled

@Composable
fun HelpThankYouScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    PageScaffold(containerColor = colors.background) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.onSurface.copy(alpha = 0.06f)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.86f)
                    .background(colors.surface, RoundedCornerShape(16.dp))
                    .padding(18.dp.scaled(scale)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Teşekkür Ederiz!",
                    fontSize = 20.sp.scaled(scale),
                    fontWeight = FontWeight.Black,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(10.dp.scaled(scale)))

                Text(
                    text = "Yardım paylaşımın alındı.",
                    fontSize = 13.sp.scaled(scale),
                    color = colors.onSurfaceVariant
                )

                Spacer(Modifier.height(18.dp.scaled(scale)))

                Button(
                    onClick = {
                        // Popup kapat: submit ekranına dön
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp.scaled(scale)),
                    shape = RoundedCornerShape(14.dp.scaled(scale)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    )
                ) {
                    Text(
                        text = "Tamam",
                        fontSize = 15.sp.scaled(scale),
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}

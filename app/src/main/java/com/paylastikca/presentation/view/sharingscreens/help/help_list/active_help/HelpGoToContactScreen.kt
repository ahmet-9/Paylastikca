package com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun HelpGoToContactScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    PageScaffold(containerColor = colors.background) {

        // Arka plan overlay + kart
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.onSurface.copy(alpha = 0.06f)),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.86f)
                    .background(colors.surface, RoundedCornerShape(16.dp))
                    .padding(16.dp.scaled(scale))
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    // Avatar placeholder
                    Box(
                        modifier = Modifier
                            .size(84.dp.scaled(scale))
                            .background(colors.surfaceVariant, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🙂", fontSize = 28.sp.scaled(scale))
                    }

                    Spacer(Modifier.height(10.dp.scaled(scale)))

                    Text(
                        text = "Ahmet badem",
                        fontSize = 18.sp.scaled(scale),
                        fontWeight = FontWeight.Black,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(4.dp.scaled(scale)))

                    Text(
                        text = "ÖĞRENCİ",
                        fontSize = 11.sp.scaled(scale),
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurfaceVariant
                    )

                    Spacer(Modifier.height(6.dp.scaled(scale)))

                    Text(
                        text = "★★★★★ 15 kişi puanladı",
                        fontSize = 11.sp.scaled(scale),
                        fontWeight = FontWeight.SemiBold,
                        color = colors.onSurfaceVariant
                    )

                    Spacer(Modifier.height(10.dp.scaled(scale)))

                    // küçük açıklama
                    Text(
                        text = "Teşekkür Ederiz!",
                        fontSize = 12.sp.scaled(scale),
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(16.dp.scaled(scale)))

                    Button(
                        onClick = {
                            // Şimdilik backend yok:
                            // İleride burada chat/telefon/whatsapp yönlendirmesi yapılır.
                            // Şimdilik DM ekranına atabiliriz:
                            navController.navigate("DmBoxScreen")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp.scaled(scale)),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.onPrimary
                        )
                    ) {
                        Text(
                            text = "İletişime geç",
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp.scaled(scale)
                        )
                    }
                }
            }
        }
    }
}

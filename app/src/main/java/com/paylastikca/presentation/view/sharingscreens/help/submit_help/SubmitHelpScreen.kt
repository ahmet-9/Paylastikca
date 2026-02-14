package com.paylastikca.presentation.view.sharingscreens.help.submit_help

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.ScrollPage
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.templates.LocationVerifyHeader
import com.paylastikca.presentation.view.homepage_screens.CoinBadge
import com.paylastikca.ui.UiDimens

@Composable
fun SubmitHelpScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Kategori Seç") }

    var categoryMenuOpen by remember { mutableStateOf(false) }
    val categories = listOf("Halı", "Mobilya", "Kıyafet", "Elektronik", "Diğer")

    val descLimit = 345
    val descCount = desc.length.coerceAtMost(descLimit)



    PageScaffold(containerColor = colors.background) { innerPadding ->

        ScrollPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            BackButton(
                title = "Yardım Et",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.xl.scaled(scale)),
                onBackClick = { navController.popBackStack() },
                scale = scale
            )

            // ✅ TEMPLATE HEADER (adres + doğrula butonu + coinbadge)
            LocationVerifyHeader(
                addressText = "konya/kadinhanı/karakayamah/hatip sok.../",
                scale = scale,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = UiDimens.xs.scaled(scale)),
                coinBadge = { CoinBadge() }
            )


            Spacer(Modifier.height(UiDimens.l.scaled(scale)))

            // Foto ekleme kutusu (placeholder)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp.scaled(scale))
                    .clip(RoundedCornerShape(16.dp.scaled(scale)))
                    .background(colors.surfaceVariant.copy(alpha = 0.55f))
                    .border(1.dp, colors.outlineVariant, RoundedCornerShape(16.dp.scaled(scale))),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 34.sp.scaled(scale),
                    fontWeight = FontWeight.Light,
                    color = colors.onSurfaceVariant
                )
            }

            Spacer(Modifier.height(UiDimens.l.scaled(scale)))

            // Başlık
            Text(
                text = "Başlık",
                fontSize = 13.sp.scaled(scale),
                fontWeight = FontWeight.Bold,
                color = colors.onSurface
            )
            Spacer(Modifier.height(6.dp.scaled(scale)))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it.take(60) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp.scaled(scale)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.outline,
                    unfocusedBorderColor = colors.outlineVariant,
                    cursorColor = colors.primary
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 13.sp.scaled(scale),
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(Modifier.height(UiDimens.m.scaled(scale)))

            // Açıklama
            Text(
                text = "Açıklama",
                fontSize = 13.sp.scaled(scale),
                fontWeight = FontWeight.Bold,
                color = colors.onSurface
            )
            Spacer(Modifier.height(6.dp.scaled(scale)))

            OutlinedTextField(
                value = desc,
                onValueChange = { desc = it.take(descLimit) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 110.dp.scaled(scale)),
                shape = RoundedCornerShape(12.dp.scaled(scale)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.outline,
                    unfocusedBorderColor = colors.outlineVariant,
                    cursorColor = colors.primary
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 13.sp.scaled(scale),
                    fontWeight = FontWeight.Normal
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "$descCount/$descLimit",
                    fontSize = 10.sp.scaled(scale),
                    color = colors.onSurfaceVariant
                )
            }

            Spacer(Modifier.height(UiDimens.m.scaled(scale)))

            // Kategori
            Text(
                text = "Kategori",
                fontSize = 13.sp.scaled(scale),
                fontWeight = FontWeight.Bold,
                color = colors.onSurface
            )
            Spacer(Modifier.height(6.dp.scaled(scale)))

            Box {
                val fieldShape = RoundedCornerShape(14.dp.scaled(scale))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp.scaled(scale))
                        .shadow(
                            elevation = if (categoryMenuOpen) 6.dp.scaled(scale) else 0.dp,
                            shape = fieldShape
                        )
                        .clip(fieldShape)
                        .background(colors.surface)
                        .border(
                            width = 1.5.dp,
                            color = if (categoryMenuOpen) colors.primary else colors.outlineVariant,
                            shape = fieldShape
                        )
                        .clickable { categoryMenuOpen = true }
                        .padding(horizontal = 16.dp.scaled(scale)),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = category,
                        fontSize = 14.sp.scaled(scale),
                        fontWeight = FontWeight.SemiBold,
                        color = if (category == "Kategori Seç") colors.onSurfaceVariant else colors.onSurface,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.categori_icon),
                        contentDescription = null,
                        tint = colors.onSurfaceVariant,
                        modifier = Modifier.size(18.dp.scaled(scale))
                    )
                }

                DropdownMenu(
                    expanded = categoryMenuOpen,
                    onDismissRequest = { categoryMenuOpen = false },
                    properties = PopupProperties(focusable = true),
                    modifier = Modifier
                        .fillMaxWidth(0.94f)
                        .background(Color.Transparent)
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp.scaled(scale)),
                        colors = CardDefaults.cardColors(containerColor = colors.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp.scaled(scale)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(vertical = 8.dp.scaled(scale))) {
                            categories.forEachIndexed { index, c ->
                                val isSelected = c == category

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp.scaled(scale)))
                                        .background(if (isSelected) colors.primaryContainer else Color.Transparent)
                                        .clickable {
                                            category = c
                                            categoryMenuOpen = false
                                        }
                                        .padding(
                                            horizontal = 16.dp.scaled(scale),
                                            vertical = 12.dp.scaled(scale)
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .size(10.dp.scaled(scale))
                                            .clip(RoundedCornerShape(50))
                                            .background(if (isSelected) colors.primary else colors.outlineVariant)
                                    )

                                    Spacer(Modifier.width(14.dp.scaled(scale)))

                                    Text(
                                        text = c,
                                        fontSize = 14.sp.scaled(scale),
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) colors.onPrimaryContainer else colors.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )

                                    if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = colors.primary,
                                            modifier = Modifier.size(18.dp.scaled(scale))
                                        )
                                    }
                                }

                                if (index != categories.lastIndex) {
                                    Spacer(Modifier.height(4.dp.scaled(scale)))
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(UiDimens.l.scaled(scale)))

            Button(
                onClick = { navController.navigate("HelpThankYouScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp.scaled(scale)),
                shape = RoundedCornerShape(14.dp.scaled(scale)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary,
                    disabledContainerColor = colors.primary.copy(alpha = 0.35f),
                    disabledContentColor = colors.onPrimary.copy(alpha = 0.8f)
                )
            ) {
                Text(
                    text = "Paylaş",
                    fontSize = 16.sp.scaled(scale),
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(Modifier.height(UiDimens.xl.scaled(scale)))
        }
    }
}

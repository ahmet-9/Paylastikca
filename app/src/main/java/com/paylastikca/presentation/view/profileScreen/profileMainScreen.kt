package com.paylastikca.presentation.view.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.presentation.view.commonUI.components.BottomBar

private val AppOrange = Color(0xFFE67E00)
private val ScreenBg = Color(0xFFF6F6F6)
private val CardBg = Color.White
private val Muted = Color(0xFF8F8F8F)
private val Border = Color(0xFFEAEAEA)


private val KPI_HEIGHT = 92.dp

@Composable
fun profileMainScreen() {
    Scaffold(
        containerColor = ScreenBg,
        bottomBar = { BottomBar() },
        contentWindowInsets = WindowInsets(0)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 90.dp)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFF2E5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null,
                                tint = AppOrange,
                                modifier = Modifier.size(70.dp)
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "isim soyisim",
                                fontSize = 21.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF1A1A1A),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = "öğrenci",
                                fontSize = 17.sp,
                                color = Muted,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFF7F7F7))
                                .clickable { }
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "edit",
                                tint = Color(0xFF333333),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = "Düzenle",
                                fontSize = 12.sp,
                                color = Color(0xFF333333),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))



                    Spacer(Modifier.height(14.dp))
                    Divider(color = Border, thickness = 1.dp)
                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        KpiChipPuan(
                            title = "Puan",
                            value = "4.2/5",
                            rating = 4.2f,
                            modifier = Modifier.weight(1f)
                        )

                        KpiChipRozet(
                            title = "Rozet",
                            badgeText = "Yardımsever",
                            modifier = Modifier.weight(1f)
                        )

                        KpiChipPaylasim(
                            title = "Etkileşim",
                            count = "25",
                            subtitle = "adet gerçekleşti",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            SectionTitle("Hesap")
            SectionCard {
                ProfileRowItem(Icons.Filled.WorkspacePremium, "Rozetler") { }
                Divider(color = Border)
                ProfileRowItem(Icons.Filled.Lock, "Gizlilik ve güvenlik") { }
                Divider(color = Border)
                ProfileRowItem(Icons.Filled.Phone, "İletişim bilgileri") { }
            }

            Spacer(Modifier.height(14.dp))

            SectionTitle("Uygulama")
            SectionCard {
                ProfileRowItem(Icons.Filled.Notifications, "Bildirimler") { }
                Divider(color = Border)
                ProfileRowItem(Icons.Filled.Settings, "Uygulama Tercihleri") { }
            }

            Spacer(Modifier.height(14.dp))

            SectionTitle("Destek")
            SectionCard {
                ProfileRowItem(Icons.Filled.HelpOutline, "Yardım ve bilgilendirme") { }
            }
        }
    }
}

/* ---------------- KPI VARIANTS ---------------- */

@Composable
private fun RowScope.KpiChipPuan(
    title: String,
    value: String,
    rating: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(KPI_HEIGHT)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF7F7F7))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 11.sp, color = Muted)

        Spacer(Modifier.height(6.dp))

        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A1A),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )


        Spacer(Modifier.weight(1f))

        RatingStarsSmall(rating = rating)
    }
}

@Composable
private fun RowScope.KpiChipRozet(
    title: String,
    badgeText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(KPI_HEIGHT)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF7F7F7))
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                color = Muted
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = badgeText,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A1A),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }


        Image(
            painter = painterResource(id = R.drawable.lvldeneme),
            contentDescription = "rozet logo",
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (0).dp)
                .size(65.dp)
        )
    }
}


@Composable
private fun RowScope.KpiChipPaylasim(
    title: String,
    count: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(KPI_HEIGHT)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF7F7F7))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 11.sp, color = Muted)

        Spacer(Modifier.height(6.dp))

        Text(
            text = count,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A1A)
        )


        Spacer(Modifier.weight(1f))

        Text(
            text = subtitle,
            fontSize = 11.sp,
            color = Muted,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

/* ---------------- STARS ---------------- */

@Composable
private fun RatingStarsSmall(rating: Float) {
    val r = rating.coerceIn(0f, 5f)

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..5) {
            val icon = when {
                r >= i -> Icons.Filled.Star
                r >= i - 0.5f -> Icons.Filled.StarHalf
                else -> Icons.Filled.StarBorder
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF444444),
                modifier = Modifier.size(14.dp)
            )
            if (i != 5) Spacer(Modifier.width(2.dp))
        }
    }
}

/* ---------------- SECTIONS ---------------- */

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color(0xFF8C8C8C),
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
    )
}

@Composable
private fun SectionCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), content = content)
    }
}

@Composable
private fun ProfileRowItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFF2E5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = AppOrange, modifier = Modifier.size(18.dp))
        }

        Spacer(Modifier.width(12.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A1A),
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = Color(0xFFBDBDBD),
            modifier = Modifier.size(22.dp)
        )
    }
}

package com.paylastikca.presentation.view.sharingscreens.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Report
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.ui.UiDimens

@Immutable
data class ReportTargetUi(
    val userId: String,
    val name: String,
    val subtitle: String? = null
)

@Composable
fun ReportUserScreen(
    navController: NavController,
    target: ReportTargetUi
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val s = adaptive.scale

    val scroll = rememberScrollState()

    val categories = remember {
        listOf(
            "Dolandırıcılık / Ücret talebi",
            "Hakaret / Taciz",
            "Uygunsuz içerik",
            "Spam",
            "Yanlış / aldatıcı bilgi",
            "Diğer"
        )
    }

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var detail by remember { mutableStateOf("") }
    var evidenceCount by remember { mutableIntStateOf(0) }

    val minChars = 10
    val maxChars = 500
    val canSend = (selectedCategory != null) && (detail.trim().length >= minChars)

    val pagePadH = adaptive.pageHorizontalPadding
    val cardShape = RoundedCornerShape((20.dp).scaled(s))
    val fieldShape = RoundedCornerShape((18.dp).scaled(s))
    val chipShape = RoundedCornerShape((16.dp).scaled(s))

    PageScaffold(
        containerColor = colors.background,
        applySafeInsets = true,
        removeTopInset = true
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            BackButton(
                title = "Şikayet Et",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.s.scaled(s)),
                onBackClick = { navController.popBackStack() },
                scale = s
            )

            Spacer(Modifier.height(UiDimens.m.scaled(s)))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(horizontal = pagePadH)
            ) {

                // ===== TARGET CARD =====
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = cardShape,
                    color = colors.surface,
                    tonalElevation = 2.dp,
                    shadowElevation = 0.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(UiDimens.m.scaled(s))
                    ) {
                        androidx.compose.foundation.layout.Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size((64.dp).scaled(s))
                                    .clip(CircleShape)
                                    .background(colors.surfaceVariant),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = null,
                                    tint = colors.onSurfaceVariant,
                                    modifier = Modifier.size((28.dp).scaled(s))
                                )
                            }

                            Spacer(Modifier.width(UiDimens.m.scaled(s)))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = target.name,
                                    fontSize = (20.sp).scaled(s),
                                    fontWeight = FontWeight.ExtraBold,
                                    color = colors.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.height((4.dp).scaled(s)))
                                Text(
                                    text = target.subtitle ?: "Sohbet üzerinden",
                                    fontSize = (13.sp).scaled(s),
                                    fontWeight = FontWeight.SemiBold,
                                    color = colors.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Surface(
                                color = colors.error,
                                shape = RoundedCornerShape(999.dp)
                            ) {
                                androidx.compose.foundation.layout.Row(
                                    modifier = Modifier.padding(
                                        horizontal = UiDimens.s.scaled(s),
                                        vertical = (7.dp).scaled(s)
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Report,
                                        contentDescription = null,
                                        tint = colors.onError,
                                        modifier = Modifier.size((16.dp).scaled(s)),

                                    )
                                    Spacer(Modifier.width((6.dp).scaled(s)))
                                    Text(
                                        text = "Şikayet",
                                        fontSize = (12.sp).scaled(s),
                                        fontWeight = FontWeight.Bold,
                                        color = colors.onError
                                    )
                                }
                            }
                        }




                    } /*---*/

                }/****/

                Spacer(Modifier.height(UiDimens.l.scaled(s)))

                // ===== CATEGORY CHIPS =====
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = cardShape,
                    color = colors.surface,
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(UiDimens.m.scaled(s))
                    ) {

                        Text(
                            text = "Kategori seç",
                            fontSize = (15.sp).scaled(s),
                            fontWeight = FontWeight.ExtraBold,
                            color = colors.onSurface
                        )
                        Spacer(Modifier.height((6.dp).scaled(s)))
                        Text(
                            text = "Doğru kategori seçmek süreci hızlandırır.",
                            fontSize = (13.sp).scaled(s),
                            color = colors.onSurfaceVariant
                        )

                        Spacer(Modifier.height(UiDimens.m.scaled(s)))
                        HorizontalDivider(color = colors.outlineVariant)
                        Spacer(Modifier.height(UiDimens.m.scaled(s)))


                        categories.chunked(2).forEach { row ->
                            androidx.compose.foundation.layout.Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy((10.dp).scaled(s))
                            ) {
                                row.forEach { item ->
                                    val selected = selectedCategory == item
                                    val bg = if (selected) colors.primary else colors.surfaceVariant
                                    val fg = if (selected) colors.onPrimary else colors.onSurface
                                    val border = if (selected) colors.primary else colors.outlineVariant

                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .heightIn(min = (56.dp).scaled(s))
                                            .clip(chipShape)
                                            .clickable { selectedCategory = item },
                                        shape = chipShape,
                                        color = bg,
                                        border = BorderStroke(1.dp, border)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(horizontal = UiDimens.m.scaled(s)),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            Text(
                                                text = item,
                                                fontSize = (14.sp).scaled(s),
                                                fontWeight = FontWeight.Bold,
                                                color = fg,
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis,
                                                lineHeight = (18.sp).scaled(s)
                                            )
                                        }
                                    }
                                }

                                if (row.size == 1) {
                                    Spacer(Modifier.weight(1f))
                                }
                            }

                            Spacer(Modifier.height((10.dp).scaled(s)))
                        }
                    }
                }

                Spacer(Modifier.height(UiDimens.l.scaled(s)))

                // ===== DETAIL =====
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = cardShape,
                    color = colors.surface,
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(UiDimens.m.scaled(s))
                    ) {
                        Text(
                            text = "Açıklama",
                            fontSize = (15.sp).scaled(s),
                            fontWeight = FontWeight.ExtraBold,
                            color = colors.onSurface
                        )
                        Spacer(Modifier.height((6.dp).scaled(s)))
                        Text(
                            text = "Ne olduğunu anlat.",
                            fontSize = (13.sp).scaled(s),
                            color = colors.onSurfaceVariant
                        )

                        Spacer(Modifier.height(UiDimens.m.scaled(s)))

                        OutlinedTextField(
                            value = detail,
                            onValueChange = { v ->
                                detail = if (v.length <= maxChars) v else v.take(maxChars)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((170.dp).scaled(s)),
                            placeholder = {
                                Text(
                                    text = "Örnek: “Ürünü vermedi, ücret istedi.”",
                                    fontSize = (14.sp).scaled(s),
                                    color = colors.onSurfaceVariant
                                )
                            },
                            shape = fieldShape,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = colors.background,
                                unfocusedContainerColor = colors.background,
                                focusedIndicatorColor = colors.outlineVariant,
                                unfocusedIndicatorColor = colors.outlineVariant,
                                cursorColor = colors.primary
                            )
                        )

                        Spacer(Modifier.height((8.dp).scaled(s)))

                        androidx.compose.foundation.layout.Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${detail.length}/$maxChars",
                                fontSize = (12.sp).scaled(s),
                                color = colors.onSurfaceVariant
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = if (detail.trim().length < minChars) "En az $minChars karakter" else "",
                                fontSize = (12.sp).scaled(s),
                                fontWeight = FontWeight.Bold,
                                color = if (detail.trim().length < minChars) colors.error else colors.primary
                            )
                        }
                    }
                }

                Spacer(Modifier.height(UiDimens.l.scaled(s)))

                // ===== EVIDENCE =====
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(cardShape)
                        .clickable { evidenceCount = (evidenceCount + 1).coerceAtMost(3) },
                    shape = cardShape,
                    color = colors.surface,
                    tonalElevation = 2.dp
                ) {
                    androidx.compose.foundation.layout.Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(UiDimens.m.scaled(s)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size((48.dp).scaled(s))
                                .clip(RoundedCornerShape((16.dp).scaled(s)))
                                .background(colors.surfaceVariant),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AddPhotoAlternate,
                                contentDescription = null,
                                tint = colors.primary,
                                modifier = Modifier.size((24.dp).scaled(s))
                            )
                        }

                        Spacer(Modifier.width(UiDimens.m.scaled(s)))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Kanıt ekle",
                                fontSize = (15.sp).scaled(s),
                                fontWeight = FontWeight.ExtraBold,
                                color = colors.onSurface
                            )
                            Spacer(Modifier.height((2.dp).scaled(s)))
                            Text(
                                text = "Ekran görüntüsü / fotoğraf (opsiyonel)",
                                fontSize = (13.sp).scaled(s),
                                color = colors.onSurfaceVariant
                            )
                        }

                        Surface(
                            color = colors.surfaceVariant,
                            shape = RoundedCornerShape(999.dp)
                        ) {
                            Text(
                                text = "$evidenceCount/3",
                                modifier = Modifier.padding(
                                    horizontal = UiDimens.s.scaled(s),
                                    vertical = (8.dp).scaled(s)
                                ),
                                fontSize = (12.sp).scaled(s),
                                fontWeight = FontWeight.Bold,
                                color = colors.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(Modifier.height(UiDimens.l.scaled(s)))

                // ===== NOTICE =====
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = cardShape,

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(UiDimens.m.scaled(s)),
                        verticalAlignment = Alignment.Top
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier
                                .size((38.dp).scaled(s))
                        )

                        Spacer(Modifier.width(UiDimens.s.scaled(s)))

                        Text(
                            text = "Yanlış beyan hesabının kısıtlanmasına neden olabilir. Şikayetler gizli incelenir.",
                            fontSize = (13.sp).scaled(s),
                            color = colors.onSurfaceVariant,
                            lineHeight = (18.sp).scaled(s),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }


                Spacer(Modifier.height(UiDimens.l.scaled(s)))

                Button(
                    onClick = { navController.popBackStack() },
                    enabled = canSend,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((60.dp).scaled(s)),
                    shape = RoundedCornerShape((18.dp).scaled(s)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary,
                        disabledContainerColor = colors.surfaceVariant,
                        disabledContentColor = colors.onSurfaceVariant
                    )
                ) {
                    Text(
                        text = "Gönder",
                        fontSize = (18.sp).scaled(s),
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(Modifier.height(UiDimens.xl.scaled(s)))
            }
        }
    }
}

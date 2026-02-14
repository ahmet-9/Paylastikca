package com.paylastikca.presentation.view.common_ui.components.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.scaled

/**
 * Help / Request / Swap liste kartı ortak şablonu
 * - Figma'daki "Talep Var" üstte ortada chip'li tasarım
 */
@Composable
fun SharingItemCard(
    title: String,
    desc: String,
    categoryLabel: String,          // "KATEGORİ: Halı"
    rightTopChipText: String?,      // "Talep Var" / "Talep yok" / null
    rightTopChipTone: ChipTone,     // Positive / Neutral
    rightButtonText: String?,       // "İsteği gör" / "Puan Ver" / null
    onRightButtonClick: (() -> Unit)?,
    showDelete: Boolean,
    onDelete: (() -> Unit)?,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    val cardShape = RoundedCornerShape(26.dp.scaled(scale))
    val cardPaddingH = 18.dp.scaled(scale)
    val cardPaddingV = 16.dp.scaled(scale)

    val imageW = 140.dp.scaled(scale)
    val imageH = 92.dp.scaled(scale)
    val imageShape = RoundedCornerShape(14.dp.scaled(scale))

    val chipHeight = 34.dp.scaled(scale)

    Box(
        modifier = modifier.fillMaxWidth()
    ) {

        // Kart
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(cardShape)
                .background(colors.primaryContainer) // senin theme’deki açık bej
                .padding(horizontal = cardPaddingH, vertical = cardPaddingV),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Sol görsel (şimdilik placeholder)
            Box(
                modifier = Modifier
                    .size(width = imageW, height = imageH)
                    .clip(imageShape)
                    .background(colors.surfaceVariant)
            )

            Spacer(Modifier.width(16.dp.scaled(scale)))

            // Orta metinler
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp.scaled(scale))
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp.scaled(scale),
                    fontWeight = FontWeight.Black,
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(4.dp.scaled(scale)))

                Text(
                    text = desc,
                    fontSize = 13.sp.scaled(scale),
                    fontWeight = FontWeight.Normal,
                    color = colors.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(10.dp.scaled(scale)))

                Text(
                    text = categoryLabel,
                    fontSize = 13.sp.scaled(scale),
                    fontWeight = FontWeight.Black,
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Sağ taraf (buton + delete)
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.heightIn(min = imageH)
            ) {

                // Buton (ortaya yakın)
                if (!rightButtonText.isNullOrBlank() && onRightButtonClick != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(colors.primary) // turuncu dolu
                            .clickable { onRightButtonClick() }
                            .padding(
                                horizontal = 18.dp.scaled(scale),
                                vertical = 9.dp.scaled(scale)
                            )
                    ) {
                        Text(
                            text = rightButtonText,
                            fontSize = 14.sp.scaled(scale),
                            fontWeight = FontWeight.Black,
                            color = colors.onPrimary
                        )
                    }
                } else {
                    Spacer(Modifier.height(1.dp))
                }

                Spacer(Modifier.height(12.dp.scaled(scale)))

                // Delete sağ alt
                if (showDelete && onDelete != null) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Sil",
                        tint = colors.error,
                        modifier = Modifier
                            .size(28.dp.scaled(scale))
                            .clickable { onDelete() }
                    )
                }
            }
        }

        // Üstte ortada chip (kartın üzerine oturan)
        if (!rightTopChipText.isNullOrBlank()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-chipHeight / 2))
            ) {
                RequestChip(
                    text = rightTopChipText,
                    tone = rightTopChipTone,
                    height = chipHeight,
                    scale = scale
                )
            }
        }
    }
}

enum class ChipTone { Positive, Neutral }

@Composable
private fun RequestChip(
    text: String,
    tone: ChipTone,
    height: Dp,
    scale: Float
) {
    val colors = MaterialTheme.colorScheme

    val isPositive = tone == ChipTone.Positive

    // Figmadaki gibi: yeşil dolu / gri dolu
    val pillBg = if (isPositive) colors.tertiary else colors.surfaceVariant
    val pillText = if (isPositive) colors.onTertiary else colors.onSurfaceVariant

    // Sağdaki daire: beyaz + ikon
    val circleBg = colors.surface
    val circleIcon = if (isPositive) colors.tertiary else colors.onSurfaceVariant
    val circleBorder = if (isPositive) colors.tertiary else colors.onSurfaceVariant.copy(alpha = 0.35f)

    val icon = if (isPositive) Icons.Outlined.Check else Icons.Outlined.Schedule

    val pillShape = RoundedCornerShape(50.dp)
    val circleSize = height

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(height)
            .clip(pillShape)
            .background(pillBg, pillShape)
            .padding(start = 16.dp.scaled(scale), end = 6.dp.scaled(scale))
    ) {
        Text(
            text = text,
            fontSize = 14.sp.scaled(scale),
            fontWeight = FontWeight.Medium,
            color = pillText,
            maxLines = 1
        )

        Spacer(Modifier.width(10.dp.scaled(scale)))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(circleSize)
                .clip(CircleShape)
                .background(circleBg, CircleShape)
                .border(2.dp, circleBorder, CircleShape)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = circleIcon,
                modifier = Modifier.size((height.value * 0.62f).dp)
            )
        }
    }
}

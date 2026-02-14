package com.paylastikca.presentation.view.common_ui.components.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.scaled

/**
 * Puan verme ekranı şablonu.
 * Help/Request/Swap bunu kullanır.
 */
@Composable
fun RatingTemplate(
    userName: String,
    badgeLabel: String?,              // örn: "ÖĞRENCİ" / null
    infoLine: String?,                // örn: "★★★★★ 15 kişi puanladı" / null
    itemTitle: String,
    itemDesc: String,
    itemCategoryLabel: String,
    rating: Int,
    onRatingChange: (Int) -> Unit,
    buttonText: String,
    onSubmit: () -> Unit,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(96.dp.scaled(scale))
                .background(colors.surfaceVariant, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("🙂", fontSize = 30.sp.scaled(scale))
        }

        Spacer(Modifier.height(10.dp.scaled(scale)))

        Text(
            userName,
            fontSize = 22.sp.scaled(scale),
            fontWeight = FontWeight.Black,
            color = colors.onSurface
        )

        if (!badgeLabel.isNullOrBlank()) {
            Spacer(Modifier.height(4.dp.scaled(scale)))
            Text(
                badgeLabel,
                fontSize = 11.sp.scaled(scale),
                fontWeight = FontWeight.Bold,
                color = colors.onSurfaceVariant
            )
        }

        if (!infoLine.isNullOrBlank()) {
            Spacer(Modifier.height(6.dp.scaled(scale)))
            Text(
                infoLine,
                fontSize = 11.sp.scaled(scale),
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurfaceVariant
            )
        }

        Spacer(Modifier.height(14.dp.scaled(scale)))

        // item card (shared style)
        SharingItemCard(
            title = itemTitle,
            desc = itemDesc,
            categoryLabel = itemCategoryLabel,
            rightTopChipText = null,
            rightTopChipTone = ChipTone.Neutral,
            rightButtonText = null,
            onRightButtonClick = null,
            showDelete = false,
            onDelete = null,
            scale = scale
        )

        Spacer(Modifier.height(18.dp.scaled(scale)))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp.scaled(scale)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..5) {
                RatingStar(
                    filled = i <= rating,
                    scale = scale,
                    onClick = { onRatingChange(i) }
                )
            }
        }

        Spacer(Modifier.height(18.dp.scaled(scale)))

        Button(
            onClick = onSubmit,
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
                buttonText,
                fontSize = 16.sp.scaled(scale),
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
private fun RatingStar(
    filled: Boolean,
    scale: Float,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .size(34.dp.scaled(scale))
            .background(
                color = if (filled) colors.primaryContainer else colors.surfaceVariant,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            if (filled) "★" else "☆",
            fontSize = 20.sp.scaled(scale),
            color = if (filled) colors.primary else colors.onSurfaceVariant,
            fontWeight = FontWeight.Black
        )
    }
}

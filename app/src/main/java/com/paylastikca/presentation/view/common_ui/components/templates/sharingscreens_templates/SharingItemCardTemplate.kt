package com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.scaled

@Composable
fun SharingItemCardTemplate(
    title: String,
    desc: String,
    categoryLabel: String,

    rightTopChipText: String?,
    rightTopChipTone: ChipTone,

    rightButtonText: String?,
    onRightButtonClick: (() -> Unit)?,

    showDelete: Boolean,
    onDelete: (() -> Unit)?,

    showTopRightCheck: Boolean = false,

    imageContent: (@Composable BoxScope.() -> Unit)? = null,

    moreText: String = "Devamı",
    deleteContentDesc: String = "Sil",

    @DrawableRes checkIconRes: Int = R.drawable.checkcircle,
    @DrawableRes neutralChipIconRes: Int = R.drawable.timeicon,
    @DrawableRes positiveChipIconRes: Int = R.drawable.checkcircle,

    modifier: Modifier = Modifier,

    scale: Float = 1f
) {
    val colors = MaterialTheme.colorScheme

    val cfg = LocalConfiguration.current
    val screenWidthDp = cfg.screenWidthDp

    val cardShape = RoundedCornerShape(24.dp.scaled(scale))
    val cardPaddingH = 16.dp.scaled(scale)
    val cardPaddingV = 14.dp.scaled(scale)

    val imageW = 122.dp.scaled(scale)
    val imageH = 78.dp.scaled(scale)
    val imageShape = RoundedCornerShape(14.dp.scaled(scale))

    val gapAfterImage = 14.dp.scaled(scale)

    val titleSize = 20.sp.scaled(scale)
    val descSize = 12.sp.scaled(scale)
    val catSize = 12.sp.scaled(scale)

    val chipHeight = 32.dp.scaled(scale)

    val topBtnHeight = 30.dp.scaled(scale)

    val topBtnBaseTextSp = when {
        screenWidthDp < 360 -> 14f
        screenWidthDp < 400 -> 15f
        else -> 16f
    }
    val topBtnTextSize = topBtnBaseTextSp.sp.scaled(scale)

    val topBtnHorizontalPadding = when {
        screenWidthDp < 360 -> 10.dp.scaled(scale)
        screenWidthDp < 400 -> 12.dp.scaled(scale)
        else -> 14.dp.scaled(scale)
    }

    val chipToSideShift = 270.dp.scaled(scale)

    val topDeleteSize = 44.dp.scaled(scale)
    val topDeleteIconSize = 30.dp.scaled(scale)

    val topRowOffsetY = (-7).dp.scaled(scale)

    val pastChipHeight = 34.dp.scaled(scale)
    val pastChipTextSize = 14.sp.scaled(scale)

    val pastRateGap = 4.dp.scaled(scale)
    val pastRateOffsetY = -(pastChipHeight + pastRateGap)

    val pastDoneOffsetY = -pastChipHeight

    val isActiveStyle = !rightTopChipText.isNullOrBlank()
    val isPastStyle = !isActiveStyle

    val showTopRightButton =
        isActiveStyle &&
                rightTopChipTone == ChipTone.Positive &&
                !rightButtonText.isNullOrBlank() &&
                onRightButtonClick != null

    val showTopLeftDelete =
        isActiveStyle &&
                rightTopChipTone == ChipTone.Positive &&
                showDelete &&
                onDelete != null

    val showTopRightDeleteWhenNeutral =
        isActiveStyle &&
                rightTopChipTone == ChipTone.Neutral &&
                showDelete &&
                onDelete != null

    val showPastTopRightCheck =
        isPastStyle && showTopRightCheck && onRightButtonClick == null

    var showDescDialog by remember { mutableStateOf(false) }

    if (showDescDialog) {
        Dialog(onDismissRequest = { showDescDialog = false }) {
            val dialogShape = RoundedCornerShape(18.dp.scaled(scale))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp.scaled(scale))
                    .clip(dialogShape)
                    .background(colors.surface)
                    .border(1.dp, colors.outlineVariant, dialogShape)
                    .padding(16.dp.scaled(scale))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp.scaled(scale),
                        fontWeight = FontWeight.Black,
                        color = colors.onSurface
                    )
                    Spacer(Modifier.height(10.dp.scaled(scale)))
                    Text(
                        text = desc,
                        fontSize = 14.sp.scaled(scale),
                        fontWeight = FontWeight.Normal,
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxWidth()) {

        // -------------------- CARD BODY --------------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(cardShape)
                .background(colors.primaryContainer)
                .padding(horizontal = cardPaddingH, vertical = cardPaddingV),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = imageW, height = imageH)
                    .clip(imageShape)
                    .background(colors.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                imageContent?.invoke(this)
            }

            Spacer(Modifier.width(gapAfterImage))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp.scaled(scale))
            ) {
                Text(
                    text = title,
                    fontSize = titleSize,
                    fontWeight = FontWeight.Black,
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(3.dp.scaled(scale)))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDescDialog = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = desc,
                        fontSize = descSize,
                        fontWeight = FontWeight.Normal,
                        color = colors.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(Modifier.width(6.dp.scaled(scale)))

                    Text(
                        text = moreText,
                        fontSize = descSize,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface,
                        maxLines = 1,
                        modifier = Modifier.clickable { showDescDialog = true }
                    )
                }

                Spacer(Modifier.height(8.dp.scaled(scale)))

                Text(
                    text = categoryLabel,
                    fontSize = catSize,
                    fontWeight = FontWeight.Black,
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.heightIn(min = imageH)
            ) {
                Spacer(Modifier.height(1.dp))

                if (isPastStyle && showDelete && onDelete != null) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = deleteContentDesc,
                        tint = colors.error,
                        modifier = Modifier
                            .size(48.dp.scaled(scale))
                            .clickable { onDelete() }
                            .padding(top = 8.dp.scaled(scale))
                    )
                }
            }
        }

        if (showPastTopRightCheck) {
            Icon(
                painter = painterResource(id = checkIconRes),
                contentDescription = null,
                tint = colors.tertiary,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        end = 8.dp.scaled(scale),
                        top = 8.dp.scaled(scale)
                    )
                    .size(25.dp.scaled(scale))
            )
        }

        // -------------------- ACTIVE TOP RAIL --------------------
        if (isActiveStyle) {
            val topRailHeight = maxOf(chipHeight, topBtnHeight, topDeleteSize)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .offset(y = -chipHeight)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .height(topRailHeight)
                        .offset(y = topRowOffsetY)
                ) {
                    Box(modifier = Modifier.align(Alignment.Center)) {
                        RequestChipHalfCapsule(
                            text = rightTopChipText!!,
                            tone = rightTopChipTone,
                            height = chipHeight,
                            scale = scale,
                            positiveIconRes = positiveChipIconRes,
                            neutralIconRes = neutralChipIconRes
                        )
                    }

                    if (showTopLeftDelete) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(end = chipToSideShift)
                                .size(topDeleteSize)
                                .clip(RoundedCornerShape(10.dp.scaled(scale)))
                                .clickable { onDelete?.invoke() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = deleteContentDesc,
                                tint = colors.error,
                                modifier = Modifier.size(topDeleteIconSize)
                            )
                        }
                    }

                    if (showTopRightButton) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(start = chipToSideShift)
                                .height(topBtnHeight)
                                .clip(RoundedCornerShape(50.dp.scaled(scale)))
                                .background(colors.primary)
                                .clickable { onRightButtonClick?.invoke() }
                                .padding(horizontal = topBtnHorizontalPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = rightButtonText!!,
                                fontSize = topBtnTextSize,
                                fontWeight = FontWeight.Black,
                                color = colors.onPrimary,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }

                    if (showTopRightDeleteWhenNeutral) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(start = chipToSideShift)
                                .size(topDeleteSize)
                                .clip(RoundedCornerShape(10.dp.scaled(scale)))
                                .clickable { onDelete?.invoke() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = deleteContentDesc,
                                tint = colors.error,
                                modifier = Modifier.size(topDeleteIconSize)
                            )
                        }
                    }
                }
            }
        }

        // -------------------- PAST TOP CHIP --------------------
        if (isPastStyle && !rightButtonText.isNullOrBlank()) {
            val isRate = onRightButtonClick != null

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = if (isRate) pastRateOffsetY else pastDoneOffsetY)
            ) {
                PastChip(
                    text = rightButtonText,
                    clickable = onRightButtonClick,
                    height = pastChipHeight,
                    textSize = pastChipTextSize,
                    scale = scale
                )
            }
        }
    }
}

enum class ChipTone { Positive, Neutral }

@Composable
private fun RequestChipHalfCapsule(
    text: String,
    tone: ChipTone,
    height: Dp,
    scale: Float,
    @DrawableRes positiveIconRes: Int,
    @DrawableRes neutralIconRes: Int,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val isPositive = tone == ChipTone.Positive

    val pillBg = if (isPositive) colors.tertiary else colors.surfaceVariant
    val pillText = if (isPositive) colors.onTertiary else colors.onSurfaceVariant

    val circleIcon = if (isPositive) colors.onTertiary else colors.onSurfaceVariant
    val iconRes = if (isPositive) positiveIconRes else neutralIconRes

    val halfCapsule = RoundedCornerShape(
        topStart = 18.dp.scaled(scale),
        topEnd = 18.dp.scaled(scale),
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(height)
            .clip(halfCapsule)
            .background(pillBg, halfCapsule)
            .padding(start = 14.dp.scaled(scale), end = 10.dp.scaled(scale))
    ) {
        Text(
            text = text,
            fontSize = 13.sp.scaled(scale),
            fontWeight = FontWeight.Medium,
            color = pillText,
            maxLines = 1
        )

        Spacer(Modifier.width(10.dp.scaled(scale)))

        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = circleIcon,
            modifier = Modifier.size((height.value * 0.62f).dp)
        )
    }
}

@Composable
private fun PastChip(
    text: String,
    clickable: (() -> Unit)?,
    height: Dp,
    textSize: TextUnit,
    scale: Float
) {
    val colors = MaterialTheme.colorScheme
    val isRate = clickable != null

    val shape = if (isRate) {
        RoundedCornerShape(50.dp.scaled(scale))
    } else {
        RoundedCornerShape(
            topStart = 14.dp.scaled(scale),
            topEnd = 14.dp.scaled(scale),
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    }

    val bg = if (isRate) colors.inverseSurface else colors.surface
    val fg = if (isRate) colors.inverseOnSurface else colors.onSurfaceVariant
    val border = if (isRate) null else colors.outlineVariant

    val base = Modifier
        .height(height)
        .clip(shape)
        .then(if (border != null) Modifier.border(2.dp, border, shape) else Modifier)
        .background(bg, shape)
        .padding(horizontal = 18.dp.scaled(scale))

    val m = if (clickable != null) base.clickable { clickable() } else base

    Box(modifier = m, contentAlignment = Alignment.Center) {
        Text(
            text = text,
            fontSize = textSize,
            fontWeight = FontWeight.Black,
            color = fg,
            maxLines = 1,
            softWrap = false
        )
    }
}

package com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.ui.theme.YellowDark
import com.paylastikca.ui.theme.YellowLight
import kotlin.math.max


sealed class RequestDecisionState {
    data class Pending(
        val roleTitle: String,
        val locationText: String,
        val distanceText: String,

        val ratingText: String,
        val ratingValue: Float,

        val badgeText: String,
        @DrawableRes val badgeRes: Int,

        val interactionCountText: String,
        val interactionSubtitle: String,

        val itemTitle: String,
        val itemDesc: String,
        val itemCategoryLabel: String,
        @DrawableRes val itemImageRes: Int? = null,

        val infoLines: List<String>,
    ) : RequestDecisionState()

    data class Accepted(
        val fullName: String,
        @DrawableRes val avatarRes: Int? = null,
        val infoLines: List<String>,
    ) : RequestDecisionState()
}

@Composable
fun RequestRevealTemplate(
    state: RequestDecisionState,
    scale: Float,
    maxContentWidth: Dp? = null,
    horizontalPadding: Dp = 16.dp,
    modifier: Modifier = Modifier,


    bottomInsetPadding: Dp = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),

    onApprove: (() -> Unit)? = null,
    onReject: (() -> Unit)? = null,
    onContact: (() -> Unit)? = null,

    @DrawableRes warningIconRes: Int = R.drawable.warningicon,
) {
    val colors = MaterialTheme.colorScheme

    val pageBg = colors.primaryContainer
    val cardBg = colors.surface

    val contentCardRadius = 18.dp.scaled(scale)
    val innerPad = 14.dp.scaled(scale)


    val roleSize = 30.sp.scaled(scale)
    val roleLineH = 34.sp.scaled(scale)

    val locSize = 13.sp.scaled(scale)
    val locLineH = 17.sp.scaled(scale)

    val distSize = 12.sp.scaled(scale)

    val buttonH = 52.dp.scaled(scale)
    val btnText = 17.sp.scaled(scale)


    val infoMinH = 150.dp.scaled(scale)
    val infoMaxH = 260.dp.scaled(scale)

    var showDescDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogDesc by remember { mutableStateOf("") }

    if (showDescDialog) {
        Dialog(onDismissRequest = { showDescDialog = false }) {
            val shape = RoundedCornerShape(18.dp.scaled(scale))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .background(colors.surface, shape)
                    .border(1.dp, colors.outlineVariant, shape)
                    .padding(16.dp.scaled(scale))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = dialogTitle,
                        fontSize = 18.sp.scaled(scale),
                        fontWeight = FontWeight.Black,
                        color = colors.onSurface
                    )
                    Spacer(Modifier.height(10.dp.scaled(scale)))
                    Text(
                        text = dialogDesc,
                        fontSize = 14.sp.scaled(scale),
                        lineHeight = 18.sp.scaled(scale),
                        fontWeight = FontWeight.Normal,
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }
    }

    Surface(
        color = pageBg,
        contentColor = colors.onBackground,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    bottom = bottomInsetPadding
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .let { base -> if (maxContentWidth != null) base.widthIn(max = maxContentWidth) else base }
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp.scaled(scale))
                ) {
                    when (state) {

                        is RequestDecisionState.Pending -> {

                            // ---- HEADER CARD
                            val headerShape = RoundedCornerShape(contentCardRadius)
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(headerShape)
                                    .border(2.dp, colors.outlineVariant, headerShape),
                                shape = headerShape,
                                colors = CardDefaults.cardColors(containerColor = cardBg),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(innerPad),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = state.roleTitle,
                                        fontSize = roleSize,
                                        lineHeight = roleLineH,
                                        fontWeight = FontWeight.Black,
                                        color = colors.onSurface,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Spacer(Modifier.height(8.dp.scaled(scale)))

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = state.locationText,
                                            fontSize = locSize,
                                            lineHeight = locLineH,
                                            fontWeight = FontWeight.SemiBold,
                                            color = colors.onSurfaceVariant,
                                            textAlign = TextAlign.Center,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.fillMaxWidth(0.92f)
                                        )
                                        Spacer(Modifier.height(2.dp.scaled(scale)))
                                        Text(
                                            text = state.distanceText,
                                            fontSize = distSize,
                                            fontWeight = FontWeight.Medium,
                                            color = colors.onSurfaceVariant,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }

                                    Spacer(Modifier.height(12.dp.scaled(scale)))

                                    HorizontalDivider(color = colors.outlineVariant, thickness = 1.dp)

                                    Spacer(Modifier.height(12.dp.scaled(scale)))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp.scaled(scale))
                                    ) {
                                        KpiPuan(
                                            title = "Puan",
                                            value = state.ratingText,
                                            ratingValue = state.ratingValue,
                                            scale = scale,
                                            modifier = Modifier.weight(1f)
                                        )
                                        KpiRozet(
                                            title = "Rozet",
                                            badgeText = state.badgeText,
                                            badgeRes = state.badgeRes,
                                            scale = scale,
                                            modifier = Modifier.weight(1f)
                                        )
                                        KpiEtkilesim(
                                            title = "Etkileşim",
                                            count = state.interactionCountText,
                                            subtitle = state.interactionSubtitle,
                                            scale = scale,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }

                            // ---- ITEM CARD
                            val itemShape = RoundedCornerShape(14.dp.scaled(scale))
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(itemShape)
                                    .border(2.dp, colors.outlineVariant, itemShape),
                                shape = itemShape,
                                colors = CardDefaults.cardColors(containerColor = cardBg),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp.scaled(scale)),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    val thumbW = 60.dp.scaled(scale)
                                    val thumbH = 44.dp.scaled(scale)
                                    val thumbShape = RoundedCornerShape(10.dp.scaled(scale))

                                    Box(
                                        modifier = Modifier
                                            .size(width = thumbW, height = thumbH)
                                            .clip(thumbShape)
                                            .background(colors.surfaceVariant),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        val res = state.itemImageRes
                                        if (res != null) {
                                            Icon(
                                                painter = painterFromRes(res),
                                                contentDescription = null,
                                                tint = colors.onSurfaceVariant,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                    }

                                    Spacer(Modifier.width(10.dp.scaled(scale)))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = state.itemTitle,
                                            fontSize = 15.sp.scaled(scale),
                                            fontWeight = FontWeight.Black,
                                            color = colors.onSurface,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Spacer(Modifier.height(2.dp.scaled(scale)))

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = state.itemDesc,
                                                fontSize = 13.sp.scaled(scale),
                                                lineHeight = 16.sp.scaled(scale),
                                                fontWeight = FontWeight.Medium,
                                                color = colors.onSurfaceVariant,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.weight(1f)
                                            )

                                            Spacer(Modifier.width(8.dp.scaled(scale)))

                                            Text(
                                                text = "Devamı",
                                                fontSize = 13.sp.scaled(scale),
                                                fontWeight = FontWeight.Black,
                                                color = colors.onSurface,
                                                maxLines = 1,
                                                modifier = Modifier.clickable {
                                                    dialogTitle = state.itemTitle
                                                    dialogDesc = state.itemDesc
                                                    showDescDialog = true
                                                }
                                            )
                                        }

                                        Spacer(Modifier.height(6.dp.scaled(scale)))

                                        Text(
                                            text = state.itemCategoryLabel,
                                            fontSize = 12.sp.scaled(scale),
                                            fontWeight = FontWeight.Black,
                                            color = colors.onSurface,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }


                            InfoBoxScrollable(
                                lines = state.infoLines,
                                scale = scale,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = infoMinH, max = infoMaxH)
                            )
                        }

                        is RequestDecisionState.Accepted -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = innerPad,
                                        end = innerPad,
                                        bottom = innerPad,
                                        top = 0.dp.scaled(scale)
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp.scaled(scale)),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(colors.tertiary.copy(alpha = 0.15f))
                                        .border(1.dp, colors.tertiary, RoundedCornerShape(50))
                                        .padding(
                                            horizontal = 12.dp.scaled(scale),
                                            vertical = 6.dp.scaled(scale)
                                        )
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = null,
                                        tint = colors.tertiary,
                                        modifier = Modifier.size(28.dp.scaled(scale))
                                    )

                                    Text(
                                        text = "İstek Onaylandı",
                                        fontSize = 23.sp.scaled(scale),
                                        fontWeight = FontWeight.Bold,
                                        color = colors.tertiary
                                    )
                                }

                                Spacer(Modifier.height(20.dp.scaled(scale)))

                                val avatarSize = 156.dp.scaled(scale)

                                Box(
                                    modifier = Modifier
                                        .size(avatarSize)
                                        .clip(CircleShape)
                                        .background(colors.surfaceVariant),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val av = state.avatarRes
                                    if (av != null) {
                                        androidx.compose.foundation.Image(
                                            painter = painterFromRes(av),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    } else {
                                        Text("🙂", fontSize = 34.sp.scaled(scale))
                                    }
                                }

                                Spacer(Modifier.height(12.dp.scaled(scale)))

                                Text(
                                    text = state.fullName,
                                    fontSize = 40.sp.scaled(scale),
                                    fontWeight = FontWeight.Black,
                                    color = colors.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(0.92f)
                                )
                            }

                            InfoBoxScrollable(
                                lines = state.infoLines,
                                scale = scale,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = infoMinH, max = infoMaxH)
                            )
                        }
                    }
                }

                // ---------- BOTTOM FIXED ACTIONS ----------
                when (state) {
                    is RequestDecisionState.Pending -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp.scaled(scale))
                                .padding(bottom = 18.dp.scaled(scale)),
                            horizontalArrangement = Arrangement.spacedBy(12.dp.scaled(scale))
                        ) {
                            OutlinedButton(
                                onClick = { onReject?.invoke() },
                                enabled = onReject != null,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(buttonH),
                                border = BorderStroke(1.5.dp, colors.error),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "Reddet",
                                    fontSize = btnText,
                                    fontWeight = FontWeight.Black,
                                    color = colors.error,
                                    maxLines = 1
                                )
                            }

                            Button(
                                onClick = { onApprove?.invoke() },
                                enabled = onApprove != null,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(buttonH),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colors.tertiary,
                                    contentColor = colors.onTertiary,
                                    disabledContainerColor = colors.tertiary.copy(alpha = 0.35f),
                                    disabledContentColor = colors.onTertiary.copy(alpha = 0.8f)
                                )
                            ) {
                                Text(
                                    text = "Onayla",
                                    fontSize = btnText,
                                    fontWeight = FontWeight.Black,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                    is RequestDecisionState.Accepted -> {

                        val warnShape = RoundedCornerShape(14.dp.scaled(scale))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp.scaled(scale), bottom = 10.dp.scaled(scale)),
                            shape = warnShape,
                            colors = CardDefaults.cardColors(containerColor = YellowLight),
                            elevation = CardDefaults.cardElevation(0.dp),
                            border = BorderStroke(1.dp, YellowDark)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 14.dp.scaled(scale),
                                        vertical = 12.dp.scaled(scale)
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp.scaled(scale))
                            ) {
                                Icon(
                                    painter = painterFromRes(warningIconRes),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(32.dp.scaled(scale))
                                )

                                Text(
                                    text = "İletişime 24 saat içinde geçiniz, aksi halde hakkınız kaybolacaktır.",
                                    fontSize = 13.sp.scaled(scale),
                                    lineHeight = 16.sp.scaled(scale),
                                    fontWeight = FontWeight.SemiBold,
                                    color = colors.onTertiaryContainer,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        Button(
                            onClick = { onContact?.invoke() },
                            enabled = onContact != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp.scaled(scale), bottom = 40.dp)
                                .height(buttonH),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colors.primary,
                                contentColor = colors.onPrimary,
                                disabledContainerColor = colors.primary.copy(alpha = 0.35f),
                                disabledContentColor = colors.onPrimary.copy(alpha = 0.8f)
                            )
                        ) {
                            Text(
                                text = "İletişime geç",
                                fontSize = btnText,
                                fontWeight = FontWeight.Black,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

/* ---------------- KMM MIGRATION SINGLE POINT ---------------- */
@Composable
private fun painterFromRes(@DrawableRes resId: Int) = painterResource(id = resId)

/* ---------------- INFO BOX (ONLY THIS SCROLLS + SCROLL INDICATOR) ---------------- */

@Composable
private fun InfoBoxScrollable(
    lines: List<String>,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val shape = RoundedCornerShape(12.dp.scaled(scale))
    val bg = colors.onSurfaceVariant.copy(alpha = 0.22f)

    val scrollState = rememberScrollState()

    var viewportHeightPx by remember { mutableIntStateOf(0) }
    var contentHeightPx by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .clip(shape)
            .background(bg, shape)
            .border(2.dp, colors.onSurfaceVariant, shape)
            .padding(12.dp.scaled(scale))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(26.dp.scaled(scale))
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = colors.onSurfaceVariant,
                        modifier = Modifier.size(38.dp.scaled(scale))
                    )
                }
            }

            Spacer(Modifier.height(10.dp.scaled(scale)))

            Box(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .onSizeChanged { viewportHeightPx = it.height }
                        .verticalScroll(scrollState)
                        .padding(end = 12.dp.scaled(scale))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onSizeChanged { contentHeightPx = it.height }
                    ) {
                        lines.forEachIndexed { i, line ->
                            Text(
                                text = "${i + 1}- $line",
                                fontSize = 13.sp.scaled(scale),
                                lineHeight = 17.sp.scaled(scale),
                                fontWeight = FontWeight.SemiBold,
                                color = colors.onSurface,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(8.dp.scaled(scale)))
                        }
                    }
                }

                ScrollIndicatorMeasured(
                    scrollState = scrollState,
                    viewportHeightPx = viewportHeightPx,
                    contentHeightPx = contentHeightPx,
                    scale = scale,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
private fun ScrollIndicatorMeasured(
    scrollState: androidx.compose.foundation.ScrollState,
    viewportHeightPx: Int,
    contentHeightPx: Int,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val density = androidx.compose.ui.platform.LocalDensity.current

    if (scrollState.maxValue <= 0) return
    if (viewportHeightPx <= 0) return
    if (contentHeightPx <= 0) return

    val trackW = 5.dp.scaled(scale)
    val radius = 999.dp

    val trackH = with(density) { viewportHeightPx.toDp() }

    val ratioVisible =
        (viewportHeightPx.toFloat() / contentHeightPx.toFloat()).coerceIn(0.05f, 1f)

    val thumbMinH = 26.dp.scaled(scale)
    val thumbH = max(thumbMinH.value, (trackH.value * ratioVisible)).dp

    val progress =
        (scrollState.value.toFloat() / scrollState.maxValue.toFloat()).coerceIn(0f, 1f)
    val travel = (trackH.value - thumbH.value).coerceAtLeast(0f)
    val thumbOffsetY = (travel * progress).dp

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(trackW)
                .height(trackH)
                .clip(RoundedCornerShape(radius))
                .background(colors.onSurfaceVariant.copy(alpha = 0.28f))
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = thumbOffsetY)
                .width(trackW)
                .height(thumbH)
                .clip(RoundedCornerShape(radius))
                .background(colors.onSurfaceVariant.copy(alpha = 0.85f))
        )
    }
}

/* ---------------- KPI CHIPS ---------------- */

@Composable
private fun KpiPuan(
    title: String,
    value: String,
    ratingValue: Float,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val h = 84.dp.scaled(scale)

    Column(
        modifier = modifier
            .height(h)
            .clip(RoundedCornerShape(12.dp.scaled(scale)))
            .background(colors.surfaceVariant)
            .padding(horizontal = 10.dp.scaled(scale), vertical = 8.dp.scaled(scale)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 11.sp.scaled(scale), color = colors.onSurfaceVariant, maxLines = 1)

        Spacer(Modifier.height(6.dp.scaled(scale)))

        Text(
            text = value,
            fontSize = 14.sp.scaled(scale),
            fontWeight = FontWeight.SemiBold,
            color = colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.weight(1f))

        RatingStarsSmall(ratingValue = ratingValue, scale = scale)
    }
}

@Composable
private fun KpiRozet(
    title: String,
    badgeText: String,
    @DrawableRes badgeRes: Int,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val h = 84.dp.scaled(scale)

    Box(
        modifier = modifier
            .height(h)
            .clip(RoundedCornerShape(12.dp.scaled(scale)))
            .background(colors.surfaceVariant)
            .padding(horizontal = 10.dp.scaled(scale), vertical = 8.dp.scaled(scale))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 11.sp.scaled(scale), color = colors.onSurfaceVariant, maxLines = 1)
            Spacer(Modifier.weight(1f))
            Text(
                text = badgeText,
                fontSize = 13.sp.scaled(scale),
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        androidx.compose.foundation.Image(
            painter = painterFromRes(badgeRes),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(46.dp.scaled(scale)),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun KpiEtkilesim(
    title: String,
    count: String,
    subtitle: String,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val h = 84.dp.scaled(scale)

    Column(
        modifier = modifier
            .height(h)
            .clip(RoundedCornerShape(12.dp.scaled(scale)))
            .background(colors.surfaceVariant)
            .padding(horizontal = 10.dp.scaled(scale), vertical = 8.dp.scaled(scale)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 11.sp.scaled(scale), color = colors.onSurfaceVariant, maxLines = 1)

        Spacer(Modifier.height(6.dp.scaled(scale)))

        Text(
            text = count,
            fontSize = 16.sp.scaled(scale),
            fontWeight = FontWeight.SemiBold,
            color = colors.onSurface,
            maxLines = 1
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = subtitle,
            fontSize = 11.sp.scaled(scale),
            lineHeight = 14.sp.scaled(scale),
            color = colors.onSurfaceVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun RatingStarsSmall(
    ratingValue: Float,
    scale: Float
) {
    val colors = MaterialTheme.colorScheme
    val r = ratingValue.coerceIn(0f, 5f)
    val star = 13.dp.scaled(scale)

    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..5) {
            val filled = r >= i
            Icon(
                imageVector = if (filled) Icons.Filled.Star else Icons.Filled.StarBorder,
                contentDescription = null,
                tint = colors.onSurface,
                modifier = Modifier.size(star)
            )
            if (i != 5) Spacer(Modifier.width(2.dp.scaled(scale)))
        }
    }
}
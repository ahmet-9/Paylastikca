package com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.ChipTone
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.SharingItemCardTemplate
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt


enum class RatingTargetType(val key: String) {
    Help("help"),
    Request("request"),
    Swap("swap");

    fun uiLabel(): String = when (this) {
        Help -> "Yardım"
        Request -> "İstek"
        Swap -> "Takas"
    }

    companion object {
        fun fromKey(key: String?): RatingTargetType = when (key?.lowercase()) {
            "help" -> Help
            "request" -> Request
            "swap" -> Swap
            else -> Help
        }
    }
}

@Composable
fun RatingTemplate(
    targetType: RatingTargetType,

    userName: String,
    badgeLabel: String?,
    infoLine: String?,

    itemTitle: String,
    itemDesc: String,
    itemCategoryLabel: String,

    rating: Int,
    onRatingChange: (Int) -> Unit,

    primaryButtonText: String,
    onSubmit: () -> Unit,

    scale: Float,
    modifier: Modifier = Modifier,

    avatar: @Composable (ColumnScope.() -> Unit)? = null,
    itemImageContent: (@Composable (BoxScope.() -> Unit))? = null
) {
    val colors = MaterialTheme.colorScheme

    val topPad = 10.dp.scaled(scale)

    val avatarSize = 220.dp.scaled(scale)
    val avatarRadius = 50.dp.scaled(scale)

    val nameSize = 28.sp.scaled(scale)

    val cardMaxW = 330.dp.scaled(scale)
    val cardTopSpace = 18.dp.scaled(scale)

    val starIcon = 42.dp.scaled(scale)
    val starGap = 12.dp.scaled(scale)

    val buttonWFrac = 0.68f
    val buttonH = 56.dp.scaled(scale)
    val buttonRadius = 999.dp
    val buttonText = 20.sp.scaled(scale)

    val footerText = "${targetType.uiLabel()} değerlendirmesi"

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = topPad),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            shape = RoundedCornerShape(avatarRadius),
            color = colors.surfaceVariant,
            modifier = Modifier.size(avatarSize)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (avatar != null) {
                    avatar(this)
                } else {
                    Text("🙂", fontSize = 38.sp.scaled(scale))
                }
            }
        }

        Spacer(Modifier.height(34.dp.scaled(scale)))

        Text(
            text = userName,
            fontSize = nameSize,
            fontWeight = FontWeight.Black,
            color = colors.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(14.dp.scaled(scale)))

        if (!badgeLabel.isNullOrBlank()) {
            Spacer(Modifier.height(6.dp.scaled(scale)))
            Text(
                text = badgeLabel,
                fontSize = 12.sp.scaled(scale),
                fontWeight = FontWeight.Bold,
                color = colors.onSurfaceVariant,
                maxLines = 1
            )
        }

        if (!infoLine.isNullOrBlank()) {
            Spacer(Modifier.height(6.dp.scaled(scale)))
            Text(
                text = infoLine,
                fontSize = 12.sp.scaled(scale),
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.height(cardTopSpace))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp.scaled(scale)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.fillMaxWidth().widthIn(max = cardMaxW)) {
                SharingItemCardTemplate(
                    title = itemTitle,
                    desc = itemDesc,
                    categoryLabel = itemCategoryLabel,
                    rightTopChipText = null,
                    rightTopChipTone = ChipTone.Neutral,
                    rightButtonText = null,
                    onRightButtonClick = null,
                    showDelete = false,
                    onDelete = null,
                    imageContent = itemImageContent,
                    scale = scale
                )
            }
        }

        Spacer(Modifier.height(26.dp.scaled(scale)))

        Row(
            horizontalArrangement = Arrangement.spacedBy(starGap),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..5) {
                RatingStar(
                    selected = i <= rating,
                    scale = scale,
                    touchSize = starIcon,
                    iconSize = starIcon,
                    onClick = { onRatingChange(i) }
                )
            }
        }

        Spacer(Modifier.height(28.dp.scaled(scale)))

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth(buttonWFrac)
                .height(buttonH),
            shape = RoundedCornerShape(buttonRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary,
                disabledContainerColor = colors.primary.copy(alpha = 0.35f),
                disabledContentColor = colors.onPrimary.copy(alpha = 0.8f)
            )
        ) {
            Text(
                text = primaryButtonText,
                fontSize = buttonText,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }

        Spacer(Modifier.height(10.dp.scaled(scale)))

        Text(
            text = footerText,
            fontSize = 11.sp.scaled(scale),
            fontWeight = FontWeight.SemiBold,
            color = colors.onSurfaceVariant
        )
    }
}

@Composable
private fun RatingStar(
    selected: Boolean,
    scale: Float,
    touchSize: Dp,
    iconSize: Dp,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .size(touchSize)
            .clip(RoundedCornerShape(12.dp.scaled(scale)))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(iconSize)) {
            val w = size.width
            val h = size.height
            val cx = w / 2f
            val cy = h / 2f

            val outerR = min(w, h) * 0.50f
            val innerR = outerR * 0.52f

            val corner = outerR * 0.22f

            val path = roundedStarPath(
                center = Offset(cx, cy),
                outerRadius = outerR,
                innerRadius = innerR,
                cornerRadius = corner
            )

            if (selected) {
                drawPath(path = path, color = colors.primary)
            } else {
                drawPath(
                    path = path,
                    color = colors.outlineVariant,
                    style = Stroke(
                        width = outerR * 0.16f,
                        cap = StrokeCap.Round
                    )
                )
            }
        }
    }
}

private fun roundedStarPath(
    center: Offset,
    outerRadius: Float,
    innerRadius: Float,
    cornerRadius: Float
): Path {
    val pts = starPoints(center, outerRadius, innerRadius)
    return roundedPolygonPath(pts, cornerRadius)
}

private fun starPoints(
    c: Offset,
    outerR: Float,
    innerR: Float
): List<Offset> {
    val points = ArrayList<Offset>(10)
    val startAngle = -PI / 2.0
    val step = PI / 5.0

    for (i in 0 until 10) {
        val r = if (i % 2 == 0) outerR else innerR
        val a = startAngle + step * i
        points += Offset(
            x = c.x + (cos(a) * r).toFloat(),
            y = c.y + (sin(a) * r).toFloat()
        )
    }
    return points
}

private fun roundedPolygonPath(points: List<Offset>, cornerRadius: Float): Path {
    val n = points.size
    if (n < 3) return Path()

    fun len(v: Offset): Float = sqrt(v.x * v.x + v.y * v.y)
    fun norm(v: Offset): Offset {
        val l = len(v)
        return if (l == 0f) Offset.Zero else Offset(v.x / l, v.y / l)
    }

    val path = Path()

    run {
        val prev = points[(n - 1) % n]
        val curr = points[0]
        val next = points[1]

        val vPrev = norm(prev - curr)
        val dPrev = len(prev - curr)

        val dNext = len(next - curr)

        val d = min(cornerRadius, min(dPrev, dNext) * 0.35f)

        val start = curr + vPrev * d
        path.moveTo(start.x, start.y)
    }

    for (i in 0 until n) {
        val prev = points[(i - 1 + n) % n]
        val curr = points[i]
        val next = points[(i + 1) % n]

        val vPrev = norm(prev - curr)
        val vNext = norm(next - curr)

        val dPrev = len(prev - curr)
        val dNext = len(next - curr)

        val d = min(cornerRadius, min(dPrev, dNext) * 0.35f)

        val p1 = curr + vPrev * d
        val p2 = curr + vNext * d

        path.lineTo(p1.x, p1.y)
        path.quadraticTo(curr.x, curr.y, p2.x, p2.y)
    }

    path.close()
    return path
}


package com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.ui.UiDimens

@Composable
fun EmojiTemplateScreen(
    @DrawableRes topRightDecoRes: Int = R.drawable.rigttopline,
    @DrawableRes bottomLeftDecoRes: Int = R.drawable.leftbottomline,
    @DrawableRes emojiRes: Int = R.drawable.happyemoji,

    title: String = " ",
    primaryButtonText: String = "",
    onPrimaryClick: () -> Unit = {},
    secondaryText: String = "",
    onSecondaryClick: () -> Unit = {},

    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    fun clampDp(target: Dp, maxAllowed: Dp): Dp = if (target <= maxAllowed) target else maxAllowed

    val pageHPad = UiDimens.xl.scaled(scale)

    val emojiSize = (150.dp).scaled(scale)
    val titleSize = (40.sp).scaled(scale)
    val titleLineH = (44.sp).scaled(scale)

    val buttonH = (62.dp).scaled(scale)
    val buttonRadius = (22.dp).scaled(scale)
    val buttonTextSize = (22.sp).scaled(scale)
    val buttonTextLineH = (26.sp).scaled(scale)

    val secondaryTextSize = (22.sp).scaled(scale)
    val secondaryLineH = (26.sp).scaled(scale)

    val gap18 = (18.dp).scaled(scale)
    val gap12 = (12.dp).scaled(scale)
    val secPadV = (6.dp).scaled(scale)

    val contentMaxW = (420.dp).scaled(scale)

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        val decoTarget = (580.dp).scaled(scale)
        val tinyScreenThreshold = 320.dp
        val decoSize = if (maxWidth < tinyScreenThreshold) {
            clampDp(decoTarget, maxWidth)
        } else {
            decoTarget
        }


        val decoScale = if (adaptive.isTablet) ContentScale.Crop else ContentScale.Fit

        val topRightAlign = Alignment.TopEnd
        val bottomLeftAlign = Alignment.BottomStart

        Image(
            painter = painterResource(topRightDecoRes),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(decoSize),
            contentScale = decoScale,
            alignment = topRightAlign
        )

        Image(
            painter = painterResource(bottomLeftDecoRes),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(decoSize),
            contentScale = decoScale,
            alignment = bottomLeftAlign
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = pageHPad),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(emojiRes),
                contentDescription = null,
                modifier = Modifier.size(emojiSize),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(gap18))

            Text(
                text = title,
                fontSize = titleSize,
                lineHeight = titleLineH,
                fontWeight = FontWeight.Black,
                color = colors.onBackground,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(gap18))

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.86f)
                    .widthIn(max = contentMaxW),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onPrimaryClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(buttonH),
                    shape = RoundedCornerShape(buttonRadius),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    )
                ) {
                    Text(
                        text = primaryButtonText,
                        fontSize = buttonTextSize,
                        lineHeight = buttonTextLineH,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(Modifier.height(gap12))

                Text(
                    text = secondaryText,
                    fontSize = secondaryTextSize,
                    lineHeight = secondaryLineH,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onBackground,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSecondaryClick() }
                        .padding(vertical = secPadV)
                )
            }
        }
    }
}

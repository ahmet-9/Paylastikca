package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.presentation.view.commonUI.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.commonUI.components.platform.PlatformDialog
import com.paylastikca.ui.UiDimens

@Composable
fun WatchAdsScreen(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onWatchAd: () -> Unit,
    watchedCount: Int = 2
) {
    val colors = MaterialTheme.colorScheme
    if (!showDialog) return

    val adaptive = rememberAdaptive()

    PlatformDialog(onDismissRequest = onDismiss) {

        val shape = RoundedCornerShape(UiDimens.rL)

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .widthIn(max = adaptive.maxContentWidth ?: Dp.Unspecified)
                .wrapContentHeight()
                .background(colors.primaryContainer, shape = shape)
                .border(2.dp, colors.primary, shape)
                .padding(UiDimens.xl)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Günlük hakkınız",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Normal,
                    color = colors.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(UiDimens.m))

                Text(
                    text = "$watchedCount Reklam",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.onSurface,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(UiDimens.m))

                Text(
                    text = "İzle, Kazan!\nHer reklamda +1P çip senin olsun",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(UiDimens.xl))

                Button(
                    onClick = {
                        onWatchAd()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    ),
                    shape = RoundedCornerShape(UiDimens.rM),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(UiDimens.actionButtonH)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.videoicon),
                            contentDescription = "Reklam",
                            tint = colors.onPrimaryContainer,
                            modifier = Modifier.size(UiDimens.icon32)
                        )
                        Spacer(modifier = Modifier.width(UiDimens.m))
                        Text(
                            text = "Reklamı Başlat",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}

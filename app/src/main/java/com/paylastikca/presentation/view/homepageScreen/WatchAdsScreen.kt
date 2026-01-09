package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.paylastikca.app.R


@Composable
fun WatchAdsScreen(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onWatchAd: () -> Unit


){

    val colors = MaterialTheme.colorScheme

    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight()
                    .background(colors.primaryContainer, shape = RoundedCornerShape(16.dp))
                    .border(2.dp, colors.primary, RoundedCornerShape(16.dp))
                    .padding(24.dp)
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

                    Spacer(modifier = Modifier.height(12.dp))

                    val watchedCount = 2

                    Text(
                        text = "$watchedCount Reklam",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onSurface
                    )


                    Spacer(modifier = Modifier.height(12.dp))


                    Text(
                        text = "İzle, Kazan!\nHer reklamda +1P çip senin olsun",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(28.dp))


                    Button(
                        onClick = {
                            onWatchAd()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.onPrimary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.videoicon),
                                contentDescription = "Reklam",
                                tint = colors.onPrimaryContainer,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
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


}


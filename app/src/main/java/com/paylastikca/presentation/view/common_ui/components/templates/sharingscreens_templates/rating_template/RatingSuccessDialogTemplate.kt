package com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.rating_template


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.presentation.view.common_ui.components.platform.PlatformDialog


@Composable
fun RatingSuccessDialogTemplate(
    targetType: RatingTargetType,
    userName: String,
    rating: Int,
    buttonText: String,
    onButtonClick: () -> Unit,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme


    val shape = RoundedCornerShape(18.dp.scaled(scale))



    val iconSize = 60.dp.scaled(scale)

    val titleSize = 21.sp.scaled(scale)
    val msgSize = 14.sp.scaled(scale)

    val buttonH = 46.dp.scaled(scale)
    val buttonRadius = 14.dp.scaled(scale)
    val buttonTextSize = 14.sp.scaled(scale)

    PlatformDialog(
        onDismissRequest = {  }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),

            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = modifier
                    .fillMaxWidth(0.82f)
                    .clip(shape)
                    .background(colors.surface, shape)

                    .padding(18.dp.scaled(scale))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = colors.tertiary,
                            modifier = Modifier.size(iconSize)
                        )


                    Spacer(Modifier.height(14.dp.scaled(scale)))

                    Text(
                        text = "Puan Gönderildi",
                        fontSize = titleSize,
                        fontWeight = FontWeight.Black,
                        color = colors.onSurface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(6.dp.scaled(scale)))

                    Text(
                        text = "$userName • ${targetType.uiLabel()} için $rating puan verdiniz",
                        fontSize = msgSize,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp.scaled(scale)))

                    Button(
                        onClick = onButtonClick,
                        modifier = Modifier
                            .fillMaxWidth(0.55f)
                            .height(buttonH),
                        shape = RoundedCornerShape(buttonRadius),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.onPrimary
                        )
                    ) {
                        Text(
                            text = buttonText,
                            fontSize = buttonTextSize,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
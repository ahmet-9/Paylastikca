package com.paylastikca.presentation.view.common_ui.components.templates.app_templates

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.ui.UiDimens
import com.paylastikca.ui.theme.Gray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class VerifyUiState { Idle, Loading, Success, Error }

@Composable
fun LocationVerifyHeader(
    addressText: String,
    coinBadge: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    scale: Float = 1f,
    initialState: VerifyUiState = VerifyUiState.Idle
) {
    val colors = MaterialTheme.colorScheme

    val state = remember { mutableStateOf(initialState) }
    val nextWillBeSuccess = remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = addressText,
            onValueChange = { },
            readOnly = true,
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = UiDimens.textFieldMinH.scaled(scale)),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.mapsicon),
                    contentDescription = "Konum",
                    modifier = Modifier.size(UiDimens.icon32.scaled(scale)),
                    contentScale = ContentScale.Fit
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(UiDimens.rS.scaled(scale)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Gray,
                unfocusedIndicatorColor = colors.outline,
                disabledIndicatorColor = colors.outlineVariant,
                disabledTextColor = colors.onSurface,
                disabledLeadingIconColor = colors.onSurfaceVariant,
                cursorColor = colors.primary
            ),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp.scaled(scale),
                lineHeight = 18.sp.scaled(scale)
            )
        )

        Spacer(Modifier.height(UiDimens.xs.scaled(scale)))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            VerifyLocationButton(
                state = state,
                nextWillBeSuccess = nextWillBeSuccess,
                scale = scale
            )

            Spacer(Modifier.width(UiDimens.xl.scaled(scale)))

            coinBadge()
        }
    }
}

@Composable
private fun VerifyLocationButton(
    state: MutableState<VerifyUiState>,
    nextWillBeSuccess: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    scale: Float = 1f
) {
    val colors = MaterialTheme.colorScheme
    val scope = rememberCoroutineScope()

    val shape = RoundedCornerShape(14.dp.scaled(scale))
    val h = 44.dp.scaled(scale)

    val (bg, fg, label) = when (state.value) {
        VerifyUiState.Idle -> Triple(colors.surfaceVariant, colors.onSurface, "Konumu Doğrula")
        VerifyUiState.Loading -> Triple(colors.surfaceVariant, colors.onSurface, "Doğrulanıyor")
        VerifyUiState.Success -> Triple(colors.tertiary, colors.onTertiary, "Doğrulandı")
        VerifyUiState.Error -> Triple(colors.error, colors.onError, "Doğrulanamadı")
    }

    val infinite = rememberInfiniteTransition(label = "rotate")
    val rotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotateAnim"
    )

    Row(
        modifier = modifier
            .height(h)
            .clip(shape)
            .background(bg)
            .border(1.dp, colors.outlineVariant, shape)
            .clickable(enabled = state.value != VerifyUiState.Loading) {
                scope.launch {
                    state.value = VerifyUiState.Loading
                    delay(2000)

                    val success = nextWillBeSuccess.value
                    nextWillBeSuccess.value = !nextWillBeSuccess.value

                    state.value = if (success) VerifyUiState.Success else VerifyUiState.Error
                }
            }
            .padding(horizontal = 14.dp.scaled(scale)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (state.value == VerifyUiState.Success) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = fg,
                modifier = Modifier.size(18.dp.scaled(scale))
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.restarticon),
                contentDescription = null,
                tint = if (state.value == VerifyUiState.Error)
                    MaterialTheme.colorScheme.onError
                else
                    MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(18.dp.scaled(scale))
                    .graphicsLayer {
                        rotationZ = if (state.value == VerifyUiState.Loading) rotation else 0f
                    }
            )

        }

        Spacer(Modifier.width(8.dp.scaled(scale)))

        Text(
            text = label,
            fontSize = 12.sp.scaled(scale),
            fontWeight = FontWeight.Black,
            color = fg,
            maxLines = 1
        )
    }
}

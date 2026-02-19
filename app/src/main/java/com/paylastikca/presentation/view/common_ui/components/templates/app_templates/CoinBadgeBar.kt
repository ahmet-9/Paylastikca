package com.paylastikca.presentation.view.common_ui.components.templates.app_templates

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.ui.UiDimens

@Composable
fun CoinBadgeBar(

    coinCount: String = "13",
) {
    val colors = MaterialTheme.colorScheme

    val adaptive = com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive()
    val scale = adaptive.scale

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(UiDimens.coinSize.scaled(scale))
            .offset(x = 4.dp.scaled(scale), y = 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin_badge),
            contentDescription = "Coin Badge",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(UiDimens.coinSize.scaled(scale))
        )

        Text(
            text = coinCount,
            color = colors.onSurface,
            fontSize = 13.sp.scaled(scale),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset(
                x = 12.dp.scaled(scale),
                y = 1.dp.scaled(scale)
            )
        )
    }
}
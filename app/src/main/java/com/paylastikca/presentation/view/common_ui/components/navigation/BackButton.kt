package com.paylastikca.presentation.view.common_ui.components.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.ui.UiDimens

@Composable
fun BackButton(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    scale: Float = 1f
) {

    val minH = (UiDimens.actionButtonH.value * scale).dp
    val sideSpace = (UiDimens.actionButtonH.value * scale).dp
    val titleSizeSp = (22f * scale).sp
    val iconSize = (24f * scale).dp
    val buttonSize = (40f * scale).dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = minH),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (onBackClick != null) {
            IconButton(onClick = onBackClick,modifier = Modifier.size(buttonSize)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Geri",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(iconSize)
                )
            }
        } else {
            Spacer(modifier = Modifier.width(sideSpace))
        }

        Spacer(modifier = Modifier.width(UiDimens.xs))

        Text(
            text = title,
            fontSize = titleSizeSp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

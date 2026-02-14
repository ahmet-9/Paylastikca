package com.paylastikca.presentation.view.common_ui.components.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.scaled

/**
 * BU DOSYA: "Liste + 2 sekme" şablonu.
 * Help/Request/Swap hepsi bunu kullanacak.
 */
@Composable
fun TabbedListTemplate(
    title: String,
    leftTab: String,
    rightTab: String,
    selectedTabIndex: Int,
    onTabChange: (Int) -> Unit,
    scale: Float,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = title,
            fontSize = 18.sp.scaled(scale),
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 12.dp.scaled(scale))
        )
        SegmentedTabs(
            leftText = leftTab,
            rightText = rightTab,
            selectedIndex = selectedTabIndex,
            onSelect = onTabChange,
            scale = scale
        )


        Spacer(Modifier.height(14.dp.scaled(scale)))

        content()
    }
}

@Composable
private fun SegmentedTabs(
    leftText: String,
    rightText: String,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    scale: Float
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp.scaled(scale))
            .clip(RoundedCornerShape(50))
            .border(1.dp, colors.primary, RoundedCornerShape(50))
    ) {

        // LEFT
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(
                    if (selectedIndex == 0)
                        RoundedCornerShape(
                            topStart = 50.dp,
                            bottomStart = 50.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp
                        )
                    else RoundedCornerShape(
                        10.dp
                    )
                )
                .background(if (selectedIndex == 0) colors.primary else Color.Transparent)
                .clickable { onSelect(0) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = leftText,
                fontSize = 12.sp.scaled(scale),
                fontWeight = FontWeight.Bold,
                color = if (selectedIndex == 0) colors.onPrimary else colors.onSurface
            )
        }

        // RIGHT
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(
                    if (selectedIndex == 1)
                        RoundedCornerShape(
                            topEnd = 50.dp,
                            bottomEnd = 50.dp,
                            topStart = 0.dp,
                            bottomStart = 0.dp
                        )
                    else RoundedCornerShape(10.dp)
                )
                .background(if (selectedIndex == 1) colors.primary else Color.Transparent)
                .clickable { onSelect(1) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = rightText,
                fontSize = 12.sp.scaled(scale),
                fontWeight = FontWeight.Bold,
                color = if (selectedIndex == 1) colors.onPrimary else colors.onSurface
            )
        }
    }
}


@Composable
private fun TabButton(
    text: String,
    selected: Boolean,
    scale: Float,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(if (selected) colors.primary else colors.background)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp.scaled(scale),
            fontWeight = FontWeight.Bold,
            color = if (selected) colors.onPrimary else colors.onSurface
        )
    }
}

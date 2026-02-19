package com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.scaled


@Composable
fun TabbedListTemplate(
    title: String,
    leftTab: String,
    rightTab: String,
    selectedTabIndex: Int,
    onTabChange: (Int) -> Unit,
    scale: Float = 1f,
    content: @Composable ColumnScope.() -> Unit
) {
    val titleBottomSpace = 6.dp.scaled(scale)
    val tabsBottomSpace = 28.dp.scaled(scale)

    Column(modifier = Modifier.fillMaxWidth()) {

        if (title.isNotBlank()) {
            Text(
                text = title,
                fontSize = 18.sp.scaled(scale),
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = titleBottomSpace)
            )
        }

        SegmentedTabs(
            leftText = leftTab,
            rightText = rightTab,
            selectedIndex = selectedTabIndex,
            onSelect = onTabChange,
            scale = scale
        )

        Spacer(Modifier.height(tabsBottomSpace))

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

    val rowH = 44.dp.scaled(scale)

    val outerRadius = 50.dp.scaled(scale)
    val outerShape = RoundedCornerShape(outerRadius)

    val innerRadius = 50.dp.scaled(scale)

    val unselectedRadius = 10.dp.scaled(scale)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(rowH)
            .clip(outerShape)
            .border(1.dp, colors.primary, outerShape)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(
                    if (selectedIndex == 0)
                        RoundedCornerShape(
                            topStart = innerRadius,
                            bottomStart = innerRadius,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp
                        )
                    else RoundedCornerShape(unselectedRadius)
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

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(
                    if (selectedIndex == 1)
                        RoundedCornerShape(
                            topEnd = innerRadius,
                            bottomEnd = innerRadius,
                            topStart = 0.dp,
                            bottomStart = 0.dp
                        )
                    else RoundedCornerShape(unselectedRadius)
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

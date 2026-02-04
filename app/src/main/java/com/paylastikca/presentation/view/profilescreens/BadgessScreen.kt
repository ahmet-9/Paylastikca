package com.paylastikca.presentation.view.profilescreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.ScrollPage
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive

@Composable
fun BadgessScreen(
    navController: NavController
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()

    PageScaffold(
        containerColor = colors.background,
        currentRoute = null,
        navController = null,
        applySafeInsets = true,
        removeTopInset = true
    ) { innerPadding ->

        ScrollPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BackButton(
                    title = "Rozetler",
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    onBackClick = { navController.popBackStack() }
                )

                BadgesHelpCard()

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun BadgesHelpCard() {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "İlk Adım",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_one,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = true,
            isCompleted = true
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Yardımcı",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_two,
            badgeIconSize = 125,
            badgeIconOffsetY = 33,
            isActive = true,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Güvenilir",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_three,
            badgeIconSize = 125,
            badgeIconOffsetY = 33,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))

        CardBadgesState(
            title = "Kahraman",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_four,
            badgeIconSize = 170,
            badgeIconOffsetY = 50,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Lider",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_five,
            badgeIconSize = 125,
            badgeIconOffsetY = 33,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Usta",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_six,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Efsane",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_seven,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Elçi",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_eight,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Önder",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_nine,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        CardBadgesState(
            title = "Altın",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_ten,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))

        CardBadgesState(
            title = "Ruhlu",
            subtitleText = "“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_eleven,
            badgeIconSize = 225,
            badgeIconOffsetY = 79,
            isActive = false,
            isCompleted = false
        )
    }
}


@Composable
private fun CardBadgesState(
    title: String,
    subtitleText: String = "",
    rewardText: String,
    progressText: String,
    isActive: Boolean,
    isCompleted: Boolean,
    badgeIconRes: Int? = null,
    badgeIconSize: Int = 92,
    badgeIconOffsetY: Int = 0
) {
    val colors = MaterialTheme.colorScheme

    val orange = colors.primary
    val lightGray = colors.outlineVariant
    val disabledText = colors.onSurfaceVariant

    val titleColor = if (isActive) colors.onSurface else disabledText
    val rewardColor = if (isActive) colors.onSurface else disabledText

    val barColor = if (isActive) orange else lightGray
    val alphaValue = if (isActive) 1f else 0.5f

    val iconDp = badgeIconSize.dp
    val iconRadius = iconDp / 2

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
            .alpha(alphaValue),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(colors.surface)
                .padding(
                    top = if (badgeIconRes != null) iconRadius + 12.dp else 18.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 18.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitleText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = rewardColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = rewardText,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = rewardColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(32.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(12.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(lightGray)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(if (isCompleted) 1f else 0.4f)
                            .height(12.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(barColor)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                if (isActive && isCompleted) {
                    Image(
                        painter = painterResource(id = R.drawable.taskicontik),
                        contentDescription = "Tamamlandı",
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = progressText,
                fontSize = 13.sp,
                color = titleColor
            )
        }

        if (badgeIconRes != null) {
            Image(
                painter = painterResource(id = badgeIconRes),
                contentDescription = "Rozet",
                modifier = Modifier
                    .size(iconDp)
                    .offset(y = -iconRadius + badgeIconOffsetY.dp)
            )
        }
    }
}

@Composable
fun BadgeDotsDividerVertical(
    modifier: Modifier = Modifier,
    dotSize: Dp = 9.dp,
    dotColor: Color = MaterialTheme.colorScheme.outline,
    dotCount: Int = 4,
    spacing: Dp = 30.dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(dotCount) { index ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(dotColor)
            )
            if (index != dotCount - 1) {
                Spacer(modifier = Modifier.height(spacing))
            }
        }
    }
}

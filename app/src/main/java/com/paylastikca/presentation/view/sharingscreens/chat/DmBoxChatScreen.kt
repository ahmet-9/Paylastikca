package com.paylastikca.presentation.view.sharingscreens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.layout.scaled
import com.paylastikca.ui.UiDimens
import kotlinx.coroutines.launch

@Immutable
private data class ChatMessageUi(
    val id: String,
    val text: String,
    val time: String,
    val isMine: Boolean
)

@Composable
fun DmBoxChatScreen(
    navController: NavController,
    chatId: String
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val s = adaptive.scale

    var input by remember { mutableStateOf("") }

    val messages = remember(chatId) {
        mutableStateListOf(
            ChatMessageUi("1", "lorem ipsum dolor", "12:38", false),
            ChatMessageUi("2", "lorem ipsum dolor", "12:38", true),
            ChatMessageUi("3", "lorem ipsum dolor", "12:38", false),
            ChatMessageUi("4", "lorem ipsum dolor lorem ipsum dolor lorem ipsum dolor.", "12:38", true),
            ChatMessageUi("5", "lorem ipsum dolor", "12:38", false),
            ChatMessageUi("6", "lorem ipsum dolor", "12:38", true)
        )
    }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = colors.background,

        contentWindowInsets = WindowInsets(0),

        bottomBar = {
            ChatInputBar(
                value = input,
                onValueChange = { input = it },
                scale = s,
                onSendClick = {
                    val trimmed = input.trim()
                    if (trimmed.isEmpty()) return@ChatInputBar

                    messages.add(
                        ChatMessageUi(
                            id = "local_${System.currentTimeMillis()}",
                            text = trimmed,
                            time = "--:--",
                            isMine = true
                        )
                    )
                    input = ""

                    scope.launch { listState.animateScrollToItem(0) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars.union(WindowInsets.ime))
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            ChatTopBar(
                name = "Ahmet Badem",
                scale = s,
                onBack = { navController.popBackStack() },
                onReport = {   navController.navigate("ReportUserScreen/$chatId") }
            )

            Spacer(Modifier.height((1.dp).scaled(s)))

            Text(
                text = "Sohbet 23 saat 59 dakika sonra\nsona eriyor",
                color = colors.onSurfaceVariant,
                fontSize = (14.sp).scaled(s),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = (2.dp).scaled(s)),
                lineHeight = (18.sp).scaled(s),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height((10.dp).scaled(s)))

            LazyColumn(
                state = listState,
                reverseLayout = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy((10.dp).scaled(s)),
                contentPadding = PaddingValues(
                    start = adaptive.pageHorizontalPadding,
                    end = adaptive.pageHorizontalPadding,
                    top = (10.dp).scaled(s),
                    bottom = (10.dp).scaled(s)
                ),

            ) {
                items(messages.asReversed(), key = { it.id }) { msg ->
                    ChatBubble(
                        text = msg.text,
                        time = msg.time,
                        isMine = msg.isMine,
                        scale = s
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatTopBar(
    name: String,
    scale: Float,
    onBack: () -> Unit,
    onReport: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val barPadV = (10.dp).scaled(scale)
    val avatarSize = (54.dp).scaled(scale)
    val cfg = androidx.compose.ui.platform.LocalConfiguration.current
    val screenWidth = cfg.screenWidthDp

    val nameBaseSize = when {
        screenWidth <= 360 -> 18.sp
        screenWidth <= 400 -> 20.sp
        else -> 24.sp
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = UiDimens.l.scaled(scale), vertical = barPadV),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack, modifier = Modifier.size((40.dp).scaled(scale))) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Geri",
                tint = colors.onSurface,
                modifier = Modifier.size((24.dp).scaled(scale))
            )
        }

        Spacer(Modifier.width((8.dp).scaled(scale)))

        Box(
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape)
                .background(colors.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Avatar",
                tint = colors.onSurfaceVariant,
                modifier = Modifier.size((18.dp).scaled(scale))
            )
        }

        Spacer(Modifier.width((10.dp).scaled(scale)))

        Text(
            text = name,
            color = colors.onSurface,
            fontSize = nameBaseSize.scaled(scale),
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .weight(1f)
                .padding(end = UiDimens.s.scaled(scale)),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            softWrap = false
        )



        TextButton(onClick = {onReport()}) {
            Text(
                text = "Şikayet et",
                color = colors.onSurfaceVariant,
                fontSize = (14.sp).scaled(scale),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ChatBubble(
    text: String,
    time: String,
    isMine: Boolean,
    scale: Float
) {
    val colors = MaterialTheme.colorScheme
    val bubbleShape = RoundedCornerShape(18.dp)
    val bubbleMaxW = (280.dp).scaled(scale)

    val bg = if (isMine) colors.primary else colors.surfaceVariant
    val fg = if (isMine) colors.onPrimary else colors.onSurface

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMine) Arrangement.End else Arrangement.Start
    ) {
        Column(
            horizontalAlignment = if (isMine) Alignment.End else Alignment.Start,
            modifier = Modifier.widthIn(max = bubbleMaxW)
        ) {
            Box(
                modifier = Modifier
                    .clip(bubbleShape)
                    .background(bg)
                    .padding(
                        horizontal = (16.dp).scaled(scale),
                        vertical = (10.dp).scaled(scale)
                    )
            ) {
                Text(
                    text = text,
                    color = fg,
                    fontSize = (15.sp).scaled(scale),
                    fontWeight = FontWeight.Medium,
                    lineHeight = (18.sp).scaled(scale)
                )
            }

            Spacer(Modifier.height((4.dp).scaled(scale)))

            Text(
                text = time,
                color = colors.onSurfaceVariant,
                fontSize = (11.sp).scaled(scale)
            )
        }
    }
}


@Composable
private fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    scale: Float,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
    onPickFromCamera: () -> Unit = {},
    onPickFromGallery: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    val h = (56.dp).scaled(scale)
    val sendSize = (53.dp).scaled(scale)

    var mediaMenuOpen by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(
                horizontal = UiDimens.l.scaled(scale),
                vertical = UiDimens.m.scaled(scale)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .height(h),
            placeholder = {
                Text(
                    text = "Mesajınız...",
                    fontSize = (14.sp).scaled(scale)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                Box {
                    IconButton(
                        onClick = { mediaMenuOpen = true },
                        modifier = Modifier.size((40.dp).scaled(scale))
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.PhotoCamera,
                            contentDescription = "Medya ekle",
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size((28.dp).scaled(scale))
                        )
                    }

                    DropdownMenu(
                        expanded = mediaMenuOpen,
                        onDismissRequest = { mediaMenuOpen = false },
                        shape = RoundedCornerShape(20.dp),
                        tonalElevation = 8.dp,
                        shadowElevation = 10.dp,
                        modifier = Modifier.width((200.dp).scaled(scale))
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Kamera",
                                    fontSize = (19.sp).scaled(scale)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.PhotoCamera,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                mediaMenuOpen = false
                                onPickFromCamera()
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Galeri",
                                    fontSize = (19.sp).scaled(scale)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.PhotoLibrary,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                mediaMenuOpen = false
                                onPickFromGallery()
                            }
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surface,
                focusedIndicatorColor = colors.outlineVariant,
                unfocusedIndicatorColor = colors.outlineVariant,
                cursorColor = colors.primary
            )
        )

        Spacer(Modifier.width(UiDimens.s.scaled(scale)))

        Box(
            modifier = Modifier
                .size(sendSize)
                .clip(CircleShape)
                .background(colors.primary),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = onSendClick, modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Rounded.Send,
                    contentDescription = "Gönder",
                    tint = colors.onPrimary,
                    modifier = Modifier.size((31.dp).scaled(scale))
                )
            }
        }
    }
}


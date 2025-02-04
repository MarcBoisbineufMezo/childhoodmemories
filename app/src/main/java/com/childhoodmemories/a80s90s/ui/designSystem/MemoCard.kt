package com.childhoodmemories.a80s90s.ui.designSystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.childhoodmemories.a80s90s.data.memory1
import com.childhoodmemories.a80s90s.model.Memory
import com.childhoodmemories.a80s90s.model.User
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.orange
import com.childhoodmemories.a80s90s.ui.theme.violet

@Composable
fun MemoCard(
    modifier: Modifier = Modifier,
    memory: Memory,
    isLiked: Boolean,
    onLikeClicked: (Memory) -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            contentColor = violet,
            containerColor = orange.copy(alpha = 0.3f),
        ),
    ) {
        Box {
            Column {
                HeaderCard(user = memory.user)
                BodyCard(memory = memory, isLiked = isLiked) {
                    onLikeClicked(it)
                }
            }

        }
    }
}

@Composable
private fun BodyCard(
    memory: Memory,
    isLiked: Boolean,
    onLikeClicked: (Memory) -> Unit,
) {
    val contrast = 2f
    val brightness = -180f
    val colorMatrix = ColorMatrix(
        floatArrayOf(contrast, 0f, 0f, 0f, brightness, 0f, contrast, 0f, 0f, brightness, 0f, 0f, contrast, 0f, brightness, 0f, 0f, 0f, 1f, 0f)
    ).apply {}

    Column {
        Box {
            Column {

                if (memory.image.contains("content://").not()) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimens.Padding.medium)
                            .clip(RoundedCornerShape(10.dp)),
                        model = memory.image,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "80s90s",
                        colorFilter = ColorFilter.colorMatrix(colorMatrix),
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .padding(horizontal = Dimens.Padding.medium)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxSize(),
                        model = memory.image,
                        contentDescription = "80s90s",
                        contentScale = ContentScale.FillWidth,
                    )
                }
                Spacer(modifier = Modifier.size(Dimens.Padding.medium))
            }
            IconButton(
                modifier = Modifier
                    .padding(horizontal = Dimens.Padding.large)
                    .align(Alignment.BottomEnd),
                icon = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                selected = isLiked,
            ) {
                onLikeClicked(memory)
            }

        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
            text = memory.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium, vertical = Dimens.Padding.small),
            text = if (memory.description.length > 150) memory.description.substring(0, 150)
            else memory.description,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun HeaderCard(user: User) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .padding(Dimens.Padding.medium)
                .clip(RoundedCornerShape(40.dp)),
            model = user.avatar,
            contentScale = ContentScale.FillWidth,
            contentDescription = "80s90s",
        )
        Text(
            text = user.firstname + " " + user.lastname,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun MemoCardPreview() {
    MemoCard(memory = memory1, isLiked = true) {}
}

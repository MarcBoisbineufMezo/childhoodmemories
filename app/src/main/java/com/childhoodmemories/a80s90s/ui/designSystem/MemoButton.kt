package com.childhoodmemories.a80s90s.ui.designSystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.Memories80s90sTheme
import com.childhoodmemories.a80s90s.ui.theme.violet

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = violet,
            contentColor = Color.White
        ),
        onClick = {
            onClick()
        },
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .size(ButtonDefaults.IconSpacing),
            )
        }
        text?.let {
            Text(
                modifier = Modifier
                    .padding(Dimens.Padding.small),
                text = it,
            )
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(
            color = violet,
            width = 1.dp
        ),
        onClick = onClick,
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .size(ButtonDefaults.IconSpacing),
            )
        }
        text?.let {
            Text(
                modifier = Modifier
                    .padding(Dimens.Padding.small),
                text = it,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = Color.White,
                )
                .size(40.dp)
                .clickable(onClick = onClick),
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(30.dp)
                    .clickable(onClick = onClick)
                    .align(Alignment.Center),
                colorFilter = ColorFilter.tint(if (selected) violet else Color.LightGray),
                imageVector = icon,
                contentDescription = "Image Button",
            )

        }

    }
}

@Preview
@Composable
fun PreviewIconButton() {
    Memories80s90sTheme {
        Row {
            IconButton(
                icon = Icons.Filled.FavoriteBorder,
                selected = true,
            ) { }
            IconButton(
                icon = Icons.Filled.FavoriteBorder,
                selected = false,
            ) { }

        }
    }
}

@Preview
@Composable
fun PreviewPrimaryButton() {
    Memories80s90sTheme {
        PrimaryButton(
            text = "Ajouter",
        ) { }
    }
}

@Preview
@Composable
fun PreviewSecondaryButton() {
    Memories80s90sTheme {
        SecondaryButton(
            text = "Ajouter",
        ) { }
    }
}
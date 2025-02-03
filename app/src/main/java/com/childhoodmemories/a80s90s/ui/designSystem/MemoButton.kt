package com.childhoodmemories.a80s90s.ui.designSystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.Memories80s90sTheme
import com.childhoodmemories.a80s90s.ui.theme.blue
import com.childhoodmemories.a80s90s.ui.theme.orange
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
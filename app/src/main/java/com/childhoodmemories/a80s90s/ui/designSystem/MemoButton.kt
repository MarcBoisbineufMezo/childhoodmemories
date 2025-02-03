package com.childhoodmemories.a80s90s.ui.designSystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.childhoodmemories.a80s90s.ui.theme.Memories80s90sTheme
import com.childhoodmemories.a80s90s.ui.theme.orange

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
            containerColor = orange,
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
//                modifier = Modifier
//                    .padding(Dimens.Padding.small),
                text = it,
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
//    Memories80s90sTheme {
////        PrimaryButton(
////            text = "Ajouter",
////            icon = Icons.Filled.Add,
////        ) {}
//    }
}

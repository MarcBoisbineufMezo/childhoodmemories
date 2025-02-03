package com.childhoodmemories.a80s90s.ui.designSystem

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.ui.theme.Dimens

@Composable
fun MemoTitle(
    modifier: Modifier = Modifier,
    text: String,
) {

    Text(
        modifier = modifier
            .padding(horizontal = Dimens.Padding.medium),
        textAlign = TextAlign.Center,
        text = text,
        color = Color.White,
        style = TextStyle(
            fontSize = Dimens.TextSize.title,
            fontStyle = FontStyle.Italic
        ),
    )
}

@Preview
@Composable
fun MemoTitlePreview() {
    MemoTitle(text = stringResource(R.string.welcome))
}
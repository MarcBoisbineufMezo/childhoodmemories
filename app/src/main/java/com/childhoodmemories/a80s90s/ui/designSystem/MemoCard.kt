package com.childhoodmemories.a80s90s.ui.designSystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.violet

@Composable
fun MemoCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            contentColor = violet,
        ),
    ) {
        Box {
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = painterResource(R.drawable.card_bg),
//                contentDescription = "80s90s",
//                contentScale = ContentScale.FillWidth,
//            )
            Column {
                HeaderCard()
                BodyCard()
            }

        }
    }
}

@Composable
private fun BodyCard() {
    val contrast = 2f
    val brightness = -180f
    val colorMatrix = ColorMatrix(
        floatArrayOf(
            contrast, 0f, 0f, 0f, brightness,
            0f, contrast, 0f, 0f, brightness,
            0f, 0f, contrast, 0f, brightness,
            0f, 0f, 0f, 1f, 0f
        )
    ).apply {
    }
    Column {
        Box {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.Padding.medium)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(R.drawable.avatar),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "80s90s",
                    colorFilter = ColorFilter.colorMatrix(colorMatrix),
                )
                Spacer(modifier = Modifier.size(Dimens.Padding.medium))
            }
            IconButton(
                modifier = Modifier
                    .padding(horizontal = Dimens.Padding.large)
                    .align(Alignment.BottomEnd),
                icon = Icons.Filled.FavoriteBorder,
                selected = true,
            ) { }

        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
            text = "Kevin MacAlister",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium, vertical = Dimens.Padding.small),
            text = "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.\n".substring(
                0,
                150
            ),
            style = MaterialTheme.typography.bodySmall,
        )

    }

}

@Composable
private fun HeaderCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .padding(Dimens.Padding.medium)
                .clip(RoundedCornerShape(40.dp)),
            painter = painterResource(R.drawable.avatar),
            contentScale = ContentScale.FillWidth,
            contentDescription = "80s90s",
        )
        Text(
            text = "Kevin MacAlister",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun MemoCardPreview() {
    MemoCard()
}

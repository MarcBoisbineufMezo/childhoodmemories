package com.childhoodmemories.a80s90s.ui.features.getStarted

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.ui.designSystem.MemoTitle
import com.childhoodmemories.a80s90s.ui.designSystem.PrimaryButton
import com.childhoodmemories.a80s90s.ui.designSystem.SecondaryButton
import com.childhoodmemories.a80s90s.ui.theme.Dimens

@Composable
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    viewModel: GetStartedViewModel = GetStartedViewModel(),
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        viewModel.init()
    }


    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(Dimens.Padding.medium))

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Padding.medium)
                    .clip(RoundedCornerShape(180.dp)),
                painter = painterResource(R.drawable.home_logo),
                contentScale = ContentScale.FillWidth,
                contentDescription = "80s90s",
            )
            Spacer(modifier = Modifier.height(Dimens.Padding.medium))
            MemoTitle(
                text = stringResource(R.string.welcome),
            )
            Spacer(modifier = Modifier.weight(1f))
            SecondaryButton(
                text = stringResource(R.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Padding.medium),
            ) { }
            Spacer(modifier = Modifier.height(Dimens.Padding.medium))
            PrimaryButton(
                text = stringResource(R.string.register),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Padding.medium),
            ) { }
            Spacer(modifier = Modifier.height(Dimens.Padding.medium))

        }
    }

}

@Preview
@Composable
fun PreviewGetStartedScreen() {
    GetStartedScreen(
        navController = rememberNavController()
    )
}
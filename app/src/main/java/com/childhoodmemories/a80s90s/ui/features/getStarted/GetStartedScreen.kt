package com.childhoodmemories.a80s90s.ui.features.getStarted

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.Screen
import com.childhoodmemories.a80s90s.ui.designSystem.MemoTitle
import com.childhoodmemories.a80s90s.ui.designSystem.PrimaryButton
import com.childhoodmemories.a80s90s.ui.designSystem.SecondaryButton
import com.childhoodmemories.a80s90s.ui.features.getStarted.GetStartedViewModel.ScreenState
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.violet

@Composable
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    viewModel: GetStartedViewModel = viewModel(),
    navController: NavHostController
) {

    val state = viewModel.getState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    LaunchedEffect(state.sideEffect) {
        if (state.sideEffect != null) {
            when (state.sideEffect) {
                GetStartedViewModel.SideEffect.NavigateToHome -> {
                    navController.navigate(Screen.Home.route)
                }
            }
        }
    }

    Box(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.card_bg),
            contentScale = ContentScale.FillHeight,
            contentDescription = "80s90s",
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
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
            if (state.screenState == ScreenState.ChooseSignInOrSignUp) {
                MemoTitle(
                    text = stringResource(R.string.welcome),
                )
            }

            when (state.screenState) {
                ScreenState.Loading -> {
                    Box(
                        modifier = modifier
                            .padding(Dimens.Padding.large)
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = violet,
                            strokeWidth = 4.dp
                        )
                    }
                }

                ScreenState.ChooseSignInOrSignUp -> {
                    Column {
                        Spacer(modifier = Modifier.height(Dimens.Padding.large))
                        SignInOrUpView(
                            onSignInClick = {
                                viewModel.onSignInClick()
                            },
                            onSignUpClick = {
                                viewModel.onSignUpClick()
                            }
                        )
                    }
                }

                ScreenState.SignIn, ScreenState.EmailError, ScreenState.PasswordError ->
                    Column {
                        SignInView(
                            email = state.email,
                            password = state.password,
                            screenState = state.screenState,
                            onEmailChange = {
                                viewModel.onEmailChange(it)
                            },
                            onPasswordChange = {
                                viewModel.onPasswordChange(it)
                            },

                            onSignInClick = {
                                viewModel.signIn()
                            },
                            onCancelClicked = {
                                viewModel.reset()
                            }
                        )
                    }

                ScreenState.SignUp ->
                    SignUpView(
                        email = state.email,
                        password = state.password,
                        onEmailChange = {
                            viewModel.onEmailChange(it)
                        },
                        onPasswordChange = {
                            viewModel.onPasswordChange(it)
                        },
                        onRegisterClick = {
                            // TODO manage register with error
                            navController.navigate(Screen.Home.route)
                        },
                        onCancelClicked = {
                            viewModel.reset()
                        }
                    )
            }
        }
    }

}

@Composable
private fun SignUpView(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onCancelClicked: () -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))
        TextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.firstname)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        )
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))
        TextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.lastname)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        )
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))
        TextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        )
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))

        TextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.password)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        )
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))

        SecondaryButton(
            text = stringResource(R.string.login),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        ) {
            onRegisterClick()
        }

        Spacer(modifier = Modifier.height(Dimens.Padding.medium))
        PrimaryButton(
            text = stringResource(R.string.cancel),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        ) {
            onCancelClicked()
        }
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))

    }
}

@Composable
private fun SignInView(
    email: String,
    password: String,
    screenState: ScreenState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onCancelClicked: () -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
            isError = screenState == ScreenState.EmailError,
            value = email,
            onValueChange = { onEmailChange(it) },
            label = { Text(stringResource(R.string.email)) },
        )
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))

        TextField(
            value = password,
            isError = screenState == ScreenState.PasswordError,
            onValueChange = { onPasswordChange(it) },
            label = { Text(stringResource(R.string.password)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
        Spacer(modifier = Modifier.height(Dimens.Padding.large))

        SecondaryButton(
            text = stringResource(R.string.login),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        ) {
            onSignInClick()
        }

        Spacer(modifier = Modifier.height(Dimens.Padding.medium))
        PrimaryButton(
            text = stringResource(R.string.cancel),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Padding.medium),
        ) {
            onCancelClicked()
        }
        Spacer(modifier = Modifier.height(Dimens.Padding.medium))

    }
}

@Composable
private fun SignInOrUpView(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    SecondaryButton(
        text = stringResource(R.string.login),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Padding.medium),
    ) {
        onSignInClick()
    }
    Spacer(modifier = Modifier.height(Dimens.Padding.medium))
    PrimaryButton(
        text = stringResource(R.string.register),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Padding.medium),
    ) {
        onSignUpClick()
    }
    Spacer(modifier = Modifier.height(Dimens.Padding.medium))
}

@Preview
@Composable
fun PreviewGetStartedScreen() {
    GetStartedScreen(
        navController = rememberNavController()
    )
}
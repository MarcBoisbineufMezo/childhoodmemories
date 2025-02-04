package com.childhoodmemories.a80s90s.ui.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.Screen
import com.childhoodmemories.a80s90s.model.Memory
import com.childhoodmemories.a80s90s.ui.designSystem.IconButton
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.violet
import com.childhoodmemories.a80s90s.ui.theme.yellow

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel(),
) {
    val state = viewModel.getState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    LaunchedEffect(state.sideEffect) {
        if (state.sideEffect != null) {
            when (state.sideEffect) {
                ProfileViewModel.SideEffect.NavigateToGetStarted -> {
                    navController.navigate(Screen.GetStarted.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderProfile(
                onBackClick = { navController.popBackStack() },
                onLogOutClicked = { viewModel.logOut() },
            )
            AsyncImage(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.Padding.medium)
                    .clip(RoundedCornerShape(100.dp)),
                model = state.user?.avatar.orEmpty(),
                contentScale = ContentScale.FillWidth,
                contentDescription = "avatar",
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.name,
                style = MaterialTheme.typography.titleLarge,
                color = violet,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(Dimens.Padding.medium))
            DataProfile(
                modifier = Modifier
                    .padding(Dimens.Padding.medium)
                    .fillMaxWidth(),
                memories = state.memories,
                likeCounter = state.likeCounter,
            )
            MemoriesProfile(
                modifier = Modifier
                    .padding(Dimens.Padding.medium)
                    .fillMaxWidth(),
                memories = state.memories,
            )
        }
    }
}

@Composable
fun MemoriesProfile(
    modifier: Modifier,
    memories: List<Memory>,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = Dimens.Padding.medium),
        columns = GridCells.Adaptive(minSize = 103.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(memories) { memory ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = memory.image,
                    contentDescription = "80s90s",
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }

}

@Composable
fun DataProfile(modifier: Modifier, memories: List<Memory>, likeCounter: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        DataProfileView(
            title = stringResource(id = R.string.memories),
            value = memories.size.toString()
        )
        DataProfileView(stringResource(id = R.string.likes), likeCounter.toString())
//        DataProfileView(stringResource(id = R.string.memories), memories.size.toString())
    }
}

@Composable
private fun DataProfileView(title: String, value: String) {
    Column(
        modifier = Modifier
            .padding(Dimens.Padding.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.background(Color.White),
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = yellow,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
private fun HeaderProfile(
    onBackClick: () -> Unit,
    onLogOutClicked: () -> Unit,
) {
    Row {
        IconButton(
            modifier = Modifier.padding(top = Dimens.Padding.large, start = Dimens.Padding.medium),
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            selected = true,
        ) {
            onBackClick()
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.padding(top = Dimens.Padding.large, end = Dimens.Padding.medium),
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            selected = true,
        ) {
            onLogOutClicked()
        }

    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
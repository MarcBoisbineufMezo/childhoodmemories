package com.childhoodmemories.a80s90s.ui.features.profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.ui.designSystem.IconButton
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.violet
import com.childhoodmemories.a80s90s.ui.theme.yellow

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = ProfileViewModel(),
) {
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
                onLogOutClicked = { TODO("LogOut") },
            )
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.Padding.medium)
                    .clip(RoundedCornerShape(100.dp)),
                painter = painterResource(R.drawable.avatar),
                contentScale = ContentScale.FillWidth,
                contentDescription = "avatar",
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Kevin MacAlister",
                style = MaterialTheme.typography.titleLarge,
                color = violet,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(Dimens.Padding.medium))
            DataProfile(
                modifier = Modifier
                    .padding(Dimens.Padding.medium)
                    .fillMaxWidth()
            )
            MemoriesProfile(
                modifier = Modifier
                    .padding(Dimens.Padding.medium)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun MemoriesProfile(modifier: Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = Dimens.Padding.medium),
        columns = GridCells.Adaptive(minSize = 103.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(10) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = "80s90s",
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }

}

@Composable
fun DataProfile(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        DataProfileView()
        DataProfileView()
        DataProfileView()
    }
}

@Composable
private fun DataProfileView() {
    Column(
        modifier = Modifier
            .padding(Dimens.Padding.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.memories),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.background(Color.White),
            text = "100",
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
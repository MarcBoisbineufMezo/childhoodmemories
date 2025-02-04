package com.childhoodmemories.a80s90s.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.Screen
import com.childhoodmemories.a80s90s.ui.StoreData
import com.childhoodmemories.a80s90s.ui.designSystem.IconButton
import com.childhoodmemories.a80s90s.ui.designSystem.MemoCard
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.orange
import com.childhoodmemories.a80s90s.ui.theme.yellow

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(),
) {
    val dataStore = remember { StoreData }
    
    val state = viewModel.getState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderHome(state.user?.firstname.orEmpty()) {
                navController.navigate(Screen.Profile.route)
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Dimens.Padding.medium),
                contentPadding = PaddingValues(Dimens.Padding.medium),
            ) {
                items(state.memories) { memory ->
                    MemoCard(
                        memory = memory,
                        isLiked = viewModel.isLiked(memory),
                        dataStore = dataStore
                    ) {
                        viewModel.onLikeClicked(memory)
                    }
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Dimens.Padding.small),
            containerColor = yellow,
            onClick = {
                navController.navigate(Screen.AddMemory.route)
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}

@Composable
private fun HeaderHome(
    firstName: String,
    onProfileClick: () -> Unit,
) {
    Row {
        Column(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.padding(Dimens.Padding.medium))
            Text(
                text = stringResource(id = R.string.home_hello, firstName),
                style = MaterialTheme.typography.titleMedium,
                color = yellow,
                modifier = Modifier
                    .padding(horizontal = Dimens.Padding.medium)
            )
            Text(
                text = stringResource(id = R.string.home_title),
                style = MaterialTheme.typography.titleLarge,
                color = orange,
                modifier = Modifier
                    .padding(horizontal = Dimens.Padding.medium)
            )
            // TODO add filters categories?
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier
                .padding(top = Dimens.Padding.large, end = Dimens.Padding.medium),
            icon = Icons.Filled.AccountCircle,
            selected = true,
        ) {
            onProfileClick()
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
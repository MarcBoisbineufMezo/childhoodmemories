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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.ui.designSystem.IconButton
import com.childhoodmemories.a80s90s.ui.designSystem.MemoCard
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.orange
import com.childhoodmemories.a80s90s.ui.theme.yellow

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = HomeViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderHome("User")
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Dimens.Padding.medium),
                contentPadding = PaddingValues(Dimens.Padding.medium),
            ) {
                item {
                    MemoCard()
                }
                item {
                    MemoCard()
                }
                item {
                    MemoCard()
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Dimens.Padding.small),
            containerColor = yellow,
            onClick = {
                TODO("navigate to gallery/photo")
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }

    }
}

@Composable
private fun HeaderHome(
    firstName: String
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
            // TODO add filters
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier
                .padding(top = Dimens.Padding.large, end = Dimens.Padding.medium),
            icon = Icons.Filled.AccountCircle,
            selected = true,
        ) {
            // TODO navigate to profile
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
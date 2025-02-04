package com.childhoodmemories.a80s90s.ui.features.addMemory

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.childhoodmemories.a80s90s.R
import com.childhoodmemories.a80s90s.ui.designSystem.GalleryLauncher
import com.childhoodmemories.a80s90s.ui.designSystem.IconButton
import com.childhoodmemories.a80s90s.ui.designSystem.MemoTextField
import com.childhoodmemories.a80s90s.ui.designSystem.PrimaryButton
import com.childhoodmemories.a80s90s.ui.theme.Dimens
import com.childhoodmemories.a80s90s.ui.theme.orange

@Composable
fun AddMemoryScreen(
    navController: NavController,
    viewModel: AddMemoryViewModel = viewModel(),
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showGallery by remember { mutableStateOf(false) }

    val state = viewModel.getState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    LaunchedEffect(state.imageId != null) {
        showGallery = true
    }

    LaunchedEffect(state.sideEffect) {
        if (state.sideEffect != null) {
            when (state.sideEffect) {
                AddMemoryViewModel.SideEffect.NavigateToHome -> navController.popBackStack()
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
            Header(
                onDismissClick = { navController.popBackStack() },
            )
            AsyncImage(
                modifier = Modifier
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.Padding.medium),
                model = selectedImageUri,
                contentScale = ContentScale.Fit,
                contentDescription = "avatar",
            )
            MemoTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Padding.medium),
                title = stringResource(id = R.string.title_add_memory),
                value = state.title,
                onValueChange = { viewModel.onTitleChanged(it) },
                maxLines = 1,
            )
            MemoTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = Dimens.Padding.medium),
                title = stringResource(id = R.string.description_add_memory),
                value = state.description,
                onValueChange = { viewModel.onDescriptionChanged(it) },
                maxLines = 5,
            )
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                text = stringResource(R.string.validate),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Padding.medium),
            ) {
                viewModel.onValidateMemory()
            }
        }

        if (showGallery) {
            GalleryLauncher { uri ->
                selectedImageUri = uri
                showGallery = false
            }
        }
        selectedImageUri?.let { uri ->
            viewModel.saveImage(uri)
        }

    }
}

@Composable
private fun Header(
    onDismissClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.padding(top = Dimens.Padding.large, start = Dimens.Padding.medium),
            icon = Icons.Filled.Close,
            selected = true,
        ) {
            onDismissClick()
        }
        Text(
            text = stringResource(id = R.string.add_title),
            style = MaterialTheme.typography.titleLarge,
            color = orange,
            modifier = Modifier
                .padding(top = Dimens.Padding.large, start = Dimens.Padding.medium)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}


@Preview
@Composable
fun AddMemoryScreenPreview() {
    AddMemoryScreen(navController = rememberNavController())
}
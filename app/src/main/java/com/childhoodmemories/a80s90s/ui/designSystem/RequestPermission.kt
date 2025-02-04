package com.childhoodmemories.a80s90s.ui.designSystem

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.childhoodmemories.a80s90s.ui.StoreData
import kotlinx.coroutines.launch

@Composable
fun GalleryLauncher(
    id: String,
    dataStore: StoreData,
    onImagePicked: (Uri?) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            scope.launch {
                dataStore.storeImage(context, uri.toString(), id)
            }
        }
        onImagePicked(uri)
    }

    LaunchedEffect(Unit) {
        launcher.launch("image/*")
    }
}

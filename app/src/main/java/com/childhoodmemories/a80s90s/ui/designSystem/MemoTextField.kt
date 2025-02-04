package com.childhoodmemories.a80s90s.ui.designSystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.childhoodmemories.a80s90s.ui.theme.violet

@Composable
fun MemoTextField(
    modifier: Modifier = Modifier,
    title: String,
    placeholder: String? = null,
    value: String,
    isSecure: Boolean = false,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
    isError: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = violet,
            focusedLabelColor = violet,
            focusedBorderColor = violet,
            unfocusedBorderColor = violet,
            unfocusedLabelColor = violet,
            errorTextColor = Color.Red,
            errorBorderColor = Color.Red,
            errorLabelColor = Color.Red,
        ),
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(title) },
        maxLines = maxLines,
        visualTransformation = if (isSecure) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (isSecure) KeyboardType.Password else KeyboardType.Text),
        isError = isError,
    )
}


@Preview
@Composable
fun MemoTextFieldPreview() {
    MemoTextField(
        title = "title",
        value = "example",
        onValueChange = {},
        isError = true,
    )
}
package com.el_aouthmanie.nticapp.ui.compenents

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun PopupWithDropdownMenu(
    state: Boolean,
    options: List<String>,
    onSubmit: (String, String) -> Unit,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = state,
        onDismissRequest = onDismiss
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = { Text(option) },
                onClick = { onSubmit(option, "Description for $option") }
            )
        }
    }
}




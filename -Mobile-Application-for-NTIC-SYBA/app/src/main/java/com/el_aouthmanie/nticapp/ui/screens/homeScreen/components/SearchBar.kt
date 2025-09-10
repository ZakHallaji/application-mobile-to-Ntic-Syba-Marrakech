package com.el_aouthmanie.nticapp.ui.screens.homeScreen.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.el_aouthmanie.nticapp.R


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.el_aouthmanie.nticapp.ui.theme.normalOuterBorderReduce
import com.el_aouthmanie.nticapp.ui.theme.truWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onActiveChanged: (Boolean) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Options")
        },
        placeholder = { Text("Search announcements") },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(10.dp))
            .background(Color.White, RoundedCornerShape(normalOuterBorderReduce))
            .onFocusChanged { focusState ->
                onActiveChanged(focusState.isFocused)
            },
        shape = RoundedCornerShape(normalOuterBorderReduce),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = truWhite
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun hello() {
    SearchBar()
}
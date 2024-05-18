package com.rokn.djur

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WinView() {
    Text(text = "You Win")
}

@Composable
@Preview
fun WinPreview() {
    WinView()
}
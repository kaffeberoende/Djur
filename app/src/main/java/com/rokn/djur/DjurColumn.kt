package com.rokn.djur

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rokn.djur.ui.theme.DjurTheme

@Composable
fun DjurColumn(
    items: List<Djur>,
    gridClicked: (String) -> Unit) {

    Column(
        verticalArrangement = Arrangement.Bottom
    ) {
        items.map { djur ->
            DjurItem(typ = djur) {
                gridClicked(it)
            }
        }
    }
}

@Composable
@Preview
fun GridPreview() {
    DjurTheme {
        DjurColumn(
            items = mutableListOf(
                Djur.Hund(Color.Green),
                Djur.Katt(Color.Red),
                Djur.Hare(Color.Yellow),
                Djur.Mose(Color.Blue)
            ).shuffled()
        ) {  }
    }
}
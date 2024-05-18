package com.rokn.djur

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rokn.djur.ui.theme.DjurTheme

@Composable
fun DjurItem(
    typ: Djur,
    onClick: (String) -> Unit) {
    val imageModifier = Modifier
        .size(width = 66.dp, height = 80.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Transparent)
        .clickable {
            if(typ !is Djur.Empty) onClick(typ.id) }
    Image(
        painter = painterResource(id = typ.imageResource),
        contentDescription = "",
        colorFilter = ColorFilter.tint(typ.color),
        modifier = imageModifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GridItemPreview() {
    DjurTheme {
        DjurItem(Djur.Katt(Color.Red)) {}
    }
}
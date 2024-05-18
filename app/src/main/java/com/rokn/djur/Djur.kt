package com.rokn.djur

import androidx.compose.ui.graphics.Color
import java.util.UUID

sealed class Djur(val imageResource: Int, open val color: Color, val id: String = UUID.randomUUID().toString())  {
    data class Hund(override val color: Color): Djur(R.drawable.hund, color)
    data class Katt(override val color: Color): Djur(R.drawable.katt, color)
    data class Mose(override val color: Color): Djur(R.drawable.alg, color)
    data class Hare(override val color: Color): Djur(R.drawable.hare, color)
    data object Empty: Djur(R.drawable.ic_launcher_foreground, color = Color.Transparent)
}
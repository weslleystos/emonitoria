package com.github.weslleystos.emonitoria.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.github.weslleystos.emonitoria.R

val AppFontFamily = FontFamily(
    Font(R.font.nunito, weight = FontWeight.Normal),
    Font(R.font.nunito, weight = FontWeight.SemiBold),
    Font(R.font.nunito, weight = FontWeight.Bold),
    Font(R.font.nunito, weight = FontWeight.Black),
)

val Typography = Typography(
    h1 = Typography().h1.copy(fontFamily = AppFontFamily),
    h2 = Typography().h2.copy(fontFamily = AppFontFamily),
    h3 = Typography().h3.copy(fontFamily = AppFontFamily),
    h4 = Typography().h4.copy(fontFamily = AppFontFamily),
    h5 = Typography().h5.copy(fontFamily = AppFontFamily),
    h6 = Typography().h6.copy(fontFamily = AppFontFamily),
    subtitle1 = Typography().subtitle1.copy(fontFamily = AppFontFamily),
    subtitle2 = Typography().subtitle2.copy(fontFamily = AppFontFamily),
    body1 = Typography().body1.copy(fontFamily = AppFontFamily),
    body2 = Typography().body2.copy(fontFamily = AppFontFamily),
    button = Typography().button.copy(fontFamily = AppFontFamily),
    caption = Typography().caption.copy(fontFamily = AppFontFamily),
    overline = Typography().overline.copy(fontFamily = AppFontFamily)
)
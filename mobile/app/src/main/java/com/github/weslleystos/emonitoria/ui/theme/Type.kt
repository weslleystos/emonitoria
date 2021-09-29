package com.github.weslleystos.emonitoria.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.github.weslleystos.emonitoria.R


val appFontFamily = FontFamily(
    Font(R.font.nunito_regular, weight = FontWeight.Normal),
    Font(R.font.nunito_semibold, weight = FontWeight.SemiBold),
    Font(R.font.nunito_bold, weight = FontWeight.Bold),
    Font(R.font.nunito_black, weight = FontWeight.Black),
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = Typography().h1.copy(fontFamily = appFontFamily),
    h2 = Typography().h2.copy(fontFamily = appFontFamily),
    h3 = Typography().h3.copy(fontFamily = appFontFamily),
    h4 = Typography().h4.copy(fontFamily = appFontFamily),
    h5 = Typography().h5.copy(fontFamily = appFontFamily),
    h6 = Typography().h6.copy(fontFamily = appFontFamily),
    subtitle1 = Typography().subtitle1.copy(fontFamily = appFontFamily),
    subtitle2 = Typography().subtitle2.copy(fontFamily = appFontFamily),
    body1 = Typography().body1.copy(fontFamily = appFontFamily),
    body2 = Typography().body2.copy(fontFamily = appFontFamily),
    button = Typography().button.copy(fontFamily = appFontFamily),
    caption = Typography().caption.copy(fontFamily = appFontFamily),
    overline = Typography().overline.copy(fontFamily = appFontFamily)
)
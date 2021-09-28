package com.github.weslleystos.emonitoria.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.weslleystos.emonitoria.R
import com.github.weslleystos.emonitoria.shared.animations.PulseEffect
import com.github.weslleystos.emonitoria.ui.theme.LightBlue

@Preview
@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            PulseEffect(maxScale = 1.1f) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "App Logo Image",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h3,
                color = LightBlue,
                fontWeight = FontWeight.Black
            )
        }
    }
}
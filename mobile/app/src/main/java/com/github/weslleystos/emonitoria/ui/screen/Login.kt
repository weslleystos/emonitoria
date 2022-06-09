package com.github.weslleystos.emonitoria.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.weslleystos.emonitoria.R
import com.github.weslleystos.emonitoria.ui.theme.BackgroundLightGray
import com.github.weslleystos.emonitoria.ui.theme.EMonitoriaTheme
import com.github.weslleystos.emonitoria.ui.theme.editTextSpacerHeightLoginScreen

@Composable
fun Login() {
    var password by remember { mutableStateOf("") }
    var passwordHidden by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(stringResource(R.string.email)) },
            leadingIcon = {
                Icon(Icons.Rounded.Email, stringResource(R.string.ic_email_description))
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundLightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(editTextSpacerHeightLoginScreen))
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(stringResource(R.string.password)) },
            leadingIcon = {
                Icon(Icons.Rounded.Lock, stringResource(R.string.ic_lock_description))
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundLightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    EMonitoriaTheme {
        Login()
    }
}
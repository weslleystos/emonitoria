package com.github.weslleystos.emonitoria.shared.util

val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

data class ValidatorString(val data: String, val isValid: Boolean = true)

fun ValidatorString.isNotEmpty(action: () -> Unit = {}): ValidatorString {
    val assert = data.isNotEmpty()
    if (!assert) action()
    return ValidatorString(data, isValid && assert)
}

fun ValidatorString.isGreatThan(length: Int, action: () -> Unit = {}): ValidatorString {
    val assert = data.length > length
    if (!assert) action()
    return ValidatorString(data, isValid && assert)
}

fun ValidatorString.isLessThan(length: Int, action: () -> Unit = {}): ValidatorString {
    val assert = data.length < length
    if (!assert) action()
    return ValidatorString(data, isValid && assert)
}

fun ValidatorString.isEmail(action: () -> Unit = {}): ValidatorString {
    val assert = data.matches(emailPattern)
    if (!assert) action()
    return ValidatorString(data, isValid && assert)
}

fun ValidatorString.isValid(action: () -> Unit = {}): Boolean {
    if (isValid) action()
    return isValid
}





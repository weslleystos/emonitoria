package com.github.weslleystos.emonitoria.shared.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, HIDE_NOT_ALWAYS)
}
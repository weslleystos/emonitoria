package com.github.weslleystos.emonitoria.shared.util

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

inline fun Fragment.snackBar(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit = {}
) {
    snackBar(resources.getString(messageRes), length, f)
}

inline fun Fragment.snackBar(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(requireView(), message, length)
    snack.f()
    snack.show()
}


//inline fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
//    snack(resources.getString(messageRes), length, f)
//}
//
//inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
//    val snack = Snackbar.make(this, message, length)
//    snack.f()
//    snack.show()
//}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

fun Snackbar.onDismissed(
    counterIdlingResource: EspressoCounterIdlingResource? = null,
    action: (event: Int) -> Unit
) {
    addCallback(object : Snackbar.Callback() {
        override fun onShown(sb: Snackbar?) {
            counterIdlingResource?.increment()
        }

        override fun onDismissed(snackbar: Snackbar?, event: Int) {
            counterIdlingResource?.decrement()
            action(event)
        }
    })
}

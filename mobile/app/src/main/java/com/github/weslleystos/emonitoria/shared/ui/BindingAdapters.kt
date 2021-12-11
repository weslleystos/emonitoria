package com.github.weslleystos.emonitoria.shared.ui

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.github.weslleystos.emonitoria.R

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}


@Suppress("deprecation")
@BindingAdapter(value = ["first", "second", "primary"], requireAll = false)
fun coloredText(
    textView: TextView,
    first: String,
    second: String,
    base: Int,
) {
    val text = "<font color=#${Integer.toHexString(base).substring(2)}>$first</font> " +
            "<font color=#${
                Integer.toHexString(textView.getColorPrimary()).substring(2)
            }>$second</font>"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        textView.text = Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
    } else {
        textView.text = Html.fromHtml(text)
    }
}


fun View.getColorPrimary(): Int {
    val theme = context.theme
    val typedValue = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
    return typedValue.data
}

@RequiresApi(Build.VERSION_CODES.M)
fun View.getColor(resId: Int): Int {
    return resources.getColor(resId, context.theme)
}



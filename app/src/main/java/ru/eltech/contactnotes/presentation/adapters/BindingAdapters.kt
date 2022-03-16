package ru.eltech.contactnotes.presentation.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import ru.eltech.contactnotes.R


@BindingAdapter("numAsText")
fun bindNumAsText(textView: TextView, num: Int) {
    textView.text = num.toString()
}

@BindingAdapter("textInputNameError")
fun bindTextInputNameError(textInputLayout: TextInputLayout, isError: Boolean) {
    if (isError) {
        textInputLayout.error = textInputLayout.context.getString(R.string.error_input_name)
    } else {
        textInputLayout.error = null
    }
}
@BindingAdapter("textInputCountError")
fun bindTextInputCountError(textInputLayout: TextInputLayout, isError: Boolean) {
    if (isError) {
        textInputLayout.error = textInputLayout.context.getString(R.string.error_input_count)
    } else {
        textInputLayout.error = null
    }
}
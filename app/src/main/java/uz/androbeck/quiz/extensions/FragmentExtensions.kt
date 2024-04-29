package uz.androbeck.quiz.extensions

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String, isLong: Boolean = false) {
    val duration = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.getColor(resId: Int) = ContextCompat.getColor(requireContext(), resId)
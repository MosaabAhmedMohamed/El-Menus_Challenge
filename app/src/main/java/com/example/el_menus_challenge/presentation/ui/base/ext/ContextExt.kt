package com.example.el_menus_challenge.presentation.ui.base.ext

import android.content.Context
import android.widget.Toast


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
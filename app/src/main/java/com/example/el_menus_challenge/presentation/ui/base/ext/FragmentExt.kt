package com.example.el_menus_challenge.presentation.ui.base.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


fun FragmentActivity.addFragment(
    fragment: Fragment,
    frameId: Int, addToBackStack: Boolean = false, tag: String = ""
) {
    supportFragmentManager?.inTransaction {
        add(frameId, fragment)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}

fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int, addToBackStack: Boolean = false, tag: String = ""
) {
    supportFragmentManager?.inTransaction {
        replace(frameId, fragment)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}

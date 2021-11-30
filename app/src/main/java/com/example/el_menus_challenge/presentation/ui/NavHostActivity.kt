package com.example.el_menus_challenge.presentation.ui

import android.os.Bundle
import com.example.el_menus_challenge.R
import com.example.el_menus_challenge.presentation.ui.base.BaseActivity

class NavHostActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }
}
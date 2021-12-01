package com.example.el_menus_challenge

import android.os.Bundle
import com.example.presentation.base.ui.BaseActivity
import com.example.presentation.base.ui.NavManager
import com.example.presentation.base.ui.ext.navigateSafe

class NavHostActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
        initNavManager()
    }

    private fun initNavManager() {
        NavManager.setOnNavEvent {

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }
    }
}
package com.example.sharbat.presentation.features.main

import android.os.Bundle
import android.view.Menu
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.sharbat.R
import com.example.sharbat.presentation.utils.CustomPagerAdapter
import com.example.sharbat.presentation.features.account.AccountFragment
import com.example.sharbat.presentation.features.events.all.AllEventsFragment
import com.example.sharbat.presentation.features.events.my.MyEventsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setupNavigation()
        setStatusBarColor(R.color.colorPrimary)
    }

    private fun setupNavigation() {
        val tabs = listOf(MyEventsFragment(), AllEventsFragment(), AccountFragment())
        viewPager.adapter =
            CustomPagerAdapter(supportFragmentManager, tabs)
        viewPager.offscreenPageLimit = tabs.size - 1
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                bottomNavigation.menu.getItem(position).isChecked = true
            }
        })
        bottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            viewPager.currentItem = when (item.itemId) {
                R.id.menu_star -> 0
                R.id.menu_crop -> 1
                R.id.menu_account -> 2
                else -> -1
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    protected fun setStatusBarColor(@ColorRes id: Int) {
        val window: Window = getWindow()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setStatusBarColor(resources.getColor(id))
    }
}
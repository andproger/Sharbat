package com.example.sharbat.presentation.fetures.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.sharbat.R
import com.example.sharbat.data.adapter.CustomPagerAdapter
import com.example.sharbat.presentation.fetures.events.AllEventsFragment
import com.example.sharbat.presentation.fetures.share.MyEventsFragment
import com.example.sharbat.presentation.fetures.slideshow.AccountFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val tabs = listOf(MyEventsFragment(), AllEventsFragment(), AccountFragment())

        viewPager.adapter = CustomPagerAdapter(supportFragmentManager, tabs)
        viewPager.offscreenPageLimit = tabs.size - 1
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                bottomNavigation.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            viewPager.currentItem = when (item.itemId) {
                R.id.menu_all_events -> 0
                R.id.menu_my_events -> 1
                R.id.menu_account -> 2
                else -> -1
            }
            false
        }
    }
}
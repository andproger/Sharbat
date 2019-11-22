package com.example.sharbat.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.sharbat.R
import com.example.sharbat.data.adapter.CustomPagerAdapter
import com.example.sharbat.presentation.ui.home.HomeFragment
import com.example.sharbat.presentation.ui.share.ShareFragment
import com.example.sharbat.presentation.ui.slideshow.SlideshowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    var lastItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNavigation()
    }

    private fun createNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val pager: ViewPager = findViewById(R.id.pager)
        pager.adapter = CustomPagerAdapter(
            supportFragmentManager, listOf(HomeFragment(), ShareFragment(), SlideshowFragment())
        )
        pager.offscreenPageLimit = 2
        pager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                bottomNavigation.menu.getItem(lastItem).isChecked = false
                lastItem = position
                bottomNavigation.menu.getItem(position).isChecked = true
                Log.println(Log.ASSERT, "msg", position.toString())
            }
        })
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    pager.currentItem = 0
                }
                R.id.menu_gallery -> {
                    pager.currentItem = 1
                }
                R.id.menu_share -> {
                    pager.currentItem = 2
                }
            }
            false
        }
    }
}
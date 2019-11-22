package com.example.sharbat.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.sharbat.R
import com.example.sharbat.data.adapter.CustomPagerAdapter
import com.example.sharbat.presentation.ui.home.HomeFragment
import com.example.sharbat.presentation.ui.share.ShareFragment
import com.example.sharbat.presentation.ui.slideshow.SlideshowFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val tabs = listOf(HomeFragment(), ShareFragment(), SlideshowFragment())

        pager.adapter = CustomPagerAdapter(supportFragmentManager, tabs)
        pager.offscreenPageLimit = tabs.size - 1
        pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                bottomNavigation.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            pager.currentItem = when (item.itemId) {
                R.id.menu_home -> 0
                R.id.menu_slide -> 1
                R.id.menu_share -> 2
                else -> -1
            }
            false
        }
    }
}
package com.example.sharbat.presentation.fetures.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.sharbat.R
import com.example.sharbat.data.adapter.CustomPagerAdapter
import com.example.sharbat.presentation.fetures.account.AccountFragment
import com.example.sharbat.presentation.fetures.events.all.AllEventsFragment
import com.example.sharbat.presentation.fetures.events.my.MyEventsFragment
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
                R.id.menu_star -> 0
                R.id.menu_crop -> 1
                R.id.menu_account -> 2
                else -> -1
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_bottom, menu)
        return true
    }
}
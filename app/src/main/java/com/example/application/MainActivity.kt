package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.application.adapters.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        viewPager2.adapter = ViewPagerFragmentAdapter(this)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            if (position == 0) {
                tab.text = "DC"
            } else {
                tab.text = "Marvel"
            }
        }.attach()
    }

    fun init() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
    }
}
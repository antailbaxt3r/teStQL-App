package com.antailbaxt3r.gettestsapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.antailbaxt3r.gettestsapplication.R
import com.antailbaxt3r.gettestsapplication.utils.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class ViewTestsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tests)

        setViewPager()
    }

    private fun setViewPager() {
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val adapter = ViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = adapter
        val tabLayout = findViewById<TabLayout>(R.id.dashboard_tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}

package com.antailbaxt3r.gettestsapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.antailbaxt3r.gettestsapplication.fragments.AdvancedFragment
import com.antailbaxt3r.gettestsapplication.fragments.CommonFragment
import com.antailbaxt3r.gettestsapplication.fragments.MainsFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return CommonFragment()
            1 -> return AdvancedFragment()
            2 -> return MainsFragment()
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Common"
            1 -> return "JEE(M+A)"
            2 -> return "JEE(Mains)"
        }
        return null
    }

    companion object {
        private const val TAB_COUNT = 3
    }
}
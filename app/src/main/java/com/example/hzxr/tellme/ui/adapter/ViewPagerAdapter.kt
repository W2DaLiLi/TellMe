package com.example.hzxr.tellme.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Hzxr on 2018/2/5.
 */
class ViewPagerAdapter(private val fragments: List<Fragment>,
                       private val titles: List<String>,
                       fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
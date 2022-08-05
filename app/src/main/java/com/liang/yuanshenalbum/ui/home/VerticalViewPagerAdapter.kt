package com.liang.yuanshenalbum.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class VerticalViewPagerAdapter(fm: FragmentManager, val list: List<String>) :
    FragmentPagerAdapter(fm) {

    override fun getCount() = list.size

    override fun getItem(position: Int): Fragment {
        return HomeFragment(list[position])
    }
}
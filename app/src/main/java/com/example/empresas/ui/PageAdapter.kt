package com.example.empresas.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.coroutines.currentCoroutineContext

class PageAdapter(fm: FragmentManager?, private val tabCount: Int) :
        FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            else -> FavoriteCompaniesFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}

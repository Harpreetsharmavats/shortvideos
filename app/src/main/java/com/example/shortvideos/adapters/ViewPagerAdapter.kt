package com.example.shortvideos.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    val fragmentlist= mutableListOf<Fragment>()
    val titlelist= mutableListOf<String>()

    override fun getCount(): Int {
        return fragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
      return fragmentlist.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlelist.get(position)
    }
    fun addFragments(fragment: Fragment,title:String){
        fragmentlist.add(fragment)
        titlelist.add(title)
    }
}
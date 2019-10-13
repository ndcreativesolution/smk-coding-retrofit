package com.example.smkcodingretrofit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.smkcodingretrofit.fragments.NowPlayingFragment
import com.example.smkcodingretrofit.fragments.UpcomingFragment

class TabAdapter (fm: FragmentManager?): FragmentStatePagerAdapter(fm!!) {

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return NowPlayingFragment()
        } else {
            return UpcomingFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}
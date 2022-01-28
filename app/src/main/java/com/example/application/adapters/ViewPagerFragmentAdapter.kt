package com.example.application.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.application.fragments.DCFragment
import com.example.application.fragments.MarvelFragment

class ViewPagerFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return DCFragment()
        }
        return MarvelFragment()
    }

}
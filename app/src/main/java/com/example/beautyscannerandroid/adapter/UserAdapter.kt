package com.example.beautyscannerandroid.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.fragment.UserAllergensFragment
import com.example.beautyscannerandroid.fragment.UserDetailsFragment
import com.example.beautyscannerandroid.fragment.UserProductsFragment


class UserAdapter(context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var pagerTitles: List<String> = listOf(
        context.getString(R.string.user_details),
        context.getString(R.string.user_products),
        context.getString(R.string.user_allergens)
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UserDetailsFragment()
            1 -> UserProductsFragment()
            2 -> UserAllergensFragment()
            else -> UserDetailsFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pagerTitles[position]
    }
}


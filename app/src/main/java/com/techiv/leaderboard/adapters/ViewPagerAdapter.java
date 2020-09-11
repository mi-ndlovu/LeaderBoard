package com.techiv.leaderboard.adapters;

import com.techiv.leaderboard.fragments.LearningFragment;
import com.techiv.leaderboard.fragments.SkillIQFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new LearningFragment();
        }
        else if (position == 1)
        {
            fragment = new SkillIQFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Learning Leaders";
        }
        else if (position == 1)
        {
            title = "Skill IQ Leaders";
        }
        return title;
    }
}

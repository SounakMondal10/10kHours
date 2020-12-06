package com.sounakmondal.a10khours.ViewPager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.sounakmondal.a10khours.Fragments.taskSelectorView;
import com.sounakmondal.a10khours.Fragments.timerView;

public class Pageradapter extends FragmentStatePagerAdapter {

    public Pageradapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position)
        {
            case(0):
            {
               returnFragment = taskSelectorView.newInstance();
               break;
            }
            case(1):
            {
                returnFragment = timerView.newInstance();
                break;
            }

            default: returnFragment = null;
        }


        return returnFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position)
        {
            case(0):
            {
                title = "Select Task";
                break;
            }
            case(1):
            {
                title = "Timer";
                break;
            }

            default: title = null;
        }


        return title;
    }
}

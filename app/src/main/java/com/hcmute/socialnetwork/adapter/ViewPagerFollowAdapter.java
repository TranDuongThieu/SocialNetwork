package com.hcmute.socialnetwork.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hcmute.socialnetwork.fragment.FollowerFragment;
import com.hcmute.socialnetwork.fragment.FollowingFragment;


public class ViewPagerFollowAdapter extends FragmentStateAdapter {
    public ViewPagerFollowAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FollowerFragment();
            case 1:
                return new FollowingFragment();
            default:
                return new FollowerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

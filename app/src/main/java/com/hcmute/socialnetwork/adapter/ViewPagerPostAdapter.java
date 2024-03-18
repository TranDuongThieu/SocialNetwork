package com.hcmute.socialnetwork.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hcmute.socialnetwork.fragment.ListPostsFragment;
import com.hcmute.socialnetwork.fragment.ListPostsVideoFragment;

public class ViewPagerPostAdapter extends FragmentStateAdapter {

    public ViewPagerPostAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ListPostsFragment();
            case 1:
                return new ListPostsVideoFragment();
            default:
                return new ListPostsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

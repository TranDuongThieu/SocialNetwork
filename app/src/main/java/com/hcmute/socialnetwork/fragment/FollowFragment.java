package com.hcmute.socialnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.adapter.ViewPagerFollowAdapter;

public class FollowFragment extends Fragment {
    private TabLayout tlFollow;
    private ViewPager2 vpFollow;
    private ViewPagerFollowAdapter vpFollowAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        tlFollow = view.findViewById(R.id.tlFollow);
        vpFollow = view.findViewById(R.id.vpFollow);

        vpFollowAdapter = new ViewPagerFollowAdapter((FragmentActivity) requireContext());
        vpFollow.setAdapter(vpFollowAdapter);

        new TabLayoutMediator(tlFollow, vpFollow, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Người theo dõi");
                    break;
                case 1:
                    tab.setText("Đang theo dõi");
                    break;
            }
        }).attach();

        return view;
    }
}

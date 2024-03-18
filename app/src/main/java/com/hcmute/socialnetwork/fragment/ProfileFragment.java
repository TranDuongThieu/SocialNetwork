package com.hcmute.socialnetwork.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.fragment.editprofile.EditProfile;
import com.hcmute.socialnetwork.adapter.ViewPagerPostAdapter;

public class ProfileFragment extends Fragment {
     TabLayout tabLayoutPosts;
     ViewPager2 viewPagerPost;
     ViewPagerPostAdapter viewPagerPostAdapter;
     Button btnProfileShare, btnProfileEdit;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_profile, container, false);
        viewPagerPostAdapter = new ViewPagerPostAdapter((FragmentActivity) requireContext());
        findID(view);
        viewPagerPost.setAdapter(viewPagerPostAdapter);
        new TabLayoutMediator(tabLayoutPosts, viewPagerPost, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.grid_ic);
                    break;
                case 1:
                    tab.setIcon(R.drawable.video_ic);
                    break;
            }
        }).attach();

        // edit profile
        btnProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfile.class));
            }
        });
        return view;
    }

    private void findID(View view) {
        tabLayoutPosts = view.findViewById(R.id.tabLayoutPosts);
        viewPagerPost = view.findViewById(R.id.viewPagerPosts);
        btnProfileShare = view.findViewById(R.id.btnProfileShare);
        btnProfileEdit = view.findViewById(R.id.btnProfileEdit);
    }

}

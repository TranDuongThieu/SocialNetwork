package com.hcmute.socialnetwork.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

<<<<<<<< HEAD:app/src/main/java/com/hcmute/socialnetwork/fragment/MyProfileFragment.java
import androidx.annotation.FloatRange;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
========
>>>>>>>> 8978964 (fixx):app/src/main/java/com/hcmute/socialnetwork/fragment/ProfileFragment.java
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.fragment.editprofile.EditProfile;
import com.hcmute.socialnetwork.adapter.ViewPagerPostAdapter;
<<<<<<<< HEAD:app/src/main/java/com/hcmute/socialnetwork/fragment/MyProfileFragment.java
import com.hcmute.socialnetwork.utils.AdroidUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
public class MyProfileFragment extends Fragment {
    TabLayout tabLayoutPosts;
    ViewPager2 viewPagerPost;
    ViewPagerPostAdapter viewPagerPostAdapter;
    Button btnProfileShare, btnProfileEdit;
========

public class ProfileFragment extends Fragment {
     TabLayout tabLayoutPosts;
     ViewPager2 viewPagerPost;
     ViewPagerPostAdapter viewPagerPostAdapter;
     Button btnProfileShare, btnProfileEdit;

<<<<<<<< HEAD:app/src/main/java/com/hcmute/socialnetwork/fragment/MyProfileFragment.java
>>>>>>>> 8978964 (fixx):app/src/main/java/com/hcmute/socialnetwork/fragment/ProfileFragment.java
========
>>>>>>>> 89789645b79b9c43b8c1cf1e9918cc5d36755176:app/src/main/java/com/hcmute/socialnetwork/fragment/ProfileFragment.java
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        // tab post
        tabLayoutPosts = view.findViewById(R.id.tabLayoutPosts);
        viewPagerPost = view.findViewById(R.id.viewPagerPosts);

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

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.llFragmentFollow, fragment);
        transaction.commit();
    }
}

package com.hcmute.socialnetwork.activity.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.MainActivity;
import com.hcmute.socialnetwork.activity.adapter.ViewPagerPostAdapter;

import java.util.ArrayList;

public class ProfileUser extends AppCompatActivity {
    //TabHost tabhost;
    TabLayout tabLayoutPosts;
    ViewPager2 viewPagerPost;
    ViewPagerPostAdapter viewPagerPostAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tabLayoutPosts = findViewById(R.id.tabLayoutPosts);
        viewPagerPost = findViewById(R.id.viewPagerPosts);

        viewPagerPostAdapter = new ViewPagerPostAdapter(this);
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





//        tabhost = findViewById(R.id.tabhost);
//        tabhost.setup();
//
//        // Tạo tab1
//        TabHost.TabSpec spec1 = tabhost.newTabSpec("Tab1");
//        spec1.setIndicator("", getResources().getDrawable(R.drawable.logo_instagram)); // Đặt tiêu đề và biểu tượng
//        spec1.setContent(R.id.tab1);
//        tabhost.addTab(spec1);
//
//        // Tạo tab2
//        TabHost.TabSpec spec2 = tabhost.newTabSpec("Tab2");
//        spec2.setIndicator("", getResources().getDrawable(R.drawable.bookmark_ic)); // Đặt tiêu đề và biểu tượng
//        spec2.setContent(R.id.tab2);
//
//        tabhost.addTab(spec2);
    }
}

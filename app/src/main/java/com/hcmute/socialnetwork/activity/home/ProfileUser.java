package com.hcmute.socialnetwork.activity.home;

import android.os.Bundle;
import android.widget.TabHost;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hcmute.socialnetwork.R;

public class ProfileUser extends AppCompatActivity {
    TabHost tabhost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

//        tabhost.setup();
//
//        // Tạo tab1
//        TabHost.TabSpec spec1 = tabhost.newTabSpec("Tab1");
//        spec1.setIndicator("Tab 1", getResources().getDrawable(R.drawable.ic_tab1_icon)); // Đặt tiêu đề và biểu tượng
//        spec1.setContent(R.id.tab1);
//        tabHost.addTab(spec1);
//
//        // Tạo tab2
//        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab2");
//        spec2.setIndicator("Tab 2", getResources().getDrawable(R.drawable.ic_tab2_icon)); // Đặt tiêu đề và biểu tượng
//        spec2.setContent(R.id.tab2);
//        tabHost.addTab(spec2);

    }
}

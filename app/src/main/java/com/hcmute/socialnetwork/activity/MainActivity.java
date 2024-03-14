package com.hcmute.socialnetwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.Image;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.CustomActionBar.CustomActionBarActivity;
import com.hcmute.socialnetwork.fragment.HomeFragment;
import com.hcmute.socialnetwork.fragment.PostFragment;
import com.hcmute.socialnetwork.fragment.ProfileFragment;
import com.hcmute.socialnetwork.fragment.ReelFragment;
import com.hcmute.socialnetwork.fragment.SearchFragment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends CustomActionBarActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActionBar();

        frameLayout = findViewById(R.id.frameMainLayout);
        ArrayList<ImageView> imageList = new ArrayList<>();
        imageList.add(findViewById(R.id.action_home));
        imageList.add(findViewById(R.id.action_search));
        imageList.add(findViewById(R.id.action_post));
        imageList.add(findViewById(R.id.action_video));

        CircleImageView avt = findViewById(R.id.action_profile);
        avt.setAlpha(0.6f);
        setActive(imageList,findViewById(R.id.action_home) );
        replaceFragment(new HomeFragment());
    }

    public void onItemClick(View view) {
        ImageView clickedImageView = (ImageView) view;
        ArrayList<ImageView> imageList = new ArrayList<>();
        imageList.add(findViewById(R.id.action_home));
        imageList.add(findViewById(R.id.action_search));
        imageList.add(findViewById(R.id.action_post));
        imageList.add(findViewById(R.id.action_video));
        CircleImageView avt = findViewById(R.id.action_profile);
        if (clickedImageView == imageList.get(0)){
            replaceFragment(new HomeFragment());
        }
        else if (clickedImageView == imageList.get(1)){
            replaceFragment(new SearchFragment());
        }
        else if (clickedImageView == imageList.get(2)){
            replaceFragment(new PostFragment());
        }
        else if (clickedImageView == imageList.get(3)){
            replaceFragment(new ReelFragment());
        }
        avt.setAlpha(0.6f);
        setActive(imageList, clickedImageView);

    }
    public void onItemClick2(View view) {
        CircleImageView clickedImageView = (CircleImageView) view;
        ArrayList<ImageView> imageList = new ArrayList<>();
        imageList.add(findViewById(R.id.action_home));
        imageList.add(findViewById(R.id.action_search));
        imageList.add(findViewById(R.id.action_post));
        imageList.add(findViewById(R.id.action_video));
        CircleImageView avt = findViewById(R.id.action_profile);
        avt.setAlpha(1.0f);
        for (ImageView imageView : imageList) {
            imageView.setAlpha(0.6f);
        }
        replaceFragment(new ProfileFragment());
    }

    private void setActive(ArrayList<ImageView> imgList, ImageView active) {
        for (ImageView imageView : imgList) {
            imageView.setAlpha(imageView == active ? 1.0f : 0.6f);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameMainLayout, fragment);
        fragmentTransaction.commit();
    }
}

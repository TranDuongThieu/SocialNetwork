package com.hcmute.socialnetwork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.adapter.FollowListAdapter;
import com.hcmute.socialnetwork.model.User;

import java.util.ArrayList;

public class FollowingFragment extends Fragment {

    ListView lvFollowing;
    User currentUser = new User();
    ArrayList<User> followingList = currentUser.getFollowings();
    FollowListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_list, container, false);
        lvFollowing = view.findViewById(R.id.lvfollow);

        User newUser1 = new User("3", null, "Loc","Vu", null, null,null, R.drawable.thumb1, null, null, null, null);
        User newUser2 = new User("4", null, "Thieu","Tran", null, null, null, R.drawable.thumb1 , null, null, null, null);
        if (followingList == null) {
            followingList = new ArrayList<>();
        }
        followingList.add(newUser1);
        followingList.add(newUser2);

        adapter = new FollowListAdapter(getContext(), followingList);
        lvFollowing.setAdapter(adapter);

        return view;
    }
}

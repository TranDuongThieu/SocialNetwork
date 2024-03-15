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

public class FollowerFragment extends Fragment {
    ListView lvFollower;
    User currentUser = new User();
    ArrayList<User> followerList = currentUser.getFollowers();
    FollowListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_list, container, false);
        lvFollower = view.findViewById(R.id.lvfollow);

        User newUser1 = new User("1", null, "Minh","Tran", null, "0966716073", R.drawable.thumb1, null, null, null, null);
        User newUser2 = new User("2", null, "Thuong","Trinh", null, "0923456765", R.drawable.thumb1, null, null, null, null);
        followerList.add(newUser1);
        followerList.add(newUser2);

        adapter = new FollowListAdapter(getContext(), followerList);
        lvFollower.setAdapter(adapter);
        return view;
    }
}

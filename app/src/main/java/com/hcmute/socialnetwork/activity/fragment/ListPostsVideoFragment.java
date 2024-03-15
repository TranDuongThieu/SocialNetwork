package com.hcmute.socialnetwork.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.adapter.ImageListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListPostsVideoFragment extends Fragment {
    GridView gvPost;
    private ImageListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_posts_video, container, false);
        gvPost = view.findViewById(R.id.gvThumb);

        // Prepare image data
        List<Integer> imageIds = new ArrayList<>();
        imageIds.add(R.drawable.thumb1);
        imageIds.add(R.drawable.thumb1);
        imageIds.add(R.drawable.thumb1);
        // Add more image ids as needed

        // Set up the adapter
        adapter = new ImageListAdapter(getActivity(), imageIds);
        gvPost.setAdapter(adapter);
        return view;

    }
}

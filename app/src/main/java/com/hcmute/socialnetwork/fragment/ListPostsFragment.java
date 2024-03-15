package com.hcmute.socialnetwork.fragment;

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
import com.hcmute.socialnetwork.model.Blog;

import java.util.ArrayList;
import java.util.List;

public class ListPostsFragment extends Fragment {
    GridView gvPost;
    private ImageListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_posts, container, false);
        gvPost = view.findViewById(R.id.gvPost);

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

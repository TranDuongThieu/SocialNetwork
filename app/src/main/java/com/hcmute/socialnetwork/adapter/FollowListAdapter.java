package com.hcmute.socialnetwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.model.User;

import java.util.ArrayList;

public class FollowListAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> followerList;
    public FollowListAdapter (Context context, ArrayList<User> followerList) {
        this.context = context;
        this.followerList = followerList;
    }
    @Override
    public int getCount() {
        return followerList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_follow, null);

        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar);
        TextView txtUsername = convertView.findViewById(R.id.txtUsername);
        TextView txtFullName = convertView.findViewById(R.id.txtFullName);

        User follower = followerList.get(position);

        imgAvatar.setImageResource(follower.getAvatar());
        txtUsername.setText(follower.getFirstName());
        txtFullName.setText(follower.getLastName());

        return convertView;
    }
}

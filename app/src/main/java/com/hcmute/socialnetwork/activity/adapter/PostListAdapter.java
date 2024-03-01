package com.hcmute.socialnetwork.activity.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.activity.home.Post;
import com.hcmute.socialnetwork.model.Blog;
import com.hcmute.socialnetwork.model.User;

import java.util.ArrayList;

public class PostListAdapter extends BaseAdapter {
    private User postBy;
    private ArrayList<Blog>postList;
    private ArrayList<User>likes;

    public PostListAdapter(User postBy, ArrayList<Blog> postList, ArrayList<User> likes) {
        this.postBy = postBy;
        this.postList = postList;
        this.likes = likes;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View postView;
        if (convertView == null) {
            postView = View.inflate(parent.getContext(), R.layout.activity_post, null);
            holder = new ViewHolder();
            holder.avatar = postView.findViewById(R.id.imgPostAvt);
            holder.cmtButton = postView.findViewById(R.id.btnPostComment);
            holder.description = postView.findViewById(R.id.txtPostdes);
            holder.likes = postView.findViewById(R.id.txtPostLike);
            holder.likeButton = postView.findViewById(R.id.btnPostLike);
            holder.shareButton = postView.findViewById(R.id.btnPostShare);
            holder.thumbnail = postView.findViewById(R.id.imgPostThumb);
            holder.userName2 = postView.findViewById(R.id.txtPostName2);
            holder.userName = postView.findViewById(R.id.txtPostName);
            holder.cmt = postView.findViewById(R.id.txtPostCmt);
            holder.date = postView.findViewById(R.id.txtPostDate);
            postView.setTag(holder);
        } else {
            postView = convertView;
            holder = (ViewHolder) postView.getTag();
        }
        Blog blog =(Blog) getItem(position);

        holder.userName.setText(postBy.getFirstName() + postBy.getLastName());
        holder.userName2.setText(postBy.getFirstName() + postBy.getLastName());
        holder.likes.setText(String.valueOf(likes.size()));
        holder.cmt.setText(String.valueOf(blog.getComments().size()));
        holder.avatar.setImageResource(postBy.getAvatar());
        holder.thumbnail.setImageResource(blog.getThumbnail());
        holder.description.setText(blog.getDescription());
        holder.date.setText(String.valueOf(blog.getPostedAt()));
        return postView;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView userName;
        ImageView thumbnail;
        TextView likes;
        TextView userName2;
        TextView description;
        TextView cmt;
        ImageButton likeButton;
        ImageButton cmtButton;
        ImageButton shareButton;
        TextView date;

    }
}

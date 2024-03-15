package com.hcmute.socialnetwork.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;

import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.helper.RemainingTime;
import com.hcmute.socialnetwork.model.Blog;
import com.hcmute.socialnetwork.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostListAdapter extends BaseAdapter {
    private User postBy;
    private ArrayList<Blog> postList;
    int i = 0;

    public PostListAdapter(User postBy, ArrayList<Blog> postList) {
        this.postBy = postBy;
        this.postList = postList;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
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
//            holder.video = postView.findViewById(R.id.videoPostThumb);
            holder.thumbnail = postView.findViewById(R.id.imgPostThumb);
            holder.userName2 = postView.findViewById(R.id.txtPostName2);
            holder.userName = postView.findViewById(R.id.txtPostName);
            holder.cmt = postView.findViewById(R.id.txtPostCmt);
            holder.date = postView.findViewById(R.id.txtPostDate);
            holder.heartImg = postView.findViewById(R.id.imgPostHeartIcon);
            postView.setTag(holder);
        } else {
            postView = convertView;
            holder = (ViewHolder) postView.getTag();
        }
        Blog blog = (Blog) getItem(position);

        holder.userName.setText(String.valueOf(postBy.getFirstName() + postBy.getLastName()));
        holder.userName2.setText(String.valueOf(postBy.getFirstName() + postBy.getLastName()));
        holder.likes.setText(String.valueOf(blog.getLikes().size()));
        holder.cmt.setText(String.valueOf(blog.getComments().size()));
        holder.avatar.setImageResource(postBy.getAvatar());
        holder.description.setText(blog.getDescription());
        holder.date.setText(RemainingTime.remainingTime(blog.getPostedAt()));
        holder.thumbnail.setImageResource(blog.getThumbnail());
//        if (blog.getThumbnail() ) {
//            holder.thumbnail.setImageResource(blog.getThumbnail());
//            holder.thumbnail.setVisibility(View.VISIBLE);
//            holder.video.setVisibility(View.GONE);
//        } else if (blog.getVideo() ) {
//            holder.video.setVideoURI(Uri.parse(blog.getVideoUrl()));
//            holder.video.requestFocus();
//            holder.video.start();
//        } else {
//            // If neither thumbnail nor video is available, you can hide both views
//            holder.thumbnail.setVisibility(View.GONE);
//            holder.video.setVisibility(View.GONE);
//        }

        User userLogin = User.getInstance();

        if (blog.getLikes().contains(userLogin)) {
            holder.likeButton.setImageResource(R.drawable.fill_heart_icon);
        } else
            holder.likeButton.setImageResource(R.drawable.heart_icon);

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Blog blog = (Blog) getItem(position);
                ArrayList<User > likesList = blog.getLikes();
                if (likesList.contains(userLogin)) {
                    // User already liked, so remove the like
                    likesList.remove(userLogin);

                } else {
                    // User hasn't liked, so add the like
                    likesList.add(userLogin);
                    animateHeartImage(holder.heartImg);
                }
                blog.setLikes(likesList);
                animateHeart(holder.likeButton);

                postList.get(position).setLikes(likesList);
                holder.likes.setText(String.valueOf(blog.getLikes().size()));
                notifyDataSetChanged();


            }
        });
        return postView;

    }
    private void animateHeartImage(final ImageView heartImg){
        heartImg.setVisibility(View.VISIBLE);
        animateHeart(heartImg);
        heartImg.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Hide imgPostHeartIcon
                heartImg.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void animateHeart(final ImageView likeButton) {
        // Use ObjectAnimator to animate the scale of the heart icon
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(likeButton, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(likeButton, "scaleY", 1f, 1.2f, 1f);

        // Set duration and interpolator for the animation
        scaleX.setDuration(300);
        scaleY.setDuration(300);
        scaleX.setInterpolator(new OvershootInterpolator());
        scaleY.setInterpolator(new OvershootInterpolator());

        // Create AnimatorSet and play the animations together
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }

    static class ViewHolder {
        CircleImageView avatar;
        TextView userName;
        ImageView thumbnail;
        VideoView video;
        TextView likes;
        TextView userName2;
        TextView description;
        TextView cmt;
        ImageButton likeButton;
        ImageButton cmtButton;
        ImageButton shareButton;
        TextView date;
        ImageView heartImg;

    }
}

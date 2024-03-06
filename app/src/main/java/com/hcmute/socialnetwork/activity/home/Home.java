package com.hcmute.socialnetwork.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import com.hcmute.socialnetwork.R;
import com.hcmute.socialnetwork.adapter.PostListAdapter;
import com.hcmute.socialnetwork.model.Blog;
import com.hcmute.socialnetwork.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User user = new User();
        user.setUserId("1");
        user.setFirstName("Tran");
        user.setLastName("Thiu");
        user.setAvatar(R.drawable.thumb1);

        Blog blog = createFakeBlog(user, R.drawable.logo_instagram);
        Blog blog1 = createFakeBlog(user, R.drawable.thumb1);
        ArrayList<Blog> blogList = new ArrayList<Blog>();
        blogList.add(blog);
        blogList.add(blog1);

        PostListAdapter postListAdapter = new PostListAdapter(user, blogList);
        ListView postList = findViewById(R.id.lvHomePost);
        postList.setAdapter(postListAdapter);


    }

    private static Blog createFakeBlog(User user, int thumb) {
        Blog blog = new Blog();

        // Set thumbnail (assuming it's an integer, replace with appropriate data)
        blog.setThumbnail(thumb);


        blog.setDescription("This is a fake blog post.");


        // Set posted date and time
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Random rand = new Random();
            blog.setPostedAt(LocalDateTime.of(2023, rand.nextInt(12) +1, rand.nextInt(28) +1, 6, 20));
        }

        // Set posted by user
        blog.setPostedBy(user.getFirstName() + " " + user.getLastName());

        // Set likes (replace with user IDs who liked the blog)
        ArrayList<String> likes = new ArrayList<>();
        likes.add("user1");
        likes.add("user2");
        blog.setLikes(likes);

        // Set comments (replace with fake comments)
        ArrayList<Blog.Comment> comments = createFakeComments(user);
        blog.setComments(comments);

        return blog;
    }

    private static ArrayList<Blog.Comment> createFakeComments(User user) {
        ArrayList<Blog.Comment> comments = new ArrayList<>();

        // Create fake comments
        Blog.Comment comment1 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            comment1 = new Blog.Comment("user1", "Great post!", LocalDateTime.now(), new ArrayList<>());
        }
        Blog.Comment comment2 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            comment2 = new Blog.Comment("user2", "Nice content!", LocalDateTime.now().minusHours(1), new ArrayList<>());
        }

        comments.add(comment1);
        comments.add(comment2);

        return comments;
    }
}
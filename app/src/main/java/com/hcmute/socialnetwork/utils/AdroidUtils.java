package com.hcmute.socialnetwork.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdroidUtils {
    public static void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void setProfilePic(Context context, Uri imageUri, CircleImageView imageAvatar)
    {
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageAvatar);

    }
}

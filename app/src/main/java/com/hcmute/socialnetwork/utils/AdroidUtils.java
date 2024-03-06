package com.hcmute.socialnetwork.utils;

import android.content.Context;
import android.widget.Toast;

public class AdroidUtils {
    public static void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }
}

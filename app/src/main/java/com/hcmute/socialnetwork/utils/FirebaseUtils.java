package com.hcmute.socialnetwork.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtils {

    public static String curentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }

    public static DocumentReference curentUserDetails(){
        return FirebaseFirestore.getInstance().collection("users").document(curentUserId());
    }

    public  static DocumentReference accountDetail(){
        return FirebaseFirestore.getInstance().collection("account").document(curentUserId());
    }

}

package com.hcmute.socialnetwork.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseUtils {

    public static String curentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }

    public static DocumentReference curentUserDetails(){
        return FirebaseFirestore.getInstance().collection("user").document(curentUserId());
    }

    public  static DocumentReference accountDetail(){
        return FirebaseFirestore.getInstance().collection("account").document(curentUserId());
    }

    public static StorageReference getCurrentAvatar(){
        return FirebaseStorage.getInstance().getReference().child("Avatar")
                .child("thieu098dm3@gmail.com");
    }


}

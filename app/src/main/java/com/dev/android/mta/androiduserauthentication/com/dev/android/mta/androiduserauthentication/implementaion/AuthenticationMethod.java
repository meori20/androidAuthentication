package com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.implementaion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.dev.android.mta.androiduserauthentication.PostLoginActivity;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.constans.ActivityExtrasConstans;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.interfaces.IAuthenticationMethod;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ori on 4/25/2018.
 */

public abstract class AuthenticationMethod {
    protected FirebaseAuth mAuth;
    protected AppCompatActivity mMainActivity;

    public AuthenticationMethod(FirebaseAuth iAuth, AppCompatActivity mainActivity){
        mAuth = iAuth;
        mMainActivity = mainActivity;
    }

    protected void startNewActivityWithUserDetails(){
        FirebaseUser user = mAuth.getCurrentUser();
        Intent intent = new Intent(mMainActivity, PostLoginActivity.class);
        intent.putExtra(ActivityExtrasConstans.FIREBASE_USER_EMAIL, user.getEmail());
        mMainActivity.startActivity(intent);
    }
}

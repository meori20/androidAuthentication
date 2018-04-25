package com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.implementaion;

import android.support.v7.app.AppCompatActivity;

import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.constans.AuthenticationMethodConstatns;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.interfaces.IAuthenticationMethod;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ori on 4/25/2018.
 */

public class AuthenticationMethodFactory {

    public static IAuthenticationMethod getAuthenticationMethod(String AuthenticationType, FirebaseAuth iAuth, AppCompatActivity iMainActivity){
        switch (AuthenticationType) {
            case AuthenticationMethodConstatns.EMAIL_AND__PASSWORD:
                return new EmailAndPasswordAuthentication(iAuth,iMainActivity);
            default:
                return null;
        }
    }
}

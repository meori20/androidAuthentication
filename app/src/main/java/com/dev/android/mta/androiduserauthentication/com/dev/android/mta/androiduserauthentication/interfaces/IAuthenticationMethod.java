package com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.interfaces;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ori on 4/25/2018.
 */

public interface IAuthenticationMethod {
    public void SignUp(String email, String password);
    public void SignIn(String email, String password);
}

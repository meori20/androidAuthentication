package com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.implementaion;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dev.android.mta.androiduserauthentication.PostLoginActivity;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.constans.ActivityExtrasConstans;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.interfaces.IAuthenticationMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ori on 4/25/2018.
 */

public class EmailAndPasswordAuthentication extends AuthenticationMethod implements IAuthenticationMethod{

    public EmailAndPasswordAuthentication(FirebaseAuth iAuth, AppCompatActivity iMainActivity){
        super(iAuth, iMainActivity );
    }

    @Override
    public void SignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(mMainActivity, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    startNewActivityWithUserDetails();
                }
                else {

                    Toast.makeText(mMainActivity, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    //TODO update
                }
            }
        });
    }

    @Override
    public void SignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mMainActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startNewActivityWithUserDetails();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(mMainActivity, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}



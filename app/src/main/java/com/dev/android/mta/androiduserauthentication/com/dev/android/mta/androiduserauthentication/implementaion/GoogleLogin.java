package com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.implementaion;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dev.android.mta.androiduserauthentication.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by Ori on 4/25/2018.
 */

public class GoogleLogin extends AuthenticationMethod {
    public GoogleLogin(FirebaseAuth iAuth, AppCompatActivity mainActivity) {
        super(iAuth, mainActivity);
    }

    public void SingInWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mMainActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startNewActivityWithUserDetails();
                        } else {
                            Toast.makeText(mMainActivity, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();                        }

                        // ...
                    }
                });
    }
}

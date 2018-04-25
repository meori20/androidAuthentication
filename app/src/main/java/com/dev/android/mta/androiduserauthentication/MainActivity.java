package com.dev.android.mta.androiduserauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.constans.AuthenticationMethodConstatns;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.implementaion.AuthenticationMethodFactory;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.implementaion.FacebookLogin;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.implementaion.GoogleLogin;
import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.interfaces.IAuthenticationMethod;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String mEmail;
    private String mPassword;
    private GoogleSignInOptions mGso;
    private GoogleSignInClient mGoogleSignInClient;
    CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        initFacebookAPI();
        mGso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGso);
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, AuthenticationMethodConstatns.RC_SIGN_IN);            }
        } );
    }




    private void initFacebookAPI() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.button_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                // ...
            }
        });


    }
    private void handleFacebookAccessToken(AccessToken token) {
        FacebookLogin facebookLogin = new FacebookLogin(mAuth,this);
        facebookLogin.SignInToFacebook(token);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AuthenticationMethodConstatns.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {


            }
        }
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        GoogleLogin googleLogin = new GoogleLogin(mAuth,this);
        googleLogin.SingInWithGoogle(account);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


    }

    public void onClickSignUpBtn(View v){
        updateCurrentEmailAndPassword();
        IAuthenticationMethod authenticationMethod = AuthenticationMethodFactory.getAuthenticationMethod(AuthenticationMethodConstatns.EMAIL_AND__PASSWORD,mAuth,this);
        authenticationMethod.SignUp(mEmail,mPassword);
    }

    public void onGoogleSignIn(View v){

    }

    public void onClickSignInBtn(View v) {
        updateCurrentEmailAndPassword();
        IAuthenticationMethod authenticationMethod = AuthenticationMethodFactory.getAuthenticationMethod(AuthenticationMethodConstatns.EMAIL_AND__PASSWORD,mAuth,this);
        authenticationMethod.SignIn(mEmail,mPassword);

    }


    private void updateCurrentEmailAndPassword(){
        mEmail = (((TextView)(findViewById(R.id.input_email))).getText()).toString();
        mPassword = (((TextView)(findViewById(R.id.input_password))).getText()).toString();
    }

}

package com.dev.android.mta.androiduserauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dev.android.mta.androiduserauthentication.com.dev.android.mta.androiduserauthentication.constans.ActivityExtrasConstans;

public class PostLoginActivity extends AppCompatActivity {

    String mUserName;
    TextView mNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        mNameTextView = (findViewById(R.id.NameTextView));
        Intent intent = getIntent();
        mUserName = intent.getStringExtra(ActivityExtrasConstans.FIREBASE_USER_EMAIL);
        mNameTextView.setText(mUserName);

    }
}

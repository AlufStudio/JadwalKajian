package com.tolabulilm.jadwalkajian.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.tolabulilm.jadwalkajian.R;
import com.tolabulilm.jadwalkajian.kajian.SearchActivity;
import com.tolabulilm.jadwalkajian.setting.SettingActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private DatabaseReference kajianRef;
    private FirebaseUser fireUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handleOnClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                goToSettingActivity();
        }
        return true;
    }

    private void goToSettingActivity() {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void initView() {
        fab = (FloatingActionButton)findViewById(R.id.fab);
    }

    private void handleOnClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });
    }

    private void initFirebase() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private void authListen() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                fireUser = FirebaseAuth.getInstance().getCurrentUser();
                if (fireUser != null) {
                    displayJadwalKajian(fireUser);
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        };
    }

    private void displayJadwalKajian(FirebaseUser fireUser) {
        kajianRef = mRef.child("users").child(fireUser.getUid()).child("jadwal");
        Query query = mRef.orderByChild("time");
    }
}

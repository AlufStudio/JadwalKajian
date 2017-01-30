package com.tolabulilm.jadwalkajian.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tolabulilm.jadwalkajian.R;
import com.tolabulilm.jadwalkajian.kajian.AddKajianActivity;
import com.tolabulilm.jadwalkajian.user.AdminActivity;
import com.tolabulilm.jadwalkajian.user.LoginActivity;
import com.tolabulilm.jadwalkajian.user.User;

public class SettingActivity extends AppCompatActivity {

    private ListView listView;
    private User user;
    private FirebaseUser fireUser;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private boolean isAdmin;
    private static final String[] settingList = {"Pengaturan","Info Donasi","Saran","Admin","Tentang","Logout"};
    private static final int[] imageId = {
            R.drawable.ic_settings_applications_white_36dp,
            R.drawable.ic_mosque_36dp,
            R.drawable.ic_chat_white_36dp,
            R.drawable.ic_account_circle_white_36dp,
            R.drawable.ic_info_white_36dp,
            R.drawable.ic_power_settings_new_white_36dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        authListen();
        setupList();
    }

    private void setupList() {
        SettingListAdapter adapter = new SettingListAdapter(SettingActivity.this, settingList, imageId);
        listView = (ListView)findViewById(R.id.setting_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 3 :
                        if (isAdmin) {
                            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        } else {
                            Toast.makeText(SettingActivity.this, "Hanya admin yang bisa masuk", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4 :
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        break;
                    case 5 :
                        mAuth.signOut();
                }
            }
        });
    }

    private void authListen() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                fireUser = FirebaseAuth.getInstance().getCurrentUser();
                if (fireUser != null) {
                    checkAdmin(fireUser);
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        };
    }

    private void checkAdmin(FirebaseUser fireUser) {
        userRef = FirebaseDatabase.getInstance().getReference("users").child(fireUser.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    user = dataSnapshot.getValue(User.class);
                    isAdmin = user.getType().equals("admin");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

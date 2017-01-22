package com.tolabulilm.jadwalkajian.setting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tolabulilm.jadwalkajian.R;

public class AboutActivity extends AppCompatActivity {

    private TextView aboutContent;
    private String content;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRef = FirebaseDatabase.getInstance().getReference("jadwal-kajian");
        initView();
        initDatabase();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        aboutContent = (TextView)findViewById(R.id.about_content);
    }

    private void initDatabase() {
        DatabaseReference aboutRef = mRef.child("about").child("content");
        ValueEventListener contentListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                content = dataSnapshot.getValue(String.class);
                aboutContent.setText(content);
                Toast.makeText(AboutActivity.this, dataSnapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        aboutRef.addListenerForSingleValueEvent(contentListener);
    }
}
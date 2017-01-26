package com.tolabulilm.jadwalkajian.setting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    private Button licenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRef = FirebaseDatabase.getInstance().getReference("jadwal-kajian");
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        aboutContent = (TextView)findViewById(R.id.about_content);
        licenseButton = (Button)findViewById(R.id.about_license_button);
        setContent();
    }

    //content tulisan di activity bisa diubah sesuai data di firebase
    private void setContent() {
        DatabaseReference aboutRef = mRef.child("about").child("content");
        aboutRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                content = dataSnapshot.getValue(String.class);
                aboutContent.setText(content);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void handleOnClick() {
        licenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to license activity
            }
        });
    }
}

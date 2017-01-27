package com.tolabulilm.jadwalkajian.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.tolabulilm.jadwalkajian.R;
import com.tolabulilm.jadwalkajian.kajian.AddKajianActivity;
import com.tolabulilm.jadwalkajian.user.AdminActivity;
import com.tolabulilm.jadwalkajian.user.LoginActivity;

public class SettingActivity extends AppCompatActivity {

    private ListView listView;
    private static final String[] settingList = {"Pengaturan","Saran","Admin","Tentang","Logout"};
    private static final int[] imageId = {
            R.drawable.ic_settings_applications_white_36dp,
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SettingListAdapter adapter = new SettingListAdapter(SettingActivity.this, settingList, imageId);
        listView = (ListView)findViewById(R.id.setting_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 2 : startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        break;
                    case 3 : startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        break;
                    case 4 :
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }

}

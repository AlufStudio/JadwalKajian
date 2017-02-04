package com.tolabulilm.jadwalkajian.setting.about;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.tolabulilm.jadwalkajian.R;

public class AboutActivity extends AppCompatActivity {

    private TextView aboutContent;
    private Button licenseButton;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        setSupportActionBar(toolbar);
        handleOnClick();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        aboutContent = (TextView)findViewById(R.id.about_content);
        licenseButton = (Button)findViewById(R.id.about_license_button);
    }

    private void handleOnClick() {
        licenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to license activity
                startActivity(new Intent(getApplicationContext(), LicenseActivity.class));
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

package com.tolabulilm.jadwalkajian.kajian;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tolabulilm.jadwalkajian.R;

public class AddKajianActivity extends AppCompatActivity {

    private EditText inputUstadz;
    private EditText inputTitle;
    private EditText inputPlace;
    private EditText inputAddress;
    private EditText inputHijri;
    private EditText inputContactNumber;

    private Button dateButton;
    private Button hourButton;

    private String ustadz;
    private String title;
    private String place;
    private String address;
    private String hijri;
    private String contactNumber;

    private boolean inputIsEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kajian);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void checkEmptyInput() {
        if(inputUstadz.getText().toString().isEmpty() || inputTitle.getText().toString().isEmpty()) {
            inputIsEmpty = true;
        } else if (inputPlace.getText().toString().isEmpty()) {
            inputIsEmpty = true;
        } else {
            inputIsEmpty = false;
        }
    }
}

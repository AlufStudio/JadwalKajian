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
    private Button submitButton;

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

    //pengecekan empty dibagi menjadi dua bagian yaitu input informasi dasar
    //dan input informasi tambahan
    private void checkEmptyInput() {
        if(inputUstadz.getText().toString().isEmpty() || inputTitle.getText().toString().isEmpty()) {
            inputIsEmpty = true;
        } else if (inputPlace.getText().toString().isEmpty()) {
            inputIsEmpty = true;
        } else {
            inputIsEmpty = false;
        }
    }

    //mengambil informasi dasar
    private void getPrimaryInputData() {
        ustadz = inputUstadz.getText().toString();
        title = inputTitle.getText().toString();
        place = inputPlace.getText().toString();
    }

    //mengambil informasi tambahan. harus dihandle jika tidak semua informasi diisi
    private void getAdditionalInputData() {
        address = inputAddress.getText().toString();
        hijri = inputHijri.getText().toString();
        contactNumber = inputContactNumber.getText().toString();
    }

    private void initView() {
        inputUstadz = (EditText)findViewById(R.id.kajian_input_ustadz);
        inputTitle = (EditText)findViewById(R.id.kajian_input_title);
        inputPlace = (EditText)findViewById(R.id.kajian_input_place);
        inputAddress = (EditText)findViewById(R.id.kajian_input_address);
        inputHijri = (EditText)findViewById(R.id.kajian_input_hijri);
        inputContactNumber = (EditText)findViewById(R.id.kajian_input_contactnumber);

        dateButton = (Button)findViewById(R.id.kajian_button_date);
        hourButton = (Button)findViewById(R.id.kajian_button_hour);
        submitButton = (Button)findViewById(R.id.kajian_button_submit);
    }

    private void handleOnClick() {
        //ketika submit minimal harus mengisi informasi dasar
        submitButton.sestOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //meminta input tanggal. tanggal tidak boleh kurang dari tanggal sekarang
        //untuk kajian rutin, boleh tidak menginput tanggal
        dateButton.sestOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //meminta input waktu
        hourButton.sestOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //back button kiri atas di toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

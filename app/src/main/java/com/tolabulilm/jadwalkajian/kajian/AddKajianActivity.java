package com.tolabulilm.jadwalkajian.kajian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tolabulilm.jadwalkajian.R;
import com.tolabulilm.jadwalkajian.user.AdminActivity;

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
    private Spinner spinnerKota;
    private Spinner spinnerTipe;
    private Toolbar toolbar;

    private Kajian kajian;
    private String idKajian;
    private String ustadz;
    private String title;
    private String place;
    private long time;
    private int city;
    private int type;
    private String address = "";
    private String hijri = "";
    private String contactNumber = "";
    private String status = "active";

    private FirebaseUser fireUser;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private boolean inputIsEmpty = true;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kajian);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        handleOnClick();
        authListen();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //pengecekan empty dibagi menjadi dua bagian yaitu input informasi dasar
    //dan input informasi tambahan
    private void checkEmptyInput() {
        if(inputUstadz.getText().toString().isEmpty() || inputTitle.getText().toString().isEmpty() ||
                inputPlace.getText().toString().isEmpty()) {
            inputIsEmpty = true;
        } else if (city == 0) {
            inputIsEmpty = true;
            Toast.makeText(this, "Belum memilih kota", Toast.LENGTH_SHORT).show();
        } else if (type == 0) {
            inputIsEmpty = true;
            Toast.makeText(this, "Belum memilih jenis kajian", Toast.LENGTH_SHORT).show();
        } else {
            inputIsEmpty = false;
        }
    }

    //mengambil informasi dasar
    private void getPrimaryInputData() {
        ustadz = inputUstadz.getText().toString();
        title = inputTitle.getText().toString();
        place = inputPlace.getText().toString();

        //city dan tipe
        type = spinnerTipe.getSelectedItemPosition();
        city = spinnerKota.getSelectedItemPosition();

        //tanggal dan jam
    }

    //mengambil informasi tambahan. harus dihandle jika tidak semua informasi diisi
    private void getAdditionalInputData() {
        if (!inputAddress.getText().toString().isEmpty()) {
            address = inputAddress.getText().toString();
        }
        if (!inputHijri.getText().toString().isEmpty()) {
            hijri = inputHijri.getText().toString();
        }
        if (!inputContactNumber.getText().toString().isEmpty()) {
            contactNumber = inputContactNumber.getText().toString();
        }
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
        spinnerKota = (Spinner)findViewById(R.id.kajian_spinner_kota);
        spinnerTipe = (Spinner)findViewById(R.id.kajian_spinner_tipe);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        progressBar = (ProgressBar)findViewById(R.id.kajian_progress_bar);
    }

    private void handleOnClick() {
        //ketika submit minimal harus mengisi informasi dasar
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmptyInput();
            }
        });

        //meminta input tanggal. tanggal tidak boleh kurang dari tanggal sekarang
        //untuk kajian rutin, boleh tidak menginput tanggal
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //meminta input waktu
        hourButton.setOnClickListener(new View.OnClickListener() {
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

        handleSpinner();
    }

    private void uploadKajianToDb(Kajian kajian, FirebaseUser fireUser) {
        DatabaseReference kajianRef = FirebaseDatabase.getInstance().getReference("admin").
                child(fireUser.getUid()).child(kajian.getId());
        kajianRef.setValue(kajian).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    toggleProgress();
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    NavUtils.navigateUpTo(AddKajianActivity.this, intent);
                    Toast.makeText(AddKajianActivity.this, "Jadwal kajian berhasil dibuat", Toast.LENGTH_SHORT).show();
                } else {
                    toggleProgress();
                    Toast.makeText(AddKajianActivity.this, "Jadwal kajian gagal dibuat", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //progress dimunculkan setelah klik submit dan dihilangkan setelah berhasil menambah jadwal
    private void toggleProgress() {
        if (submitButton.getVisibility() == View.VISIBLE) {
            submitButton.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            submitButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    //jika user admin tidak ada, maka button submit di disable
    private void authListen() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                fireUser = FirebaseAuth.getInstance().getCurrentUser();
                if (fireUser != null) {
                    toggleEnableButton(submitButton);
                } else {
                    toggleEnableButton(submitButton);
                }
            }
        };
    }

    private void toggleEnableButton(Button button) {
        if (button.isEnabled()) {
            button.setEnabled(false);
        } else {
            button.setEnabled(true);
        }
    }

    private void handleSpinner() {
        ArrayAdapter<CharSequence> adapterKota = ArrayAdapter.createFromResource(this,
                R.array.menu_kota, android.R.layout.simple_spinner_item);
        adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterTipe = ArrayAdapter.createFromResource(this,
                R.array.tipe_kajian, android.R.layout.simple_spinner_item);
        adapterTipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKota.setAdapter(adapterKota);
        spinnerTipe.setAdapter(adapterTipe);
    }

    private void showDatePicker(int year, int month, int day) {

    }

    private void showTimerPicker(int hour, int minute) {

    }
}

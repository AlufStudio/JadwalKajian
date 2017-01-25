package com.tolabulilm.jadwalkajian.kajian;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tolabulilm.jadwalkajian.R;
import com.tolabulilm.jadwalkajian.setting.SettingActivity;

public class SearchActivity extends AppCompatActivity {

    private Spinner spinnerKota;
    private Spinner spinnerTipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handleOnClick();
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //menu di toolbar kanan selalu ada di beberapa activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                goToSettingActivity();
                break;
        }
        return true;
    }

    //dibuat method sendiri agar lebih fleksibel jika membutuhkan intent
    //untuk activity tertentu
    private void goToSettingActivity() {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
    }

    private void initView() {
        spinnerKota = (Spinner)findViewById(R.id.search_spinner_kota);
        spinnerTipe = (Spinner)findViewById(R.id.search_spinner_tipe);
        ArrayAdapter<CharSequence> adapterKota = ArrayAdapter.createFromResource(this,
                R.array.menu_kota, android.R.layout.simple_spinner_item);
        adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterTipe = ArrayAdapter.createFromResource(this,
                R.array.tipe_kajian, android.R.layout.simple_spinner_item);
        adapterTipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKota.setAdapter(adapterKota);
        spinnerTipe.setAdapter(adapterTipe);
    }

    private void handleOnClick() {
        //back button di toolbar diklik agar setara dengan back button di bawah
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

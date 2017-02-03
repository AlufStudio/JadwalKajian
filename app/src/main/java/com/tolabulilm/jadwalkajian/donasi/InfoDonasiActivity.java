package com.tolabulilm.jadwalkajian.donasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tolabulilm.jadwalkajian.R;

public class InfoDonasiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference donasiRef;
    private FirebaseRecyclerAdapter firebaseAdapter;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_donasi);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initFirebase();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.info_donasi_recycler);
    }

    private void initFirebase() {
        donasiRef = FirebaseDatabase.getInstance().getReference("donasi");
        firebaseAdapter = new FirebaseRecyclerAdapter<Donasi, DonasiViewHolder> (
            Donasi.class,
            R.layout.card_donasi,
            DonasiViewHolder.class,
            donasiRef
        ) {
            @Override
            protected void populateViewHolder(DonasiViewHolder viewHolder, Donasi model, int position) {
                viewHolder.setView(model);
                viewHolder.setProgressBar(model.getDonated(), model.getNeeded());
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(firebaseAdapter);
    }
}

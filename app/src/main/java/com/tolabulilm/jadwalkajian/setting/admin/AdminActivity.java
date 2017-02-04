package com.tolabulilm.jadwalkajian.setting.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.tolabulilm.jadwalkajian.R;
import com.tolabulilm.jadwalkajian.kajian.Kajian;
import com.tolabulilm.jadwalkajian.kajian.KajianViewHolder;
import com.tolabulilm.jadwalkajian.setting.admin.input.AddKajianActivity;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String adminId;
    private DatabaseReference kajianRef;
    private FirebaseIndexRecyclerAdapter firebaseAdapter;
    private Query query;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddKajianActivity.class));
            }
        });

        displayKajian();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void displayKajian() {
        query = kajianRef.orderByChild("adminId").equalTo(adminId);
        firebaseAdapter = new FirebaseIndexRecyclerAdapter<Kajian, AdminViewHolder>(
                Kajian.class,
                R.layout.card_admin_kajian,
                AdminViewHolder.class,
                query,
                kajianRef
        ) {
            @Override
            protected void populateViewHolder(AdminViewHolder viewHolder, Kajian model, int position) {
                viewHolder.setView(model);
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(firebaseAdapter);
    }

    private void initView() {
        adminId = getIntent().getStringExtra("adminId");
        kajianRef = FirebaseDatabase.getInstance().getReference("kajian");

        recyclerView = (RecyclerView)findViewById(R.id.admin_recycler);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }
}

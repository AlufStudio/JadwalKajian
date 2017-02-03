package com.tolabulilm.jadwalkajian.donasi;

public class InfoDonasiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference donasiRef;
    private FirebaseRecyclerAdapter firebaseAdapter;

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
                viewHolder.setProgressBar(model.getDonated, model.getNeeded);
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(firebaseAdapter);
    }
}

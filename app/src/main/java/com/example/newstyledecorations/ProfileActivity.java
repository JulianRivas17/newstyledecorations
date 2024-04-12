package com.example.newstyledecorations;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstyledecorations.adapter.EventosAdapter;
import com.example.newstyledecorations.model.Eventos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ProfileActivity extends AppCompatActivity {
    RecyclerView mRecycler;
    EventosAdapter mAdapter;
    FirebaseFirestore miFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        miFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = miFirestore.collection("eventos");

        FirestoreRecyclerOptions<Eventos> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Eventos>()
                        .setQuery(query, Eventos.class)
                        .build();

        mAdapter = new EventosAdapter(firestoreRecyclerOptions, this);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}

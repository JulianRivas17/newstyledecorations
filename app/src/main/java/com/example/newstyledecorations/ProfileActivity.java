package com.example.newstyledecorations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstyledecorations.adapter.EventosAdapter;
import com.example.newstyledecorations.model.Eventos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ProfileActivity extends AppCompatActivity {
    RecyclerView mRecycler;
    EventosAdapter mAdapter;
    FirebaseFirestore miFirestore;
    FirebaseAuth mAuth;
    Button btn_return;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        miFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerView);
        btn_return = findViewById(R.id.btn_return);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userId =  user.getUid();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = miFirestore.collection("eventos").whereEqualTo("userId", userId);

        FirestoreRecyclerOptions<Eventos> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Eventos>()
                        .setQuery(query, Eventos.class)
                        .build();

        mAdapter = new EventosAdapter(firestoreRecyclerOptions, this);
        mRecycler.setAdapter(mAdapter);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
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

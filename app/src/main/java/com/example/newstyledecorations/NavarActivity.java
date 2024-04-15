package com.example.newstyledecorations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class NavarActivity extends AppCompatActivity {
    Button btn_return, btn_logout, btn_reservation;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbar);
        mAuth = FirebaseAuth.getInstance();

        btn_return = findViewById(R.id.btn_return);
        btn_logout = findViewById(R.id.btn_logout);
        btn_reservation = findViewById(R.id.btn_reservation);


        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavarActivity.this, MainActivity.class));
            }
        });

        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavarActivity.this, ProfileActivity.class));

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();;
                startActivity(new Intent(NavarActivity.this, LoginActivity.class));
            }
        });
    }
}

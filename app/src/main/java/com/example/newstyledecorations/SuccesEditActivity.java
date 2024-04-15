package com.example.newstyledecorations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SuccesEditActivity extends AppCompatActivity {

    Button id_redirect_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successful_edit);
        id_redirect_home = findViewById(R.id.id_redirect_home);
        id_redirect_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccesEditActivity.this, MainActivity.class));
            }
        });
    }
}

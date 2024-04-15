package com.example.newstyledecorations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SuccesEventActivity extends AppCompatActivity {
    Button btn_return;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.succes_event);
            btn_return = findViewById(R.id.btn_return);
            btn_return.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SuccesEventActivity.this, MainActivity.class));
                }
            });
        }
}


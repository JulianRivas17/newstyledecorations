package com.example.newstyledecorations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SuccesEditActivity extends AppCompatActivity {

    Button id_redirect_home, btn_confirm_event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successful_edit);
        id_redirect_home = findViewById(R.id.id_redirect_home);
        btn_confirm_event = findViewById(R.id.btn_confirm_event);
        id_redirect_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccesEditActivity.this, MainActivity.class));
            }
        });
        btn_confirm_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://wa.link/3wvwg5";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}

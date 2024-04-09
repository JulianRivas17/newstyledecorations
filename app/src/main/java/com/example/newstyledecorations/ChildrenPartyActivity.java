package com.example.newstyledecorations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChildrenPartyActivity extends AppCompatActivity {

    Button btn_save;
    EditText input_reason, input_name, input_decoration, input_type_light;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.children_party);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firestore = FirebaseFirestore.getInstance();

        input_name = findViewById(R.id.input_name);
        input_reason = findViewById(R.id.input_reason);
        input_decoration = findViewById(R.id.input_decoration);
        input_type_light = findViewById(R.id.input_type_light);
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input_name.getText().toString().trim();
                String motivo = input_reason.getText().toString().trim();
                String decoracion = input_decoration.getText().toString().trim();
                String tipoLuz = input_type_light.getText().toString().trim();

                if(name.isEmpty() && motivo.isEmpty() && decoracion.isEmpty() && tipoLuz.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postEvent(name, motivo,decoracion,tipoLuz);
                }
            }
        });
    }

    private void postEvent(String name, String motivo, String decoracion, String tipoLuz) {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("name", name);
        eventData.put("motivo", motivo);
        eventData.put("decoracion", decoracion);
        eventData.put("tipoLuz", tipoLuz);

        firestore.collection("eventos").add(eventData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Creado exitosamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error al ingresar", Toast.LENGTH_SHORT).show();
                        Log.e("ChildrenPartyActivity", "Error al ingresar evento: ", e);
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

}

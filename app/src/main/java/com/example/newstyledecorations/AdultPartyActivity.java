package com.example.newstyledecorations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newstyledecorations.adapter.EventosAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdultPartyActivity extends AppCompatActivity {

    Button btn_save, btn_return;
    EditText input_reason, input_name, input_decoration, input_type_light, input_date, input_time, input_age, cant_acces, input_cant_puff, input_colors, input_direction, input_location;
    Switch swith_candy, switch_ballons, switch_table, switch_lights, switch_puff, switch_photo, switch_recept, switch_trono;
    CheckBox check_cylinder, check_table, check_home, check_salon;
    private FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    String userId;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.adult_party);

        String id = getIntent().getStringExtra("id_eventos");

        //config bd
        firestore = FirebaseFirestore.getInstance();

        //inputs
        input_name = findViewById(R.id.input_name);
        input_reason = findViewById(R.id.input_reason);
        input_decoration = findViewById(R.id.input_decoration);
        input_type_light = findViewById(R.id.input_type_light);
        input_date = findViewById(R.id.input_date);
        input_time = findViewById(R.id.input_time);
        input_age = findViewById(R.id.input_age);
        input_colors = findViewById(R.id.input_colors);
        input_direction = findViewById(R.id.input_direction);
        input_location = findViewById(R.id.input_location);

        //input number
        cant_acces = findViewById(R.id.cant_acces);
        input_cant_puff = findViewById(R.id.input_cant_puff);

        //checkboxs
        check_cylinder = findViewById(R.id.check_cylinder);
        check_table = findViewById(R.id.check_table);
        check_home = findViewById(R.id.check_home);
        check_salon = findViewById(R.id.check_salon);

        //swiches
        swith_candy = findViewById(R.id.swith_candy);
        switch_ballons = findViewById(R.id.switch_ballons);
        switch_table = findViewById(R.id.switch_table);
        switch_lights = findViewById(R.id.switch_lights);
        switch_lights = findViewById(R.id.switch_lights);
        switch_puff = findViewById(R.id.switch_puff);
        switch_photo = findViewById(R.id.switch_photo);
        switch_recept = findViewById(R.id.switch_recept);
        switch_trono = findViewById(R.id.switch_trono);

        //Guardar
        btn_save = findViewById(R.id.btn_save);
        btn_return = findViewById(R.id.btn_return);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userId =  user.getUid();

        OnBackPressedDispatcher onBackPressedDispatcher = this.getOnBackPressedDispatcher();

        if (id == null || id == "") {
            onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    startActivity(new Intent(AdultPartyActivity.this, SelectPartyActivity.class));
                }
            });
            btn_return.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AdultPartyActivity.this, SelectPartyActivity.class));
                }
            });
            check_cylinder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_table.setChecked(false);
                    }
                }
            });

            check_table.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_cylinder.setChecked(false);
                    }
                }
            });

            check_home.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_salon.setChecked(false);
                    }
                }
            });

            check_salon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_home.setChecked(false);
                    }
                }
            });
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = input_name.getText().toString().trim();
                    String motivo = input_reason.getText().toString().trim();
                    String decoracion = input_decoration.getText().toString().trim();
                    String tipoLuz = input_type_light.getText().toString().trim();
                    String fecha = input_date.getText().toString().trim();
                    String hora = input_time.getText().toString().trim();
                    Integer edad = null;
                    String edadString = input_age.getText().toString().trim();
                    if (!edadString.isEmpty()) {
                        edad = Integer.parseInt(edadString);
                    } else {
                        edad = 0;
                    }

                    Integer cantAccsesorios = null;
                    String cantAccesoriosString = cant_acces.getText().toString().trim();
                    if (!cantAccesoriosString.isEmpty()) {
                        cantAccsesorios = Integer.parseInt(cantAccesoriosString);
                    } else {
                        cantAccsesorios = 0;
                    }

                    Integer cantPuff = null;
                    String cantPuffString = input_cant_puff.getText().toString().trim();
                    if (!cantPuffString.isEmpty()) {
                        cantPuff = Integer.parseInt(cantPuffString);
                    } else {
                        cantPuff = 0;
                    }

                    String colroes = input_colors.getText().toString().trim();
                    String inputDirection = input_direction.getText().toString().trim();
                    String inputLocation = input_location.getText().toString().trim();

                    boolean swithCandy = swith_candy.isChecked();
                    if (!swith_candy.isChecked()) {
                        swithCandy = false;
                    }
                    boolean switGlobos = switch_ballons.isChecked();
                    if (!switch_ballons.isChecked()) {
                        switGlobos = false;
                    }
                    boolean switMesas = switch_table.isChecked();
                    if (!switch_table.isChecked()) {
                        switMesas = false;
                    }
                    boolean mesaCilidro = check_cylinder.isChecked();
                    if (!check_cylinder.isChecked()) {
                        mesaCilidro = false;
                    }
                    boolean mesaComun = check_table.isChecked();
                    if (!check_table.isChecked()) {
                        mesaComun = false;
                    }
                    boolean swichtLuces = switch_lights.isChecked();
                    if (!switch_lights.isChecked()) {
                        swichtLuces = false;
                    }
                    boolean switchPuff = switch_puff.isChecked();
                    if (!switch_puff.isChecked()) {
                        switchPuff = false;
                    }
                    boolean switchPhoto = switch_photo.isChecked();
                    if (!switch_photo.isChecked()) {
                        switchPhoto = false;
                    }
                    boolean switchRecept = switch_recept.isChecked();
                    if (!switch_recept.isChecked()) {
                        switchRecept = false;
                    }
                    boolean switchTrono = switch_trono.isChecked();
                    if (!switch_trono.isChecked()) {
                        switchTrono = false;
                    }
                    boolean checkHome = check_home.isChecked();
                    if (!check_home.isChecked()) {
                        checkHome = false;
                    }
                    boolean checkSalon = check_salon.isChecked();
                    if (!check_salon.isChecked()) {
                        checkSalon = false;
                    }

                    if (name.isEmpty() && motivo.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        postEvent(name, motivo, decoracion, tipoLuz, fecha, hora, edad, cantAccsesorios, swithCandy, cantPuff,
                                switGlobos, switMesas, mesaCilidro, mesaComun, swichtLuces, switchPuff, switchPhoto, switchRecept,
                                switchTrono, colroes, inputDirection, inputLocation, checkHome, checkSalon);
                    }
                }
            });
        } else {
            onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    startActivity(new Intent(AdultPartyActivity.this, ProfileActivity.class));
                }
            });
            btn_return.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AdultPartyActivity.this, ProfileActivity.class));
                }
            });

            getEvent(id);
            String idEvent = id;

            check_cylinder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_table.setChecked(false);
                    }
                }
            });

            check_table.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_cylinder.setChecked(false);
                    }
                }
            });

            check_home.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_salon.setChecked(false);
                    }
                }
            });
            check_salon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        check_home.setChecked(false);
                    }
                }
            });
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = input_name.getText().toString().trim();
                    String motivo = input_reason.getText().toString().trim();
                    String decoracion = input_decoration.getText().toString().trim();
                    String tipoLuz = input_type_light.getText().toString().trim();
                    String fecha = input_date.getText().toString().trim();
                    String hora = input_time.getText().toString().trim();
                    Integer edad = null;
                    String edadString = input_age.getText().toString().trim();
                    if (!edadString.isEmpty()) {
                        edad = Integer.parseInt(edadString);
                    } else {
                        edad = 0;
                    }

                    Integer cantAccsesorios = null;
                    String cantAccesoriosString = cant_acces.getText().toString().trim();
                    if (!cantAccesoriosString.isEmpty()) {
                        cantAccsesorios = Integer.parseInt(cantAccesoriosString);
                    } else {
                        cantAccsesorios = 0;
                    }

                    Integer cantPuff = null;
                    String cantPuffString = input_cant_puff.getText().toString().trim();
                    if (!cantPuffString.isEmpty()) {
                        cantPuff = Integer.parseInt(cantPuffString);
                    } else {
                        cantPuff = 0;
                    }
                    String colroes = input_colors.getText().toString().trim();
                    String inputDirection = input_direction.getText().toString().trim();
                    String inputLocation = input_location.getText().toString().trim();

                    boolean swithCandy = swith_candy.isChecked();
                    if (!swith_candy.isChecked()) {
                        swithCandy = false;
                    }
                    boolean switGlobos = switch_ballons.isChecked();
                    if (!switch_ballons.isChecked()) {
                        switGlobos = false;
                    }
                    boolean switMesas = switch_table.isChecked();
                    if (!switch_table.isChecked()) {
                        switMesas = false;
                    }
                    boolean mesaCilidro = check_cylinder.isChecked();
                    if (!check_cylinder.isChecked()) {
                        mesaCilidro = false;
                    }
                    boolean mesaComun = check_table.isChecked();
                    if (!check_table.isChecked()) {
                        mesaComun = false;
                    }
                    boolean swichtLuces = switch_lights.isChecked();
                    if (!switch_lights.isChecked()) {
                        swichtLuces = false;
                    }
                    boolean switchPuff = switch_puff.isChecked();
                    if (!switch_puff.isChecked()) {
                        switchPuff = false;
                    }
                    boolean switchPhoto = switch_photo.isChecked();
                    if (!switch_photo.isChecked()) {
                        switchPhoto = false;
                    }
                    boolean switchRecept = switch_recept.isChecked();
                    if (!switch_recept.isChecked()) {
                        switchRecept = false;
                    }
                    boolean switchTrono = switch_trono.isChecked();
                    if (!switch_trono.isChecked()) {
                        switchTrono = false;
                    }
                    boolean checkHome = check_home.isChecked();
                    if (!check_home.isChecked()) {
                        checkHome = false;
                    }
                    boolean checkSalon = check_salon.isChecked();
                    if (!check_salon.isChecked()) {
                        checkSalon = false;
                    }

                    if (name.isEmpty() && motivo.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        updateEvent(idEvent, name, motivo, decoracion, tipoLuz, fecha, hora, edad, cantAccsesorios, swithCandy, cantPuff,
                                switGlobos, switMesas, mesaCilidro, mesaComun, swichtLuces, switchPuff, switchPhoto, switchRecept,
                                switchTrono, colroes, inputDirection, inputLocation, checkHome, checkSalon);
                    }
                }
            });
        }


    }

    private void updateEvent(String id, String name, String motivo, String decoracion, String tipoLuz, String fecha, String hora, Integer edad,
                             Integer cantAccsesorios, boolean swithCandy, Integer cantPuff, boolean switGlobos, boolean switMesas, boolean mesaCilidro,
                             boolean mesaComun, boolean swichtLuces, boolean switchPuff, boolean switchPhoto, boolean switchRecept, boolean switchTrono,
                             String colroes, String inputDirection, String inputLocation, boolean checkHome, boolean checkSalon) {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("nombre", name);
        eventData.put("motivo", motivo);
        eventData.put("decoracion", decoracion);
        eventData.put("tipoLuz", tipoLuz);
        eventData.put("fecha", fecha);
        eventData.put("hora", hora);
        eventData.put("edad", edad);
        eventData.put("cantidad_accsesorios", cantAccsesorios);
        eventData.put("candy_bar", swithCandy);
        eventData.put("cant_puff", cantPuff);
        eventData.put("globos", switGlobos);
        eventData.put("mesas_decorativas", switMesas);
        eventData.put("mesas_cilindro", mesaCilidro);
        eventData.put("mesas_comun", mesaComun);
        eventData.put("luces", swichtLuces);
        eventData.put("puff", switchPuff);
        eventData.put("fotos", switchPhoto);
        eventData.put("recepción", switchRecept);
        eventData.put("trono", switchTrono);
        eventData.put("colores", colroes);
        eventData.put("direccion", inputDirection);
        eventData.put("Localidad", inputLocation);
        eventData.put("casa", checkHome);
        eventData.put("salon", checkSalon);
        eventData.put("userId", userId);


        firestore.collection("eventos").document(id).update(eventData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Editado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdultPartyActivity.this, SuccesEditActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "Error al editar el evento con ID: " + id, e); // Se agrega el ID al mensaje de log
                Toast.makeText(getApplicationContext(), "Error al editar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void postEvent(String name, String motivo, String decoracion, String tipoLuz, String fecha, String hora, Integer edad,
                           Integer cantAccsesorios, boolean swithCandy, Integer cantPuff, boolean switGlobos, boolean switMesas, boolean mesaCilidro,
                           boolean mesaComun, boolean swichtLuces, boolean switchPuff, boolean switchPhoto, boolean switchRecept, boolean switchTrono,
                           String colroes, String inputDirection, String inputLocation, boolean checkHome, boolean checkSalon) {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("nombre", name);
        eventData.put("motivo", motivo);
        eventData.put("decoracion", decoracion);
        eventData.put("tipoLuz", tipoLuz);
        eventData.put("fecha", fecha);
        eventData.put("hora", hora);
        eventData.put("edad", edad);
        eventData.put("cantidad_accsesorios", cantAccsesorios);
        eventData.put("candy_bar", swithCandy);
        eventData.put("cant_puff", cantPuff);
        eventData.put("globos", switGlobos);
        eventData.put("mesas_decorativas", switMesas);
        eventData.put("mesas_cilindro", mesaCilidro);
        eventData.put("mesas_comun", mesaComun);
        eventData.put("luces", swichtLuces);
        eventData.put("puff", switchPuff);
        eventData.put("fotos", switchPhoto);
        eventData.put("recepción", switchRecept);
        eventData.put("trono", switchTrono);
        eventData.put("colores", colroes);
        eventData.put("direccion", inputDirection);
        eventData.put("Localidad", inputLocation);
        eventData.put("casa", checkHome);
        eventData.put("salon", checkSalon);
        eventData.put("userId", userId);


        firestore.collection("eventos").add(eventData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdultPartyActivity.this, SuccesEventActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
                        Log.e("ChildrenPartyActivity", "Error al ingresar evento: ", e);
                    }
                });
    }

    private void getEvent(String id) {
        String idUser = userId;
        firestore.collection("eventos").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombre = documentSnapshot.getString("nombre");
                String motivo = documentSnapshot.getString("motivo");
                String decoracion = documentSnapshot.getString("decoracion");
                String tipoLuz = documentSnapshot.getString("tipoLuz");
                String fecha = documentSnapshot.getString("fecha");
                String hora = documentSnapshot.getString("hora");
                Long edad = documentSnapshot.getLong("edad");
                Long cantidadAccesorios = documentSnapshot.getLong("cantidad_accsesorios");
                boolean candyBar = documentSnapshot.getBoolean("candy_bar");
                Long cantidadPuff = documentSnapshot.getLong("cant_puff");
                boolean globos = documentSnapshot.getBoolean("globos");
                boolean mesasDecorativas = documentSnapshot.getBoolean("mesas_decorativas");
                boolean mesaCilindro = documentSnapshot.getBoolean("mesas_cilindro");
                boolean mesaComun = documentSnapshot.getBoolean("mesas_comun");
                boolean luces = documentSnapshot.getBoolean("luces");
                boolean puff = documentSnapshot.getBoolean("puff");
                boolean fotos = documentSnapshot.getBoolean("fotos");
                boolean recepcion = documentSnapshot.getBoolean("recepción");
                boolean trono = documentSnapshot.getBoolean("trono");
                String colores = documentSnapshot.getString("colores");
                String direccion = documentSnapshot.getString("direccion");
                String localidad = documentSnapshot.getString("Localidad");
                boolean casa = documentSnapshot.getBoolean("casa");
                boolean salon = documentSnapshot.getBoolean("salon");

                input_name.setText(nombre);
                input_reason.setText(motivo);
                input_decoration.setText(decoracion);
                input_type_light.setText(tipoLuz);
                input_date.setText(fecha);
                input_time.setText(hora);
                input_colors.setText(colores);
                input_direction.setText(direccion);
                input_location.setText(localidad);

                //input number
                if (cantidadAccesorios != null) {
                    cant_acces.setText(cantidadAccesorios.toString());
                }
                if (cantidadPuff != null) {
                    input_cant_puff.setText(cantidadPuff.toString());
                }
                if (edad != null) {
                    input_age.setText(edad.toString());
                }
                //checkboxs
                check_cylinder.setChecked(mesaCilindro);
                check_table.setChecked(mesaComun);
                check_home.setChecked(casa);
                check_salon.setChecked(salon);

                //swiches
                swith_candy.setChecked(candyBar);
                switch_ballons.setChecked(globos);
                switch_table.setChecked(mesasDecorativas);
                switch_lights.setChecked(luces);
                switch_puff.setChecked(puff);
                switch_photo.setChecked(fotos);
                switch_recept.setChecked(recepcion);
                switch_trono.setChecked(trono);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Eror al obtener los datos", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

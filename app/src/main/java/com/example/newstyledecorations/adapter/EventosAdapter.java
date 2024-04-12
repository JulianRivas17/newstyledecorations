package com.example.newstyledecorations.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstyledecorations.AdultPartyActivity;
import com.example.newstyledecorations.ChildrenPartyActivity;
import com.example.newstyledecorations.R;
import com.example.newstyledecorations.model.Eventos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventosAdapter extends FirestoreRecyclerAdapter<Eventos, EventosAdapter.ViewHolder> {

    private FirebaseFirestore mFirebase = FirebaseFirestore.getInstance();
    Activity activity;

    public EventosAdapter(@NonNull FirestoreRecyclerOptions<Eventos> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Eventos eventos) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAbsoluteAdapterPosition());
        final String id = documentSnapshot.getId();

        viewHolder.motivo.setText(eventos.getMotivo());
        viewHolder.fecha.setText(eventos.getFecha());
        viewHolder.hora.setText(eventos.getHora());

        viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.collection("eventos").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            if (documentSnapshot.contains("casa")) {
                                Intent intent = new Intent(activity, AdultPartyActivity.class);
                                intent.putExtra("id_eventos", id);
                                activity.startActivity(intent);
                            } else {
                                Intent intent = new Intent(activity, ChildrenPartyActivity.class);
                                intent.putExtra("id_eventos", id);
                                activity.startActivity(intent);
                            }
                        } else {
                            Toast.makeText(activity, "No se encontró el evento", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Error al cargar el evento", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEventos(id);
            }
        });
    }

    private void deleteEventos(String id) {
        mFirebase.collection("eventos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado correctamenete", Toast.LENGTH_SHORT).show();;
            }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(activity, "Error al eliminar", Toast.LENGTH_SHORT).show();;
                }
            });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView motivo, fecha, hora;
        ImageView btn_delete, btn_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            motivo = itemView.findViewById(R.id.motivo);
            fecha = itemView.findViewById(R.id.fecha);
            hora = itemView.findViewById(R.id.hora);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }
}

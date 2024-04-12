package com.example.newstyledecorations.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstyledecorations.R;
import com.example.newstyledecorations.model.Eventos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EventosAdapter extends FirestoreRecyclerAdapter<Eventos, EventosAdapter.ViewHolder> { // Corrección aquí

    public EventosAdapter(@NonNull FirestoreRecyclerOptions<Eventos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Eventos eventos) {
        viewHolder.motivo.setText(eventos.getMotivo());
        viewHolder.fecha.setText(eventos.getFecha());
        viewHolder.hora.setText(eventos.getHora());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView motivo, fecha, hora;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            motivo = itemView.findViewById(R.id.motivo);
            fecha = itemView.findViewById(R.id.fecha);
            hora = itemView.findViewById(R.id.hora);
        }
    }
}

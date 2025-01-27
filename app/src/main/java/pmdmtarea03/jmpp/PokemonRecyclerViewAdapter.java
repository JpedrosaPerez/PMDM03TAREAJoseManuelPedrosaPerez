package pmdmtarea03.jmpp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pmdmtarea03.jmpp.databinding.ItemCardviewBinding;

public class PokemonRecyclerViewAdapter extends RecyclerView.Adapter<Pokemon_ViewHolder> {
    private final List<PokemonItem> pokemons;
    private final Context context;

    public PokemonRecyclerViewAdapter(List<PokemonItem> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public Pokemon_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardviewBinding binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Pokemon_ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Pokemon_ViewHolder holder, int position) {
        PokemonItem pokemon=this.pokemons.get(position);
        holder.bind(pokemon);
        holder.itemView.setOnClickListener(v -> {
            // Llamar al método del listener cuando se haga clic
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(pokemon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public interface OnItemClickListener {
        void onItemClick(PokemonItem pokemon);
    }

    // En el adaptador, agrega un setter para el listener
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}

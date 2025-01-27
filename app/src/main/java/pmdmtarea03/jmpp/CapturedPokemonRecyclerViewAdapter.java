package pmdmtarea03.jmpp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pmdmtarea03.jmpp.databinding.PokemonCapturadoCardviewBinding;

public class CapturedPokemonRecyclerViewAdapter extends RecyclerView.Adapter<CapturadosViewHolder> {
    private final List<PokemonItem> pokemons;
    private final Context context;

    public CapturedPokemonRecyclerViewAdapter(List<PokemonItem> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public CapturadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonCapturadoCardviewBinding binding = PokemonCapturadoCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CapturadosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CapturadosViewHolder holder, int position) {
        PokemonItem pokemon = this.pokemons.get(position);
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

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}

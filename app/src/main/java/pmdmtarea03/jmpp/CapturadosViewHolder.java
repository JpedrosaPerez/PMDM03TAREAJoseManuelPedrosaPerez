package pmdmtarea03.jmpp;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import pmdmtarea03.jmpp.databinding.PokemonCapturadoCardviewBinding;

public class CapturadosViewHolder extends RecyclerView.ViewHolder {
    private final PokemonCapturadoCardviewBinding binding;

    public CapturadosViewHolder(PokemonCapturadoCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(PokemonItem pokemon) {

        binding.nombreCapturado.setText(pokemon.getName());
        binding.indice.setText(String.valueOf(pokemon.getPokedexId()));
        binding.peso.setText(String.valueOf(pokemon.getHeight()));
        binding.altura.setText(String.valueOf(pokemon.getWeight()));
        // Aquí puedes cargar la imagen del Pokémon, por ejemplo:
        String imageUrl = pokemon.getSprites() != null ? pokemon.getSprites().getFront_default() : null;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(binding.getRoot())
                    .load(imageUrl)
                    .placeholder(R.drawable.ajustes)  // Imagen mientras carga
                    .error(R.drawable.ajustes)        // Imagen si falla la carga
                    .into(binding.imagen);
        }

    }
}
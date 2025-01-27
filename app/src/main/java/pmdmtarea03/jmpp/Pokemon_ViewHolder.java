package pmdmtarea03.jmpp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pmdmtarea03.jmpp.databinding.ItemCardviewBinding;

public class Pokemon_ViewHolder extends RecyclerView.ViewHolder {
    private final ItemCardviewBinding binding;

    public Pokemon_ViewHolder( ItemCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(PokemonItem pokemon){
        binding.nombre.setText(pokemon.getName());
        //binding.imagen.setImageResource(pokemon.getImagen());
        binding.executePendingBindings();
    }
}

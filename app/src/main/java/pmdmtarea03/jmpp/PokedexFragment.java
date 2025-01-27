package pmdmtarea03.jmpp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexFragment extends Fragment {
    private PokemonRecyclerViewAdapter adapter;
    private final List<PokemonItem> pokemonList = new ArrayList<>(); // Lista inicial

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View rootView = inflater.inflate(R.layout.pokedex_fragment, container, false);

        // Configurar RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerPokemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonRecyclerViewAdapter(pokemonList, getContext());
        recyclerView.setAdapter(adapter);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/") // Base URL de PokeAPI
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService service = retrofit.create(PokeApiService.class);

        // Llamada a la API para obtener la lista de Pokémon (nombre y url)
        Call<PokemonResponse> call = service.getPokemon();
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonList.clear();
                    pokemonList.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Error en la respuesta de la API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Error al cargar Pokémon", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el click listener en el adapter para cargar detalles cuando se haga click
        adapter.setOnItemClickListener(pokemon -> {
            // Al hacer clic en un Pokémon, obtener los detalles usando su URL
            obtenerDetallesPokemon(pokemon.getUrl());
        });

        return rootView;
    }
    private void obtenerDetallesPokemon(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/") // Base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService service = retrofit.create(PokeApiService.class);

        // Llamada al endpoint del Pokémon usando su URL
        Call<PokemonCapturado> call = service.getPokemonDetalles(url);
        call.enqueue(new Callback<PokemonCapturado>() {
            @Override
            public void onResponse(Call<PokemonCapturado> call, Response<PokemonCapturado> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonCapturado pokemon = response.body();
                    // Ahora tienes todos los detalles del Pokémon, puedes guardarlo en Firebase
                    guardarPokemonEnFirebase(pokemon);
                } else {
                    Toast.makeText(getContext(), "Error en la respuesta de la API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonCapturado> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Error al cargar detalles del Pokémon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarPokemonEnFirebase(PokemonCapturado pokemon) {
        // Guardar solo el nombre y la URL por ahora
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pokemons")
                .add(pokemon)
                .addOnSuccessListener(documentReference ->
                        Toast.makeText(getContext(), "¡Pokémon guardado!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}

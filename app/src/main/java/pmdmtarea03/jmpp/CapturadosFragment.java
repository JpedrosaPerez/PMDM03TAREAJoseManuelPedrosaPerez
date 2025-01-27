package pmdmtarea03.jmpp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import pmdmtarea03.jmpp.databinding.CapturedFragmentBinding;

public class CapturadosFragment extends Fragment {

    private CapturedFragmentBinding binding;
    private RecyclerView recyclerView;
    private CapturedPokemonRecyclerViewAdapter adapter;

    // Lista de Pokémon capturados
    private List<PokemonItem> capturadosList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout usando DataBinding
        binding = CapturedFragmentBinding.inflate(inflater, container, false);

        // Inicializar el RecyclerView desde el binding
        recyclerView = binding.recyclerPokemonCaptured;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Aquí puedes cargar la lista de Pokémon capturados desde Firebase
        loadCapturedPokemons();

        // Configurar el adaptador
        adapter = new CapturedPokemonRecyclerViewAdapter(capturadosList, getContext());
        recyclerView.setAdapter(adapter);

        return binding.getRoot(); // Retorna la raíz del layout inflado
    }

    // Método para cargar los Pokémon capturados desde Firebase
    private void loadCapturedPokemons() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pokemons")  // Nombre de la colección de Pokémon en Firestore
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            capturadosList.clear();  // Limpiar la lista antes de llenarla
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                // Obtener los datos de cada Pokémon desde el documento
                                String name = document.getString("name");
                                String imageUrl = document.getString("imageUrl");
                                int weight = document.getLong("weight").intValue();
                                int height = document.getLong("height").intValue();
                                int pokedexId = document.getLong("pokedexId").intValue();
                                String frontDefaultImageUrl = document.getString("sprites.front_default");
                                // Crear un nuevo objeto PokemonItem

                                PokemonItem pokemonItem = new PokemonItem();
                                PokemonItem.Sprites sprites=new PokemonItem.Sprites();
                                pokemonItem.setName(name);
                                pokemonItem.setWeight(weight);
                                pokemonItem.setHeight(height);
                                pokemonItem.setPokedexId(pokedexId);
                                sprites.setFront_default(frontDefaultImageUrl);  // Asignar la URL recuperada
                                pokemonItem.setSprites(sprites);  // Asignar el objeto Sprites a PokemonItem


                                // Añadir el Pokémon a la lista
                                capturadosList.add(pokemonItem);
                            }
                            // Notificar al adaptador que los datos han cambiado
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        // Si ocurre un error al obtener los datos
                        Log.w("CapturadosFragment", "Error getting documents.", task.getException());
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Limpiar el binding cuando el fragmento sea destruido
    }
}
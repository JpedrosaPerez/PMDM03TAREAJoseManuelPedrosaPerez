package pmdmtarea03.jmpp;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface PokeApiService {
    @GET("pokemon")
    Call<PokemonResponse> getPokemon(); // Para obtener la lista inicial con nombre y URL

    @GET
    Call<PokemonCapturado> getPokemonDetalles(@Url String url); // Para obtener los detalles usando la URL del Pokémon
}

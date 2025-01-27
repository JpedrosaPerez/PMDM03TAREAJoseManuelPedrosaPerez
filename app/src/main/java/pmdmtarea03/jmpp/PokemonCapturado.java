package pmdmtarea03.jmpp;

public class PokemonCapturado {
    private String name;
    private String url;
    private String imageUrl;
    private int weight;      // Peso
    private int height;      // Altura
    private int pokedexId;
    private Sprites sprites;

    public Sprites getSprites() {
        return sprites;
    }
    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
    public static class Sprites {
        private String front_default;

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }

        public String getFront_default() {
            return front_default;
        }
    }
    // Getters y setters
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(int pokedexId) {
        this.pokedexId = pokedexId;
    }


}

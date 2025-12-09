package edu.kirkwood.model.json;

import java.util.List;

public class PokemonSpecies {
    private String name;
    private List<PokedexNumber> pokedex_numbers;
    // private List<PokemonForms> varieties;
    public String getName() {
        return name;
    }
    public List<PokedexNumber> getPokedex_numbers() {
        return pokedex_numbers;
    }
    // public List<PokemonForms> getVarieties() {
    //     return varieties;
    // }
}

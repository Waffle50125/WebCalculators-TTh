package edu.kirkwood.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.kirkwood.model.Pokemon;
import edu.kirkwood.model.json.PokemonSpecies;
import edu.kirkwood.model.json.PokemonSpeciesList;
import edu.kirkwood.model.json.SpeciesListResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PokemonDAO {
    // The Url to get every Pokemon species... at least until they get to 10,000 Pokemon
    private final String apiUrl = "https://pokeapi.co/api/v2/pokemon-species/?limit=151";

    public PokemonDAO(){}

    private List<PokemonSpeciesList> buildSpeciesList(){
        List<PokemonSpeciesList> list = new ArrayList<>();

        String rawData = getRawData(apiUrl);
        Gson gson = new GsonBuilder().create();
        SpeciesListResponse speciesListResponse = null;

        try {
            speciesListResponse = gson.fromJson(rawData, SpeciesListResponse.class);
            speciesListResponse.getResults().forEach(result -> {
                list.add(result);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    private String getRawData(String url){
        String data = "";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try {
            data = client.newCall(request).execute().body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    private List<PokemonSpecies> buildSpecies(){
        List<PokemonSpecies> list = new ArrayList<>();

        List<PokemonSpeciesList> urlList = buildSpeciesList();
        Gson gson = new GsonBuilder().create();
        try {
            for (PokemonSpeciesList pokemonSpeciesList : urlList) {
                try {
                String speciesResponse = getRawData(pokemonSpeciesList.getUrl());
                list.add(gson.fromJson(speciesResponse, PokemonSpecies.class));
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve pokemon data");
        }

        return list;
    }

    private List<Pokemon> buildPokemonList(){
        List<Pokemon> results = new ArrayList<>();

        List<PokemonSpecies> data = buildSpecies();
        for (PokemonSpecies species : data) {
            Pokemon pokemon = new Pokemon();
            pokemon.setName(species.getName());
            pokemon.setPokedexNumber(Integer.parseInt(species.getPokedex_numbers().get(0).getEntry_number()));
            results.add(pokemon);
        }

        return results;
    }

    public List<Pokemon> getResults(String name){
        List<Pokemon> results = new ArrayList<>();

        List<Pokemon> fullList = buildPokemonList();
        for (Pokemon pokemon : fullList) {
            if (pokemon.getName().contains(name)) {
                results.add(pokemon);
            }
        }

        return results;
    }
}

package edu.kirkwood.model;

public class Pokemon implements Comparable<Pokemon>{
    private int pokedexNumber;
    private String name;

    public Pokemon(){}

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Pokemon [pokedexNumber=" + pokedexNumber + ", name=" + name +"]";
    }

    @Override
    public int compareTo(Pokemon o) {
        return pokedexNumber - o.pokedexNumber;
    }

}

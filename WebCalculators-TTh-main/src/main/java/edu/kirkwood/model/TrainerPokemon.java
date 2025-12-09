package edu.kirkwood.model;

import org.jetbrains.annotations.NotNull;

public class TrainerPokemon implements Comparable<TrainerPokemon>{
    private int id;
    private String speciesName;
    private String move1;
    private String move2;
    private String move3;
    private String move4;
    private String nickname;

    public TrainerPokemon(String nickname, String move4, String move3, String move2, String move1, String speciesName, int id) {
        this.nickname = nickname;
        this.move4 = move4;
        this.move3 = move3;
        this.move2 = move2;
        this.move1 = move1;
        this.speciesName = speciesName;
        this.id = id;
    }

    public TrainerPokemon() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getMove1() {
        return move1;
    }

    public void setMove1(String move1) {
        this.move1 = move1;
    }

    public String getMove2() {
        return move2;
    }

    public void setMove2(String move2) {
        this.move2 = move2;
    }

    public String getMove3() {
        return move3;
    }

    public void setMove3(String move3) {
        this.move3 = move3;
    }

    public String getMove4() {
        return move4;
    }

    public void setMove4(String move4) {
        this.move4 = move4;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        if (nickname == null) {
            return speciesName;
        } else {
            return nickname + " the " + speciesName;
        }
    }

    @Override
    public int compareTo(@NotNull TrainerPokemon o) {
        if (this.getNickname() == null && o.getNickname() != null) {
            return 1; // these two if statements should push non-nicknamed Pokemon to the bottom of the list
        } else if (this.getNickname() != null && o.getNickname() == null){
            return -1;
        } else if (this.getNickname() == null && o.getNickname() == null){
            return this.getSpeciesName().compareTo(o.getSpeciesName());
        } else {
            return this.getNickname().compareTo(o.getNickname());
        }
    }
}

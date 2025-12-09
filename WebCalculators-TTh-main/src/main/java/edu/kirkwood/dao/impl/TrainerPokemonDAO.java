package edu.kirkwood.dao.impl;

import edu.kirkwood.model.TrainerPokemon;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.dao.MySQLConnection.getConnection;

public class TrainerPokemonDAO {

    public static List<TrainerPokemon> search(String name) {
        List<TrainerPokemon> results = new ArrayList<>();
        // try-with-resources
        try(Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall("{call sp_search_for_trainer_pokemon(?)}");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                TrainerPokemon trainerPokemon = new TrainerPokemon();
                trainerPokemon.setId(resultSet.getInt("TrainerPokemonID"));
                trainerPokemon.setSpeciesName(resultSet.getString("SpeciesName"));
                trainerPokemon.setNickname(resultSet.getString("Nickname"));
                trainerPokemon.setMove1(resultSet.getString("Move1"));
                trainerPokemon.setMove2(resultSet.getString("Move2"));
                trainerPokemon.setMove3(resultSet.getString("Move3"));
                trainerPokemon.setMove4(resultSet.getString("Move4"));

                results.add(trainerPokemon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}

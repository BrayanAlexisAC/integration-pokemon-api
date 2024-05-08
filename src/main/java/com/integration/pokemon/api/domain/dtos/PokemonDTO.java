package com.integration.pokemon.api.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class PokemonDTO implements Serializable {

    private Integer id;
    private String name;
    private String locationAreaEncounters;
    private Integer baseExperience;
    private ArrayList<Object> abilities;
    private ArrayList<Object> heldItems;

}

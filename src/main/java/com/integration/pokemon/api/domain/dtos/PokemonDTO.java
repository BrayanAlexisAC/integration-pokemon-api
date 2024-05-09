package com.integration.pokemon.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class PokemonDTO implements Serializable {

    private Integer id;
    private String name;
    @JsonProperty("location_area_encounters")
    private String locationAreaEncounters;
    @JsonProperty("base_experience")
    private Integer baseExperience;
    @JsonProperty("abilities")
    private ArrayList<Object> abilities;
    @JsonProperty("held_items")
    private ArrayList<Object> heldItems;

}

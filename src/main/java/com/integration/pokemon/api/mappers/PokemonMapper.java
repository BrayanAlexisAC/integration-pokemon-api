package com.integration.pokemon.api.mappers;

import com.integration.pokemon.api.domain.dtos.PokemonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper(componentModel = "spring", imports = ArrayList.class)
public interface PokemonMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java((Integer) pokemon.get(\"id\"))"),
            @Mapping(target = "name", expression = "java((String) pokemon.get(\"name\"))"),
            @Mapping(target = "locationAreaEncounters", expression = "java((String) pokemon.get(\"location_area_encounters\"))"),
            @Mapping(target = "baseExperience", expression = "java((Integer) pokemon.get(\"base_experience\"))"),
            @Mapping(target = "abilities", expression = "java((ArrayList<Object>) pokemon.get(\"abilities\"))"),
            @Mapping(target = "heldItems", expression = "java((ArrayList<Object>) pokemon.get(\"held_items\"))")
    })
    PokemonDTO toPokemonDTO(HashMap<String, Object> pokemon);

}

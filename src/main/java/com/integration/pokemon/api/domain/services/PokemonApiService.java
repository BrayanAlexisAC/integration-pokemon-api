package com.integration.pokemon.api.domain.services;

import com.integration.pokemon.api.domain.dtos.ability.AbilityDTO;
import com.integration.pokemon.api.domain.dtos.pokemon.PokemonDTO;

public interface PokemonApiService {

    PokemonDTO getPokemon(String name);

    AbilityDTO getAbility(String url);

}

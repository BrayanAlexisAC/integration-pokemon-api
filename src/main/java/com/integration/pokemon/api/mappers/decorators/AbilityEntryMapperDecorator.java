package com.integration.pokemon.api.mappers.decorators;

import com.integration.pokemon.api.Constants;
import com.integration.pokemon.api.domain.dtos.pokemon.PokemonAbilityEntryDTO;
import com.integration.pokemon.api.mappers.AbilityEntryMapper;
import com.integration.pokemon.api.persistence.services.PokemonApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import services.brayan.pokemon_api_integration.Ability;

public class AbilityEntryMapperDecorator implements AbilityEntryMapper {

    @Autowired
    @Qualifier("delegate")
    private AbilityEntryMapper mapper;

    @Autowired
    private PokemonApiService pokemonApiService;

    @Override
    public Ability toAbility(PokemonAbilityEntryDTO abilityEntry) {
        var ability = mapper.toAbility(abilityEntry);
        var abilityInf = pokemonApiService.getAbility(abilityEntry.getUrl());
        var lstAbilityEn = abilityInf.getEffectEntries().stream().filter(effectEntriesDTO ->
					effectEntriesDTO.getLanguage().getName().equalsIgnoreCase(Constants.DEFAULT_LANGUAGE_EN)).toList();

        if (!lstAbilityEn.isEmpty()){
            ability.setEffect(lstAbilityEn.get(0).getEffect());
        }

        return ability;
    }
}

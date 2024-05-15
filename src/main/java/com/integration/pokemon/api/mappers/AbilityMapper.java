package com.integration.pokemon.api.mappers;

import com.integration.pokemon.api.domain.dtos.pokemon.PokemonAbilityDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import services.brayan.pokemon_api_integration.Abilities;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AbilityEntryMapper.class})
public interface AbilityMapper {

    @Mappings({
            @Mapping(target = "hidden", source = "isHidden"),
            @Mapping(target = ".", source = "slot"),
            @Mapping(target = ".", source = "ability")
    })
    Abilities toAbilities(PokemonAbilityDTO pokemonAbility);

    @InheritInverseConfiguration
    List<Abilities> toLstAbilities(List<PokemonAbilityDTO> lstPokemonAbility);

}

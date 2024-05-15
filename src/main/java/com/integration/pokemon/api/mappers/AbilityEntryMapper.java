package com.integration.pokemon.api.mappers;

import com.integration.pokemon.api.domain.dtos.pokemon.PokemonAbilityEntryDTO;
import com.integration.pokemon.api.mappers.decorators.AbilityEntryMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import services.brayan.pokemon_api_integration.Ability;

@Mapper(componentModel = "spring")
@DecoratedWith(AbilityEntryMapperDecorator.class)
public interface AbilityEntryMapper {

    @Mappings({
            @Mapping(target = ".", source = "name"),
            @Mapping(target = "effect", ignore = true)
    })
    Ability toAbility(PokemonAbilityEntryDTO abilityEntry);

}

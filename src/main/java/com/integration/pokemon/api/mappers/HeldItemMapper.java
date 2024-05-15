package com.integration.pokemon.api.mappers;

import com.integration.pokemon.api.domain.dtos.heldItem.HeldItemDTO;
import com.integration.pokemon.api.mappers.decorators.HeldItemMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import services.brayan.pokemon_api_integration.HeldItem;

@Mapper(componentModel = "spring")
@DecoratedWith(HeldItemMapperDecorator.class)
public interface HeldItemMapper {

    @Mappings({
            @Mapping(target = ".", source = "id"),
            @Mapping(target = ".", source = "name"),
            @Mapping(target = ".", source = "cost"),
            @Mapping(target = ".", source = "flingPower"),
            @Mapping(target = "category", source = "category.name"),
            @Mapping(target = "effect", ignore = true)
    })
    HeldItem toHeldItem(HeldItemDTO heldItemDTO);
}

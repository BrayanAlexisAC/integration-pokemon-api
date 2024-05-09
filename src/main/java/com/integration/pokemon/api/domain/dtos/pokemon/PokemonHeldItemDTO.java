package com.integration.pokemon.api.domain.dtos.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PokemonHeldItemDTO {
    @JsonProperty("item")
    private PokemonHeldItemEntryDTO item;
}

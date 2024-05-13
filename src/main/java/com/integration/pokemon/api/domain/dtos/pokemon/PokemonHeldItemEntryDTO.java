package com.integration.pokemon.api.domain.dtos.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PokemonHeldItemEntryDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
}

package com.integration.pokemon.api.domain.dtos.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PokemonAbilityDTO {
    @JsonProperty("is_hidden")
    private Boolean isHidden;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("ability")
    private PokemonAbilityEntryDTO ability;

}

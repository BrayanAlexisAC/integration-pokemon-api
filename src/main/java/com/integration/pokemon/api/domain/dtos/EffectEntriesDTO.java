package com.integration.pokemon.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EffectEntriesDTO {
    private String effect;
    @JsonProperty("short_effect")
    private String shortEffect;
    @JsonProperty("language")
    private LanguageDTO language;
}

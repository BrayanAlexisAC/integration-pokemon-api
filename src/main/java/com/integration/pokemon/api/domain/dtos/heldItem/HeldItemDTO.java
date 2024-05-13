package com.integration.pokemon.api.domain.dtos.heldItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.integration.pokemon.api.domain.dtos.ability.EffectEntriesDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HeldItemDTO {
    private Integer id;
    private String name;
    private Integer cost;
    @JsonProperty("fling_power")
    private Integer flingPower;
    @JsonProperty("category")
    private ItemCategory category;
    @JsonProperty("effect_entries")
    private List<EffectEntriesDTO> effectEntries;
}

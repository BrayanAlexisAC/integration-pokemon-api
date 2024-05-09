package com.integration.pokemon.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class AbilityDTO {

    private Integer id;
    private String name;
    @JsonProperty("effect_entries")
    private ArrayList<Object> effectEntries;

}

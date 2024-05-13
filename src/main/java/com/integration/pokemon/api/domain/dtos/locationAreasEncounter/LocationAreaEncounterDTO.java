package com.integration.pokemon.api.domain.dtos.locationAreasEncounter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationAreaEncounterDTO {
    @JsonProperty("location_area")
    private LocationAreaDTO locationArea;
}

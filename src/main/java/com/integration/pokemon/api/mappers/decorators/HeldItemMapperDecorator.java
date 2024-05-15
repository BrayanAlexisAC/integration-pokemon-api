package com.integration.pokemon.api.mappers.decorators;

import com.integration.pokemon.api.Constants;
import com.integration.pokemon.api.domain.dtos.heldItem.HeldItemDTO;
import com.integration.pokemon.api.mappers.HeldItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import services.brayan.pokemon_api_integration.HeldItem;

public class HeldItemMapperDecorator implements HeldItemMapper {

    @Autowired
    @Qualifier("delegate")
    private HeldItemMapper mapper;

    @Override
    public HeldItem toHeldItem(HeldItemDTO heldItemDTO) {
        var heldItem = mapper.toHeldItem(heldItemDTO);

        var lstEffectEn = heldItemDTO.getEffectEntries().stream().filter(data ->
                data.getLanguage().getName().equalsIgnoreCase(Constants.DEFAULT_LANGUAGE_EN)).toList();

        if (!lstEffectEn.isEmpty()) {
            heldItem.setEffect(lstEffectEn.get(0).getEffect());
        }

        return heldItem;
    }
}

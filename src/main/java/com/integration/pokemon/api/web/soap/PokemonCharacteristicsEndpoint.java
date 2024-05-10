package com.integration.pokemon.api.web.soap;

import com.integration.pokemon.api.Constants.SoapService;
import com.integration.pokemon.api.domain.services.PokemonApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.brayan.pokemon_api_integration.*;

@Endpoint
public class PokemonCharacteristicsEndpoint {

	@Autowired
	private PokemonApiService pokemonApiService;

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = "getPokemonAbilitiesRequest")
	@ResponsePayload
	public GetPokemonAbilitiesResponse getAbilities(@RequestPayload GetPokemonAbilitiesRequest request) {
		var response = new GetPokemonAbilitiesResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());

        pokemon.getAbilities().forEach(abilities -> {
            var soapAbilities = new Abilities();
            soapAbilities.setSlot(abilities.getSlot());
			soapAbilities.setHidden(abilities.getIsHidden());

			var soapAbility = new Ability();
			soapAbility.setName(abilities.getAbility().getName());

			var abilityInf = pokemonApiService.getAbility(abilities.getAbility().getUrl());
			var lstAbilityEn = abilityInf.getEffectEntries().stream().filter(effectEntriesDTO ->
					effectEntriesDTO.getLanguage().getName().equalsIgnoreCase("en")).toList();

			if (!lstAbilityEn.isEmpty()){
				soapAbility.setEffect(lstAbilityEn.get(0).getEffect());
			}

			soapAbilities.setAbility(soapAbility);
			response.getAbilities().add(soapAbilities);
		});

		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = "getPokemonBaseExperienceRequest")
	@ResponsePayload
	public GetPokemonBaseExperienceResponse getBaseExperience(@RequestPayload GetPokemonBaseExperienceRequest request) {
		var response = new GetPokemonBaseExperienceResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setBaseExperience(pokemon.getBaseExperience());

		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = "getPokemonHeldItemsRequest")
	@ResponsePayload
	public GetPokemonHeldItemsResponse getHeldItems(@RequestPayload GetPokemonHeldItemsRequest request) {
		var response = new GetPokemonHeldItemsResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());

		var lstHeldItems = pokemon.getHeldItems().stream().map(pokemonHeldItemDTO -> {
			var heldItemDTO = pokemonApiService.getHeldItem(pokemonHeldItemDTO.getItem().getUrl());
			HeldItem heldItem = new HeldItem();
			heldItem.setId(heldItemDTO.getId());
			heldItem.setName(heldItemDTO.getName());
			heldItem.setCost(heldItemDTO.getCost());
			heldItem.setFlingPower(heldItemDTO.getFlingPower());
			heldItem.setCategory(heldItemDTO.getCategory().getName());

			var lstEffectEn = heldItemDTO.getEffectEntries().stream().filter(data ->
					data.getLanguage().getName().equalsIgnoreCase("en")).toList();

			if (!lstEffectEn.isEmpty()) {
				heldItem.setEffect(lstEffectEn.get(0).getEffect());
			}

			return heldItem;
		}).toList();

		response.getHeldItems().addAll(lstHeldItems);

		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = "getPokemonIdRequest")
	@ResponsePayload
	public GetPokemonIdResponse getId(@RequestPayload GetPokemonIdRequest request) {
		var response = new GetPokemonIdResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setId(pokemon.getId());
		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = "getPokemonLocationAreaEncountersRequest")
	@ResponsePayload
	public GetPokemonLocationAreaEncountersResponse getLocationAreasEncounters(@RequestPayload GetPokemonLocationAreaEncountersRequest request) {
		var response = new GetPokemonLocationAreaEncountersResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		var lstLocationAreasEncounter = pokemonApiService.getLocationAreas(pokemon.getLocationAreaEncounters());
		var lstLocationAreas = lstLocationAreasEncounter.stream().map(locationArea ->
				locationArea.getLocationArea().getName()).toList();

		response.getLocation().addAll(lstLocationAreas);

		return response;
	}
}

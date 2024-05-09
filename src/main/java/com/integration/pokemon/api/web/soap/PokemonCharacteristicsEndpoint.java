package com.integration.pokemon.api.web.soap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.pokemon.api.Constants.SoapService;
import com.integration.pokemon.api.domain.services.PokemonApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.brayan.pokemon_api_integration.*;

import java.util.HashMap;

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
			var mapAbilities = (HashMap<String, Object>) abilities;
            var soapAbilities = new Abilities();
            soapAbilities.setSlot((Integer) mapAbilities.get("slot"));
			soapAbilities.setHidden((Boolean) mapAbilities.get("is_hidden"));

			var mapAbility = ((HashMap<String, String>) mapAbilities.get("ability"));
			var soapAbility = new Ability();
			soapAbility.setName(mapAbility.get("name"));
			soapAbilities.setAbility(soapAbility);

			var abilityInf = pokemonApiService.getAbility(mapAbility.get("url"));
			var lstAbilityEn = abilityInf.getEffectEntries().stream().filter(effectEntriesDTO ->
					effectEntriesDTO.getLanguage().getName().equalsIgnoreCase("en")).toList();

			if (!lstAbilityEn.isEmpty()){
				soapAbility.setEffect(lstAbilityEn.get(0).getEffect());
			}

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

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = "getPokemonIdRequest")
	@ResponsePayload
	public GetPokemonIdResponse getBaseExperience(@RequestPayload GetPokemonIdRequest request) {
		var response = new GetPokemonIdResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setId(pokemon.getId());
		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = "getPokemonLocationAreaEncountersRequest")
	@ResponsePayload
	public GetPokemonLocationAreaEncountersResponse getBaseExperience(@RequestPayload GetPokemonLocationAreaEncountersRequest request) {
		var response = new GetPokemonLocationAreaEncountersResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setUrl(pokemon.getLocationAreaEncounters());
		return response;
	}
}

package com.integration.pokemon.api.web.soap;

import com.integration.pokemon.api.domain.services.PokemonApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.brayan.pokemon_api_integration.*;

@Endpoint
public class PokemonCharacteristicsEndpoint {
	private static final String NAMESPACE_URI = "http://brayan.services/pokemon-api-integration";

	@Autowired
	private PokemonApiService pokemonApiService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonAbilitiesRequest")
	@ResponsePayload
	public GetPokemonAbilitiesResponse getAbilities(@RequestPayload GetPokemonAbilitiesRequest request) {
		var response = new GetPokemonAbilitiesResponse();

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonBaseExperienceRequest")
	@ResponsePayload
	public GetPokemonBaseExperienceResponse getBaseExperience(@RequestPayload GetPokemonBaseExperienceRequest request) {
		var response = new GetPokemonBaseExperienceResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setBaseExperience(pokemon.getBaseExperience());

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonIdRequest")
	@ResponsePayload
	public GetPokemonIdResponse getBaseExperience(@RequestPayload GetPokemonIdRequest request) {
		var response = new GetPokemonIdResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setId(pokemon.getId());
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonLocationAreaEncountersRequest")
	@ResponsePayload
	public GetPokemonLocationAreaEncountersResponse getBaseExperience(@RequestPayload GetPokemonLocationAreaEncountersRequest request) {
		var response = new GetPokemonLocationAreaEncountersResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setUrl(pokemon.getLocationAreaEncounters());
		return response;
	}
}

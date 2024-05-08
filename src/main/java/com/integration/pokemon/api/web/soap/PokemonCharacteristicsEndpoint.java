package com.integration.pokemon.api.web.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.brayan.pokemon_api_integration.GetPokemonAbilitiesRequest;
import services.brayan.pokemon_api_integration.GetPokemonAbilitiesResponse;
import services.brayan.pokemon_api_integration.GetPokemonBaseExperienceRequest;
import services.brayan.pokemon_api_integration.GetPokemonBaseExperienceResponse;

@Endpoint
public class PokemonCharacteristicsEndpoint {
	private static final String NAMESPACE_URI = "http://brayan.services/pokemon-api-integration";

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
		response.setBaseExperience(64.0);

		return response;
	}
}

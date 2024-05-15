package com.integration.pokemon.api.web.soap;

import com.integration.pokemon.api.Constants;
import com.integration.pokemon.api.Constants.SoapService;
import com.integration.pokemon.api.mappers.AbilityMapper;
import com.integration.pokemon.api.mappers.HeldItemMapper;
import com.integration.pokemon.api.persistence.entity.SoapServicesHistoryEntity;
import com.integration.pokemon.api.persistence.services.PokemonApiService;
import com.integration.pokemon.api.persistence.services.SoapServicesHistoryService;
import io.micrometer.common.util.StringUtils;
import jakarta.xml.ws.WebServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.brayan.pokemon_api_integration.*;

@Endpoint
public class PokemonCharacteristicsEndpoint {
	private final static Logger LOG = LoggerFactory.getLogger(PokemonCharacteristicsEndpoint.class);

	@Autowired
	private PokemonApiService pokemonApiService;

	@Autowired
	private SoapServicesHistoryService soapServicesHistoryService;

	@Autowired
	private AbilityMapper abilityMapper;

	@Autowired
	private HeldItemMapper heldItemMapper;

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = SoapService.GET_POKEMON_ABILITIES_REQUEST)
	@ResponsePayload
	public GetPokemonAbilitiesResponse getAbilities(@RequestPayload GetPokemonAbilitiesRequest request) {
		saveRequest(request, SoapService.GET_POKEMON_ABILITIES_REQUEST);
		var response = new GetPokemonAbilitiesResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.getAbilities().addAll(abilityMapper.toLstAbilities(pokemon.getAbilities()));

		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = SoapService.GET_POKEMON_BASE_EXPERIENCE_REQUEST)
	@ResponsePayload
	public GetPokemonBaseExperienceResponse getBaseExperience(@RequestPayload GetPokemonBaseExperienceRequest request) {
		saveRequest(request, SoapService.GET_POKEMON_BASE_EXPERIENCE_REQUEST);
		var response = new GetPokemonBaseExperienceResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setBaseExperience(pokemon.getBaseExperience());

		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = SoapService.GET_POKEMON_HELD_ITEMS_REQUEST)
	@ResponsePayload
	public GetPokemonHeldItemsResponse getHeldItems(@RequestPayload GetPokemonHeldItemsRequest request) {
		saveRequest(request, SoapService.GET_POKEMON_HELD_ITEMS_REQUEST);
		var response = new GetPokemonHeldItemsResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());

		var lstHeldItems = pokemon.getHeldItems().stream().map(pokemonHeldItemDTO -> {
			var heldItemDTO = pokemonApiService.getHeldItem(pokemonHeldItemDTO.getItem().getUrl());
			return heldItemMapper.toHeldItem(heldItemDTO);
		}).toList();

		response.getHeldItems().addAll(lstHeldItems);

		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = SoapService.GET_POKEMON_ID_REQUEST)
	@ResponsePayload
	public GetPokemonIdResponse getId(@RequestPayload GetPokemonIdRequest request) {
		saveRequest(request, SoapService.GET_POKEMON_ID_REQUEST);
		var response = new GetPokemonIdResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		response.setId(pokemon.getId());
		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart = SoapService.GET_POKEMON_NAME_REQUEST)
	@ResponsePayload
	public GetPokemonNameResponse getName(@RequestPayload GetPokemonNameRequest request) {
		saveRequest(request, SoapService.GET_POKEMON_NAME_REQUEST);
		var response = new GetPokemonNameResponse();
		try {
			var pokemon = pokemonApiService.getPokemon(request.getName());
			response.setName(pokemon.getName());
		} catch (RuntimeException e){
			LOG.warn("Pokemon does not exist");
		}
		return response;
	}

	@PayloadRoot(namespace = SoapService.DEFAULT_INTEGRATION_POKE_URI, localPart =SoapService.GET_POKEMON_LOCATION_AREA_ENCOUNTERS_REQUEST)
	@ResponsePayload
	public GetPokemonLocationAreaEncountersResponse getLocationAreasEncounters(@RequestPayload GetPokemonLocationAreaEncountersRequest request) {
		saveRequest(request, SoapService.GET_POKEMON_LOCATION_AREA_ENCOUNTERS_REQUEST);
		var response = new GetPokemonLocationAreaEncountersResponse();
		var pokemon = pokemonApiService.getPokemon(request.getName());
		var lstLocationAreasEncounter = pokemonApiService.getLocationAreas(pokemon.getLocationAreaEncounters());
		var lstLocationAreas = lstLocationAreasEncounter.stream().map(locationArea ->
				locationArea.getLocationArea().getName()).toList();

		response.getLocation().addAll(lstLocationAreas);

		return response;
	}

	// Temporally method while create an interceptor with custom parameter in header
	private void saveRequest(GeneralRequest request, String method) {
		if (StringUtils.isNotBlank(request.getIpOrigin()) && request.getIpOrigin().matches(Constants.Regex.IPV4)) {
			var isTrackingSaved = soapServicesHistoryService.save(
					new SoapServicesHistoryEntity(0,
							StringUtils.isNotBlank(request.getIpOrigin()) ? request.getIpOrigin() : Constants.SoapService.DEFAULT_IP_ORIGIN,
							method)
			);

			if (isTrackingSaved){
				LOG.info("Track Created, Method:{}", method);
			} else {
				LOG.warn("Error to created tracking, Method:{}", method);
			}
		} else {
			throw new WebServiceException("ipOrigin cannot be empty or it is not valid");
		}
	}
}

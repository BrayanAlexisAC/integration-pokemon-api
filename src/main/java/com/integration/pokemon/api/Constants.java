package com.integration.pokemon.api;

public class Constants {
    public static final String EMPTY_URL = "/";
    public static final String DEFAULT_LANGUAGE_EN = "en";

    public static class PokemonAPI {
        public static final String URL = "service.pokemon.api.url";
    }

    public static class Regex {
        public static final String IPV4 = "^\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b$";
    }

    public static class SoapService {
        public static final String DEFAULT_INTEGRATION_POKE_URI = "http://brayan.services/pokemon-api-integration";
        public static final String DEFAULT_IP_ORIGIN = "127.0.0.1";

        // SCHEMAS
        public static final String SCHEMA_POKEMON = "xsd-schemas/pokemon.xsd";

        // METHODS POKEMON API
        public static final String GET_POKEMON_ABILITIES_REQUEST = "getPokemonAbilitiesRequest";
        public static final String GET_POKEMON_BASE_EXPERIENCE_REQUEST = "getPokemonBaseExperienceRequest";
        public static final String GET_POKEMON_HELD_ITEMS_REQUEST = "getPokemonHeldItemsRequest";
        public static final String GET_POKEMON_ID_REQUEST = "getPokemonIdRequest";
        public static final String GET_POKEMON_NAME_REQUEST = "getPokemonNameRequest";
        public static final String GET_POKEMON_LOCATION_AREA_ENCOUNTERS_REQUEST = "getPokemonLocationAreaEncountersRequest";

    }

}

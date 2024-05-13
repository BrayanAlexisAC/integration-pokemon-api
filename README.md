# integration-pokemon-api
This repository focus to integrate with pokemon api (https://pokeapi.co/api/v2/pokemon/), official site [Pokemon API](https://pokeapi.co/)

# Requirements
- Java 17
- Basic knowledge command line (Convert commands to own Operating System, Based on Linux / MacOs)
- SOAPUI or equivalent tool to consumes SOAP Services

# Libraries
- Spring Web
- Spring Web Services
- Spring JPA
- Lombook
- Mssql driver

# Instructions
- Download Repository **[Master Branch]**
- run command -> **./gradlew genJaxb**, This will create objects for correct functionality with SOAP webservices
- run command -> **./gradlew bootRun**, This will turn on the server in the port:9090 (Stage environment) **[DEFAULT]**
- Get the WSDL -> **{{host}}**/integration-poke-**{{profile}}**/api/ws/pokemon.wsdl
- Execute one of different methods available

# WSDLs
- [DEV](http://localhost:8080/integration-poke-dev/api/ws/pokemon.wsdl)
- [STG](http://localhost:9090/integration-poke-stg/api/ws/pokemon.wsdl) **[DEFAULT]**

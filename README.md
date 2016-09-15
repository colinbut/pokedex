# Pokedex

## Pokemon Index

This is an application developed using Dropwizard. It represents the famous 'Pokedex' which is an index of Pokemons (Pocket Monsters). 
This pokedex is the original pokedex consisting of 150 pokemon. Mew the 151st is therefore not included.

### Build 

```
mvn clean package
```

### Running

```
java -jar target/pokedex-1.0-SNAPSHOT.jar server src/main/resources/pokedex.yaml
```

note that:

`server` is the command to indicate starting this application as a HTTP Server. (Jetty HTTP Server is started up)
`src/main/resources/pokedex.yaml` is the application configuration file in yaml (yml) format


### Key Technologies

- Dropwizard
    - Jersey (JAX-RS)
    - Jackson JSON
    - Jetty
    - JDBI
    - SLF4J/Logback

# Pokedex

## Pokemon Index

This is an application developed using Dropwizard. It represents the famous 'Pokedex' which is an index of Pokemons (Pocket Monsters). 
This pokedex is the original pokedex consisting of 150 pokemon. Mew the 151st is therefore not included.

![Image of Pokemon]
(etc/pokemon.jpg)

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
    - Liquibase

### Dropwizard Modules

- dropwizard-core
- dropwizard-jdbi
- dropwizard-migrations

### Database

Uses MySQL database. You will need to have MySQL in your local. Download it from the MySQL website and follow its installation instructions to install it. 

### Liquibase

Database management is done using Liquibase patches. The patches live under the following directory location:
`src/main/resources/liquibase/`

where migrations.xml contains list of individual patches to run.

Run the following command to update the database:

```
java -jar target/pokedex-1.0-SNAPSHOT.jar db migrate src/main/resources/pokedex.yaml
```

and to rollback:
```
java -jar target/pokedex-1.0-SNAPSHOT.jar db rollback src/main/resources/pokedex.yaml --count=4
```

where `--count` tag indicates number of patches to rollback




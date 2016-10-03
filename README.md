# Pokedex

**DISCLAIMER: All data in this project are fictional**

## Pokemon Index

This is an application developed using Dropwizard. It represents the famous 'Pokedex' which is an index of Pokemons (Pocket Monsters). 
This pokedex is the original pokedex consisting of 150 pokemon. Mew the 151st is therefore not included.

![Image of Pokemon]
(etc/pokemon.jpg)

What this web application does is that you hit the specified endpoint and it will return the pokemon data containing its name, combat power, hit points, list of attacks in JSON format. You give the id of the pokemon you want to retreive and it will return the pokemon data by the given id.

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

### Hitting the endpoint

http://localhost:8080/pokemon/25

Note that 25 is the pokemon index of Pikachu :heart_eyes:

```JSON
{  
   "id":25,
   "name":"Pikachu",
   "hitPoints":61,
   "combatPower":777,
   "type":{  
      "typeName":"ELECTRIC"
   },
   "attacks":[  
      {  
         "name":"Thunder Shock",
         "power":40,
         "accuracy":100,
         "typeRepresentation":{  
            "typeName":"ELECTRIC"
         }
      },
      {  
         "name":"Thunderbolt",
         "power":90,
         "accuracy":100,
         "typeRepresentation":{  
            "typeName":"ELECTRIC"
         }
      }
   ]
}
```


### Key Technologies

- Dropwizard
    - Jersey (JAX-RS)
    - Jackson JSON
    - Jetty
    - JDBI
    - SLF4J/Logback
    - Liquibase
    - Freemarker
    - Mustache

### Dropwizard Modules

- dropwizard-core
- dropwizard-jdbi
- dropwizard-migrations
- dropwizard-views-freemarker
- dropwizard-views-mustache

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

### View

Freemarker/Mustache template engine is used to render views of the resources in a nicely representation for display.









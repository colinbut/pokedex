package com.mycompany.pokdedex;

import com.mycompany.pokdedex.db.PokemonDao;
import com.mycompany.pokdedex.resources.PokemonResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class PokedexApplication extends Application<PokedexConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PokedexApplication().run(args);
    }

    @Override
    public String getName() {
        return "Pokedex";
    }

    @Override
    public void initialize(final Bootstrap<PokedexConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final PokedexConfiguration configuration,
                    final Environment environment) {

        final DBIFactory dbiFactory = new DBIFactory();
        final DBI dbi = dbiFactory.build(environment, configuration.getDatasourceFactory(), "mysql");
        final PokemonDao pokemonDao = dbi.onDemand(PokemonDao.class);

        environment.jersey().register(new PokemonResource());


    }

}

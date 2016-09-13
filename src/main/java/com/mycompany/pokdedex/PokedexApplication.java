package com.mycompany.pokdedex;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: implement application
    }

}

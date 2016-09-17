package com.mycompany.pokdedex;

import com.mycompany.pokdedex.common.PokemonRepresentationDomainTransformer;
import com.mycompany.pokdedex.core.service.AttackService;
import com.mycompany.pokdedex.core.service.AttackServiceImpl;
import com.mycompany.pokdedex.core.service.PokemonAttackService;
import com.mycompany.pokdedex.core.service.PokemonAttackServiceImpl;
import com.mycompany.pokdedex.core.service.PokemonService;
import com.mycompany.pokdedex.core.service.PokemonServiceImpl;
import com.mycompany.pokdedex.core.service.TypeService;
import com.mycompany.pokdedex.core.service.TypeServiceImpl;
import com.mycompany.pokdedex.db.AttackDao;
import com.mycompany.pokdedex.db.PokemonAttackDao;
import com.mycompany.pokdedex.db.PokemonDao;
import com.mycompany.pokdedex.db.TypeDao;
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
        final DBI dbi = dbiFactory.build(environment, configuration.getDatabase(), "mysql");

        // initialise (load) DAOs
        final PokemonDao pokemonDao = dbi.onDemand(PokemonDao.class);
        final TypeDao typeDao = dbi.onDemand(TypeDao.class);
        final AttackDao attackDao = dbi.onDemand(AttackDao.class);
        final PokemonAttackDao pokemonAttackDao = dbi.onDemand(PokemonAttackDao.class);

        // manual Dependency Injection (for now at least)
        final TypeService typeService = new TypeServiceImpl(typeDao);
        final AttackService attackService = new AttackServiceImpl(attackDao, typeService);
        final PokemonAttackService pokemonAttackService = new PokemonAttackServiceImpl(pokemonAttackDao);
        final PokemonService pokemonService = new PokemonServiceImpl(pokemonDao, attackService, typeService, pokemonAttackService);

        // register!
        environment.jersey().register(new PokemonResource(pokemonService));


    }

}

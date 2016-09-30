package com.mycompany.pokdedex;

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
import com.mycompany.pokdedex.resources.PokemonApiResource;
import com.sun.javafx.collections.MappingChange;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;

import java.util.Map;

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
        bootstrap.addBundle(new MigrationsBundle<PokedexConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(PokedexConfiguration pokedexConfiguration) {
                return pokedexConfiguration.getDatabase();
            }
        });

        bootstrap.addBundle(new ViewBundle<PokedexConfiguration>());
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
        environment.jersey().register(new PokemonApiResource(pokemonService));

    }

}

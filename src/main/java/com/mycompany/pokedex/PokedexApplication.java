package com.mycompany.pokedex;


import com.mycompany.pokedex.db.hibernate.dao.AttackDaoHibernate;
import com.mycompany.pokedex.db.hibernate.dao.PokemonDaoHibernate;
import com.mycompany.pokedex.db.jdbi.AttackDaoJDBI;
import com.mycompany.pokedex.db.jdbi.PokemonAttackDaoJDBI;
import com.mycompany.pokedex.db.jdbi.PokemonDaoJDBI;
import com.mycompany.pokedex.db.jdbi.TypeDaoJDBI;
import com.mycompany.pokedex.db.hibernate.entity.AttackEntity;
import com.mycompany.pokedex.db.hibernate.entity.PokemonEntity;
import com.mycompany.pokedex.db.hibernate.entity.PokemonTypeEntity;
import com.mycompany.pokedex.health.ApplicationHealthCheck;
import com.mycompany.pokedex.health.DatabaseHealthCheck;
import com.mycompany.pokedex.resources.PokemonApiResource;
import com.mycompany.pokedex.resources.PokemonViewResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;

public class PokedexApplication extends Application<PokedexConfiguration> {

    // declaring bundles here since especially the hibernate bundle is being referenced in the
    // Application#run method

    /**
     * The hibernate bundle
     */
    private final HibernateBundle<PokedexConfiguration> hibernateBundle = new HibernateBundle<PokedexConfiguration>(PokemonEntity.class,
        AttackEntity.class,
        PokemonTypeEntity.class) {

        @Override
        public PooledDataSourceFactory getDataSourceFactory(PokedexConfiguration pokedexConfiguration) {
            return pokedexConfiguration.getDatabase();
        }
    };

    private MigrationsBundle<PokedexConfiguration> migrationsBundle = new MigrationsBundle<PokedexConfiguration>() {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(PokedexConfiguration pokedexConfiguration) {
            return pokedexConfiguration.getDatabase();
        }
    };


    @Override
    public String getName() {
        return "Pokedex";
    }

    @Override
    public void initialize(final Bootstrap<PokedexConfiguration> bootstrap) {
        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<PokedexConfiguration>());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/fonts", "/fonts", null, "fonts"));
    }

    @Override
    public void run(final PokedexConfiguration configuration,
                    final Environment environment) {

        final DBIFactory dbiFactory = new DBIFactory();
        final DBI dbi = dbiFactory.build(environment, configuration.getDatabase(), "mysql");

        // initialise (load) JDBI DAOs
        final PokemonDaoJDBI pokemonDaoJDBI = dbi.onDemand(PokemonDaoJDBI.class);
        final TypeDaoJDBI typeDaoJDBI = dbi.onDemand(TypeDaoJDBI.class);
        final AttackDaoJDBI attackDaoJDBI = dbi.onDemand(AttackDaoJDBI.class);
        final PokemonAttackDaoJDBI pokemonAttackDaoJDBI = dbi.onDemand(PokemonAttackDaoJDBI.class);

        // initialise (load) Hibernate DAOs
        final PokemonDaoHibernate pokemonDaoHibernate = new PokemonDaoHibernate(hibernateBundle.getSessionFactory());
        final AttackDaoHibernate attackDaoHibernate = new AttackDaoHibernate(hibernateBundle.getSessionFactory()); // don't think we need this

        environment.jersey().setUrlPattern("/api/*");

        // register healthchecks!
        environment.healthChecks().register("database", new DatabaseHealthCheck(dbi, configuration.getDatabase().getValidationQuery()));
        environment.healthChecks().register("application", new ApplicationHealthCheck());

        // register application resources!
        // manual Dependency Injection (for now at least)
        environment.jersey().register(new PokemonApiResource(pokemonDaoJDBI, pokemonDaoHibernate, typeDaoJDBI, attackDaoJDBI, pokemonAttackDaoJDBI, configuration.getDataAccess()));
        environment.jersey().register(new PokemonViewResource(pokemonDaoJDBI, pokemonDaoHibernate));
    }


    public static void main(final String[] args) throws Exception {
        new PokedexApplication().run(args);
    }

}

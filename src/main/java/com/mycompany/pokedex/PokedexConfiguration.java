package com.mycompany.pokedex;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.pokedex.core.constants.DataAccessMethod;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PokedexConfiguration extends Configuration {

    @NotEmpty
    private String dataAccess = DataAccessMethod.JDBI;

    @NotNull
    @Valid
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDatabase() {
        return database;
    }

    @JsonProperty
    public String getDataAccess() {
        return dataAccess;
    }

    @JsonProperty
    public void setDataAccess(String dataAccess) {
        this.dataAccess = dataAccess;
    }

}

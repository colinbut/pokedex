/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.health;

import com.codahale.metrics.health.HealthCheck;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class DatabaseHealthCheck extends HealthCheck {

    private final DBI dbi;
    private final String validationQuery;

    public DatabaseHealthCheck(DBI dbi, String validationQuery) {
        this.dbi = dbi;
        this.validationQuery = validationQuery;
    }

    @Override
    protected Result check() throws Exception {
        try {
            Handle handle = dbi.open();
            handle.execute(validationQuery);
            return Result.healthy();
        } catch (Exception e) {
            return Result.unhealthy("DB unhealthy");
        }
    }
}

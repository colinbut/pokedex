/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.hibernate.dao;

import com.mycompany.pokedex.db.hibernate.entity.AttackEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class AttackDaoHibernate extends AbstractDAO<AttackEntity> {

    public AttackDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public AttackEntity fetch(int id) {
        return get(id);
    }

    public List<AttackEntity> fetchList() {
        return list(namedQuery("findAllAttacks"));
    }


}

/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.core.service;

import com.mycompany.pokedex.core.domain.Type;

public interface TypeService {

    Type getTypeById(int id);

    Type getTypeByName(String name);

}

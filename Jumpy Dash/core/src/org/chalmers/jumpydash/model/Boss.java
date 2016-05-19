package org.chalmers.jumpydash.model;

import org.chalmers.jumpydash.physics.IJDBody;

/**
 * Created by Johannes on 2016-05-19.
 */
public class Boss extends Enemy{

    public Boss(IJDBody jdbody){
        super(jdbody,10,2);
    }
}

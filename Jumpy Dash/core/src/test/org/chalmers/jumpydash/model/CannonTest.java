package org.chalmers.jumpydash.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Johannes on 2016-05-28.
 */
public class CannonTest {
    private Cannon cannon;
    private Cannon cannon2;

    @Before
    public void setUp(){
        cannon = new Cannon();
        cannon2 = new Cannon();
    }

    /*
    First tests if the cannon is allowed to fire, which should be allowed. Then test if it is allowed
    to fire again, which should not be allowed. After that the timer for firing is reset to 0.
    After that it tests if the cannon is allowed to fire after timer reset, which is true.
    Finally it tests so that two cannoncs does not have the same cooldown.
     */


    @Test
    public void checkCollision() throws Exception {

    }

    @Test
    public void allowedToFire() throws Exception {
        assertEquals(cannon.allowedToFire(),true);
        assertEquals(cannon.allowedToFire(),false);

        cannon.setPreviousFireTime(0);
        assertEquals(cannon.allowedToFire(),true);

        assertEquals(cannon2.allowedToFire(),true);
        assertEquals(cannon.allowedToFire(),false);
    }

    @Test
    public void setPreviousFireTime() throws Exception {

    }

}

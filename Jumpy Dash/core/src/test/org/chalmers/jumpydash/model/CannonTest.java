package org.chalmers.jumpydash.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CannonTest {
    private Cannon cannon;

    @Before
    public void setUp(){
        cannon = new Cannon();
    }

    /*
    First tests if the cannon is allowed to fire, which should be allowed. Then test if it is allowed
    to fire again, which should not be allowed. After that the timer for firing is reset to 0.
    After that it tests if the cannon is allowed to fire after timer reset, which is true.
    Finally it tests so that two cannoncs does not have the same cooldown.
     */

    @Test
    public void allowedToFire() throws Exception {
        assertEquals(cannon.allowedToFire(),true);
        assertEquals(cannon.allowedToFire(),false);

        cannon.setPreviousFireTime(0);
        assertEquals(cannon.allowedToFire(),true);

        cannon.setPreviousFireTime(System.currentTimeMillis()-2501);
        assertEquals(cannon.allowedToFire(),true);

        cannon.setPreviousFireTime(System.currentTimeMillis()-2000);
        assertEquals(cannon.allowedToFire(),false);
    }

}

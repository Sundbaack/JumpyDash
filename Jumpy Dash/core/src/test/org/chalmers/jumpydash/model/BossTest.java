package org.chalmers.jumpydash.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BossTest {
    private Boss boss;

    @Before
    public void setUp(){
        boss = new Boss();
    }

    /*
    First tests if the boss is allowed to fire, which should be allowed. Then test if it is allowed
    to fire again, which should not be allowed. After that the timer for firing is reset to 0.
    After that it tests if the boss is allowed to fire after timer reset, which is true.
    Finally it tests two values that are close to the cooldown limit one inside
    the limit, which should return false on fire, and one outside, which should allow fire.
     */

    @Test
    public void allowedToFire() throws Exception {
        assertEquals(boss.allowedToFire(),true);
        assertEquals(boss.allowedToFire(),false);

        boss.setPreviousFireTime(0);
        assertEquals(boss.allowedToFire(),true);

        boss.setPreviousFireTime(System.currentTimeMillis()-1001);
        assertEquals(boss.allowedToFire(),true);

        boss.setPreviousFireTime(System.currentTimeMillis()-500);
        assertEquals(boss.allowedToFire(),false);
    }

}
package org.chalmers.jumpydash.model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {
    public static Enemy enemy;

    @BeforeClass
    public static void beforeClass(){
        enemy = new Soldier(0);
    }
    @Test
    public void isDead() throws Exception {
        assertFalse(enemy.isDead());
        assertEquals(enemy.getHealthPoints(),1);
        enemy.damageTaken();
        assertTrue(enemy.isDead());
    }

}
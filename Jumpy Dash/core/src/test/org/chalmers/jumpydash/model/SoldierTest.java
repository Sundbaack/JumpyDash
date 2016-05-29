package org.chalmers.jumpydash.model;

import org.junit.BeforeClass;
import org.junit.Test;
import physics.MockBox2D;
import physics.MockJDBody;

import javax.vecmath.Vector2f;

import static org.junit.Assert.*;


public class SoldierTest {
    private static Soldier soldier;
    private static MockBox2D mockBox2D;
    private static float x = 0;
    private static int mapHeight = 0;
    private static boolean ghost = false;

    @BeforeClass
    public static void setUp(){
        mockBox2D = new MockBox2D();
        soldier = new Soldier(0);

        soldier.setJDBody(mockBox2D.newBody(x,x,mapHeight,null,ghost,ghost));
        soldier.getJDBody().setUserData(soldier);
    }
    /*
            The method move is not tested more extensively with the use of the framework because we
            got instructions to only test the models. Therefor this test only test the if logic in move().
         */
    @Test
    public void jump() throws Exception {
        soldier.jump();
        MockJDBody mockJDBody = (MockJDBody) soldier.getJDBody();
        assertEquals(mockJDBody.getImpulse(),(new Vector2f(0, 3f)));
    }

    /*
        The method move is not tested more extensively with the use of the framework because we
        got instructions to only test the models. Therefor this test only test the if logic in move().
     */
    @Test
    public void move() throws Exception {
        soldier.move();
        MockJDBody mockJDBody = (MockJDBody) soldier.getJDBody();
        assertEquals(mockJDBody.getForceToCenter(),new Vector2f(2f, 0));

        soldier.setCurrentState();
        soldier.move();
        mockJDBody = (MockJDBody) soldier.getJDBody();
        assertEquals(mockJDBody.getForceToCenter(),new Vector2f(-2f, 0));

        soldier.setCurrentState();
        soldier.move();
        mockJDBody = (MockJDBody) soldier.getJDBody();
        assertEquals(mockJDBody.getForceToCenter(),new Vector2f(2f, 0));
    }
}
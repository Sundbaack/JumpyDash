package org.chalmers.jumpydash.model;

import org.junit.Before;
import org.junit.Test;
import physics.MockBox2D;

import javax.vecmath.Vector2f;

import static org.junit.Assert.*;


public class MovingPlatformTest {
    private  MockBox2D mockBox2D;
    private MovingPlatform movingPlatform;

    @Before
    public void setUp(){
        mockBox2D = new MockBox2D();
        movingPlatform = new MovingPlatform();


        float x = 0;
        int mapHeight = 0;
        boolean ghost = false;

        movingPlatform.setJDBody(mockBox2D.newBody(x,x,mapHeight,null,ghost,ghost));
        movingPlatform.getJDBody().setUserData(movingPlatform);

    }
    @Test
    public void moveUp() throws Exception {
        movingPlatform.moveUp();
        assertEquals(movingPlatform.getJDBody().getLinearVelocity(),new Vector2f(0, 0));
        movingPlatform.setMovePlatforms();
        movingPlatform.moveUp();
        assertEquals(movingPlatform.getJDBody().getLinearVelocity(),new Vector2f(0, 1));
        movingPlatform.setMovePlatforms();
        movingPlatform.moveUp();
        assertEquals(movingPlatform.getJDBody().getLinearVelocity(),new Vector2f(0, 0));
    }

}
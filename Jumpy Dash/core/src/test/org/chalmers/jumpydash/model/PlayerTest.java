package org.chalmers.jumpydash.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import physics.MockBox2D;
import physics.MockJDBody;

import javax.vecmath.Vector2f;

import static org.junit.Assert.*;


public class PlayerTest {
    private static Player player;
    private static MockBox2D mockBox2D;
    private static float x = 0;
    private static int mapHeight = 0;
    private static boolean ghost = false;
    private static final float MAX_SPEED_X = 3.5f;

    @BeforeClass
    public static void setUp(){
        mockBox2D = new MockBox2D();
        player = new Player();

        player.setJDBody(mockBox2D.newBody(x,x,mapHeight,null,ghost,ghost));
        player.getJDBody().setUserData(player);
        player.getJDBody().setLinearVelocity(new Vector2f(0f,0));
    }
    /*
            The method move is not tested more extensively with the use of the framework because we
            got instructions to only test the models. Therefor this test only test the if logic in move().
         */
    @Test
    public void jump() throws Exception {
        player.setImpulse(0);
        player.jump();
        MockJDBody mockJDBody = (MockJDBody) player.getJDBody();
        assertEquals(mockJDBody.getImpulse(),(new Vector2f(0, 0)));
        assertEquals(player.previousState,Player.State.RUNNING);
        assertEquals(player.currentState,Player.State.JUMPING);
    }

    /*
        The method move is not tested more extensively with the use of the framework because we
        got instructions to only test the models. Therefor this test only test the if logic in move().
        First the test checks if a player that is standing still is allowed to increase speed.
        Then it checks if a player that is already moving too fast is allowed to increase.
        Finally it test a value right under the maximum limit for the logical checks.
     */
    @Test
    public void move() throws Exception {
        float expected =player.getSpeed()+0f;
        player.move();
        MockJDBody mockJDBody = (MockJDBody) player.getJDBody();
        assertEquals(mockJDBody.getForceToCenter(),new Vector2f(expected, 0));

        player.getJDBody().setLinearVelocity(new Vector2f(3.6f,0));
        expected =player.getSpeed()+3.6f;
        player.move();
        mockJDBody = (MockJDBody) player.getJDBody();
        assertNotEquals(mockJDBody.getForceToCenter(),new Vector2f(expected, 0));

        player.getJDBody().setLinearVelocity(new Vector2f(3.4f,0));
        player.move();
        mockJDBody = (MockJDBody) player.getJDBody();
        expected =player.getSpeed();
        assertEquals(mockJDBody.getForceToCenter(),new Vector2f(expected, 0));
    }

    @Test
    public void applyTrampolineImpulse() throws Exception {
        player.setImpulse(0);
        player.applyTrampolineImpulse();
        MockJDBody mockJDBody = (MockJDBody) player.getJDBody();
        assertEquals(mockJDBody.getImpulse(), new Vector2f(0, 0 + 2.5f));
    }

    @Test
    public void applySoldierImpulse() throws Exception {
        player.setImpulse(0);
        player.applySoldierImpulse();
        MockJDBody mockJDBody = (MockJDBody) player.getJDBody();
        assertEquals(mockJDBody.getImpulse(), new Vector2f(0, 0));
    }

    @Test
    public void getState() throws Exception {
        player.getJDBody().setLinearVelocity(new Vector2f(0,0));
        assertEquals(player.getState(), Player.State.STANDING);

        player.getJDBody().setLinearVelocity(new Vector2f(0,1));
        assertEquals(player.getState(), Player.State.JUMPING);

        player.getJDBody().setLinearVelocity(new Vector2f(0,-1));
        assertEquals(player.getState(), Player.State.FALLING);

        player.getJDBody().setLinearVelocity(new Vector2f(5,0));
        assertEquals(player.getState(), Player.State.RUNNING);
    }

    @Test
    public void getInvinciblePickUpTime() throws Exception {
        assertEquals(player.getInvinciblePickUpTime(),0);
        player.setInvinciblePickUpTime();
        assertNotEquals(player.getInvinciblePickUpTime(),0);
    }

    @Test
    public void isInvincible() throws Exception {
        assertFalse(player.isInvincible());
        player.setInvincible();
        assertTrue(player.isInvincible());
        player.setInvincible();
        assertFalse(player.isInvincible());
    }

    @Test
    public void allowedToFire() throws Exception {
        assertEquals(player.allowedToFire(),true);
        assertEquals(player.allowedToFire(),false);

        player.setPreviousFireTime(0);
        assertEquals(player.allowedToFire(),true);
    }

    @Test
    public void isDead() throws Exception {
        assertFalse(player.isDead());
        player.setHealth(-3);
        assertTrue(player.isDead());
    }

    @Test
    public void playerSpeedUp() throws Exception {
        float start = player.getSpeed();
        player.playerSpeedUp();
        assertNotEquals(player.getSpeed(),start);
    }

    @Test
    public void getPoints() throws Exception {
        assertEquals(player.getPoints(),0);
        player.setPoints(5);
        assertEquals(player.getPoints(),5);
        player.setPoints(-10);
        assertEquals(player.getPoints(),-5);
    }

    @Test
    public void getHealth() throws Exception {
        player.setHealth(3);
        assertEquals(player.getHealth(),3);
    }
}
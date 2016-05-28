package org.chalmers.jumpydash.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class PlayerTest {

    private static Player player;

    @BeforeClass
    public static void beforeClass(){
        //IBox2D box2D = new MockBox2D();


        player= new Player();

       /* player.setJDBody(box2D.newBody(0,0,50,"dynamic", false,false));
        player.setJDBody(new MockJDBody());*/
    }

    //first methods are not yet tested or maybe they shouldnt be tested

    @Test
    public void jump() throws Exception {
        /*Vector2f positionBefore = player.getPosition();
        player.jump();
        player.move();
        Vector2f positionAfter = player.getPosition();
        assertNotEquals(positionAfter,positionBefore);*/
    }

    @Test
    public void move() throws Exception {

    }

    @Test
    public void applyTrampolineImpulse() throws Exception {

    }

    @Test
    public void applySoldierImpulse() throws Exception {

    }

    @Test
    public void getState() throws Exception {

    }

    @Test
    public void setSound() throws Exception {

    }

    @Test
    public void dispose() throws Exception {

    }

    @Test
    public void setImpulse() throws Exception {

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
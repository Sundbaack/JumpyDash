package org.chalmers.jumpydash.model;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import physics.MockBox2D;

import javax.vecmath.Vector2f;

import static org.junit.Assert.*;


public class JDModelTest {
    private static JDModel jdModel;
    private static MockBox2D mockBox2D;
    private static float x = 0;
    private static int mapHeight = 0;
    private static boolean ghost = false;

    @BeforeClass
    public static void setUp(){
        mockBox2D = new MockBox2D();
        jdModel = new Soldier(0);

        jdModel.setJDBody(mockBox2D.newBody(x,x,mapHeight,null,ghost,ghost));
        jdModel.getJDBody().setUserData(jdModel);
    }
    @Test
    public void getPosition() throws Exception {
        assertEquals(jdModel.getPosition(),new Vector2f(0,0));
    }

    @Test
    public void equals() throws Exception {
        JDModel jdModelNull = null;
        JDModel jdModelSoldier1 = new Soldier(0);
        assertFalse(jdModelSoldier1.equals(jdModelNull));

        JDModel jdModelSoldier2 = jdModelSoldier1;
        assertTrue(jdModelSoldier1.equals(jdModelSoldier2));

        JDModel jdModelAbility = new Ability();
        assertFalse(jdModelSoldier1.equals(jdModelAbility));

        JDModel jdModelSoldier3 = new Soldier(1);

        jdModelSoldier3.setJDBody(mockBox2D.newBody(x,x,mapHeight,null,ghost,ghost));
        jdModelSoldier1.setJDBody(null);
        assertFalse(jdModelSoldier1.equals(jdModelSoldier3));

        jdModelSoldier3.setJDBody(null);
        assertTrue(jdModelSoldier1.equals(jdModelSoldier3));
    }

    @Test
    public void userDataNull() throws Exception {
        assertNotEquals(jdModel.getJDBody().getUserData(),null);
        jdModel.userDataNull();
        assertEquals(jdModel.getJDBody().getUserData(),null);
    }

}
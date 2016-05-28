package org.chalmers.jumpydash.model;


import org.junit.Before;
import org.junit.Test;
import physics.MockBox2D;

import javax.vecmath.Vector2f;

import static org.junit.Assert.*;


public class JDModelTest {
    private JDModel jdModel;

    @Before
    public void setUp(){
        MockBox2D mockBox2D = new MockBox2D();
        jdModel = new Soldier(0);
        float x = 0;
        int mapHeight = 0;
        boolean ghost = false;
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
        JDModel jdModelSoldier2 = jdModelSoldier1;
        JDModel jdModelSoldier3 = new Soldier(1);
        JDModel jdModelAbility = new Ability();
        assertFalse(jdModelSoldier1.equals(jdModelNull));
        assertTrue(jdModelSoldier1.equals(jdModelSoldier2));
        assertFalse(jdModelSoldier1.equals(jdModelAbility));
        assertTrue(jdModelSoldier1.equals(jdModelSoldier3));
    }

    @Test
    public void userDataNull() throws Exception {

    }

}
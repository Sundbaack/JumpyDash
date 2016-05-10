package org.chalmers.projectrolf.model

/**
 * Created by Johannes on 2016-05-09.
 */
class SoldierTest extends GroovyTestCase {
    void testSetDirectionFlag() {
        Soldier s = new Soldier();
        assertTrue(s.getDirectionFlag()==true);
        s.setDirectionFlag();
        assertTrue(s.getDirectionFlag()==false);
    }
}

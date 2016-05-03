package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;

public class Bullet {

    private JDBody body;

    public Bullet(JDBody body) {
        this.body = body;
        this.body.setLinearVelocity(new Vector2(15, 0));
    }

    public JDBody getJDBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }


}

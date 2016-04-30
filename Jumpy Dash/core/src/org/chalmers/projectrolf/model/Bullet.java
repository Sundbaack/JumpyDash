package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import org.chalmers.projectrolf.controller.JDBody;

public class Bullet {

    private JDBody body;

    public Bullet(JDBody body) {
        this.body = body;
        this.body.setLinearVelocity(new Vector2(15, 0));
    }

    public JDBody getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }


}

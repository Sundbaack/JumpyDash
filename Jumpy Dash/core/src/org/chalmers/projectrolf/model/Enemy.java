package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;

public class Enemy {

    private JDBody body;

    public Enemy(JDBody body) {
        this.body = body;
    }

    public JDBody getJDBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }
}

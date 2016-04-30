package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;

public class Item {

    private JDBody body;

    public Item(JDBody body) {
        this.body = body;
    }

    public JDBody getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

}

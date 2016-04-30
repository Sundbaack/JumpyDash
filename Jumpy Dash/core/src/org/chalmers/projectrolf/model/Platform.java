package org.chalmers.projectrolf.model;

import com.badlogic.gdx.math.Vector2;
import org.chalmers.projectrolf.controller.JDBody;

public class Platform  {

    private JDBody body;

    public Platform(JDBody body){
        this.body = body;
    }

    public JDBody getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

}

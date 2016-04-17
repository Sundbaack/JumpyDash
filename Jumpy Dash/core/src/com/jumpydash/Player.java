package com.jumpydash;

import com.badlogic.ashley.core.Entity;
import com.uwsoft.editor.renderer.scripts.IScript;

public class Player implements IScript {

    private Entity entity;

    // Initialize
    @Override
    public void init(Entity entity) {
        this.entity = entity;
    }

    // Updates the actor based on time.
    @Override
    public void act(float delta) {

    }

    // Called when this screen should release all resources.
    @Override
    public void dispose() {

    }
}


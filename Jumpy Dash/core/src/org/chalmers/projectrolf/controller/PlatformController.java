package org.chalmers.projectrolf.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.chalmers.projectrolf.model.Platform;
import org.chalmers.projectrolf.view.PlatformView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexsundback on 2016-04-28.
 */
public class PlatformController extends ApplicationAdapter {

    private BodyDef platformBodyDef;
    private Body platformBody;
    private Platform platform;
    private List<Platform> platformList;
    private PlatformView platformView;



    public PlatformController(){
        platformList = new ArrayList<Platform>();

    }


    public void createObject(int x, int y, float tileWidthHeight, int mapHeight){
        platformBodyDef = new BodyDef();
        platformBodyDef.type = BodyDef.BodyType.StaticBody;
        platformBodyDef.position.set(x * tileWidthHeight / GameController.PIXELS_TO_METERS, (mapHeight - 1 - y) * 32 / GameController.PIXELS_TO_METERS);
        platformBody = GameController.world.createBody(platformBodyDef);
        platform = new Platform(platformBody, tileWidthHeight / GameController.PIXELS_TO_METERS);
        platformList.add(platform);
        platformView = new PlatformView(platformList);
    }

    public PlatformView getPlatformView(){
        return this.platformView;
    }

}

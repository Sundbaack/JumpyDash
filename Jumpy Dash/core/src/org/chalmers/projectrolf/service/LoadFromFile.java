package org.chalmers.projectrolf.service;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.chalmers.projectrolf.controller.*;
import org.chalmers.projectrolf.physics.IBox2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFromFile {

    public static void loadMap(IBox2D box2D, Stage stage, File level) throws FileNotFoundException {
        Scanner scanLevel = new Scanner(level);

        char[][] level1 = new char[23][1200];

        for (int y = 0; y < level1.length; y++) {
            String currentLine = scanLevel.nextLine();
            currentLine = currentLine.replaceAll("\\s+", "");

            for (int x = 0; x < currentLine.length(); x++) {
                level1[y][x] = currentLine.charAt(x);
            }
        }

        int mapHeight = level1.length;
        int mapWidth = level1[0].length;

        // Loop from top to bottom, left to right
        // Create objects, place them in lists and set their positions
        for (int y = 0; y < mapHeight; y++) {

            for (int x = 0; x < mapWidth; x++) {

                if (level1[y][x] == 'P') {
                    PlayerController p = new PlayerController(box2D, x, y, mapHeight);
                    stage.addActor(p);

                } else if (level1[y][x] == '#') {
                    PlatformController platformController = new PlatformController(box2D, x, y, mapHeight);
                    stage.addActor(platformController);
                } else if (level1[y][x] == 'C') {
                    CoinController coinController = new CoinController(box2D, x, y, mapHeight);
                    stage.addActor(coinController);

                } else if (level1[y][x] == 'S') {
                    SoldierController soldierController = new SoldierController(box2D, x, y, mapHeight);
                    stage.addActor(soldierController);

                } else if (level1[y][x] == 'A') {
                    AbilityController abilityController = new AbilityController(box2D, x, y, mapHeight);
                    stage.addActor(abilityController);

                }
            }
        }
    }
}

package org.chalmers.jumpydash.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public static char[][] fileToArray(File tileMap) throws FileNotFoundException {
        Scanner scanLevel = new Scanner(tileMap);

        char[][] level = new char[23][1200];

        for (int y = 0; y < level.length; y++) {
            String currentLine = scanLevel.nextLine();
            currentLine = currentLine.replaceAll("\\s+", "");

            for (int x = 0; x < currentLine.length(); x++) {
                level[y][x] = currentLine.charAt(x);
            }
        }
        return level;
    }
}

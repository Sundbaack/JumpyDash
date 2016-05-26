package org.chalmers.jumpydash.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile implements IReadFile {

    public char[][] fileToArray(File tileMap) throws FileNotFoundException {
        Scanner scanLevel = new Scanner(tileMap);

        // Read the first line to determine array size
        String line = scanLevel.nextLine();
        line = line.replaceAll("\\s+", "");
        int mapLenght = line.length();

        // Reset scanner
        scanLevel = new Scanner(tileMap);

        char[][] level = new char[23][mapLenght];

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

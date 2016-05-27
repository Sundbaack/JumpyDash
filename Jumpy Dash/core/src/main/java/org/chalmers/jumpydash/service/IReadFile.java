package main.java.org.chalmers.jumpydash.jumpydash.service;

import java.io.File;
import java.io.FileNotFoundException;

public interface IReadFile {

    char[][] fileToArray(File tileMap) throws FileNotFoundException;
}

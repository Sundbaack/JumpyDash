package org.chalmers.jumpydash.service;

import java.io.File;
import java.io.FileNotFoundException;

public interface IReadFile {

    char[][] fileToArray(File tileMap) throws FileNotFoundException;
}

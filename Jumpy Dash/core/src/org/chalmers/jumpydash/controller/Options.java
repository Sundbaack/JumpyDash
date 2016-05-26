package org.chalmers.jumpydash.controller;

/**
 * Created by alexsundback on 2016-05-25.
 */
public class Options {

    // Singleton
    private static Options instance = new Options();

    // Variables
    private boolean sound;
    private boolean music;

    public static Options getInstance() {
        return instance;
    }

    public void initialize(boolean sound, boolean music) {
        this.sound = sound;
        this.music = music;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean getSound() {
        return this.sound;
    }

    public boolean getMusic() {
        return this.music;
    }

}

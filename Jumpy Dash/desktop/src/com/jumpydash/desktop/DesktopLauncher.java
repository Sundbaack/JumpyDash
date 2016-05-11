package com.jumpydash.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.chalmers.projectrolf.controller.JumpyDash;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 736;
		config.width = 1280;
		config.resizable = false;
		new LwjglApplication(new JumpyDash(), config);
	}
}

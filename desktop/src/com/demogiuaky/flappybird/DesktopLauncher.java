package com.demogiuaky.flappybird;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.demogiuaky.flappybird.FlappyBirdDemo;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("FlappyBirdDemo");
		config.setWindowedMode(FlappyBirdDemo.WIDTH,FlappyBirdDemo.HEIGHT);
		new Lwjgl3Application(new FlappyBirdDemo(), config);
	}
}

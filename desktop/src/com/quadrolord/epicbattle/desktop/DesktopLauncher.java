package com.quadrolord.epicbattle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.desktop.utils.DesktopFileService;
import com.quadrolord.epicbattle.logic.utils.PlatformServices;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		PlatformServices ps = new PlatformServices(
				new DesktopFileService()
		);

		new LwjglApplication(new EpicBattle(ps), config);
	}
}

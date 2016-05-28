package com.quadrolord.epicbattle;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.quadrolord.epicbattle.logic.utils.AndroidFileService;
import com.quadrolord.ejge.utils.PlatformServices;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		PlatformServices ps = new PlatformServices(
				new AndroidFileService()
		);

		initialize(new EpicBattle(ps), config);
	}
}

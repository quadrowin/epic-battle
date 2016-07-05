package com.quadrolord.epicbattle;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.firebase.client.Firebase;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.epicbattle.logic.utils.AndroidFileService;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Firebase.setAndroidContext(getApplicationContext());
		PlatformServices ps = new PlatformServices(
				new AndroidFileService()
		);

		initialize(new EpicBattle(ps), config);
	}
}

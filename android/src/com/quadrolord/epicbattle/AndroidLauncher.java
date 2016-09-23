package com.quadrolord.epicbattle;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.epicbattle.logic.utils.AndroidAuthService;
import com.quadrolord.epicbattle.logic.utils.AndroidFileService;
import com.quadrolord.epicbattle.logic.utils.PackageHelper;

public class AndroidLauncher extends AndroidApplication {

    private AndroidAuthService mAuthService;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        mAuthService = new AndroidAuthService(this);
		PlatformServices ps = new PlatformServices(
				new AndroidFileService(),
                mAuthService
		);
        ps.setBuildTime(PackageHelper.getBuildTimestamp(this) );

		initialize(new EpicBattle(ps), config);
        mAuthService.setup();

        int signed = PackageHelper.checkAppSignature(this.getContext());
        Gdx.app.log("AndroidLauncher", "Check signature result=" + signed);
    }

    @Override
	protected final void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
        mAuthService.onActivityResult(requestCode, resultCode, intent);
	}

    @Override
    protected void onStart() {
        super.onStart();
        mAuthService.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuthService.getGameHelper().onStop();
    }

}

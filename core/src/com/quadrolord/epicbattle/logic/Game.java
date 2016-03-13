package com.quadrolord.epicbattle.logic;

import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.utils.PlatformServices;
import com.quadrolord.epicbattle.view.sounds.SoundManager;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class Game {

    private BattleGame mBattleGame;

    private PlatformServices mPlatformServices;

    private ProfileManager mProfileManager;

    private SoundManager mSoundManager = new SoundManager();

    public Game(PlatformServices platformServices) {
        mPlatformServices = platformServices;
        mProfileManager = new ProfileManager(mPlatformServices);
    }

    public BattleGame getBattleGame() {
        if (mBattleGame == null) {
            mBattleGame = new BattleGame(this);
        }
        return mBattleGame;
    }

    public ProfileManager getProfileManager() {
        return mProfileManager;

    }

    public SoundManager getSoundManager() {
        return mSoundManager;
    }



}

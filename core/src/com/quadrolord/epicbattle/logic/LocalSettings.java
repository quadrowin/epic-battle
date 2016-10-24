package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Goorus on 16.10.2016.
 */

public class LocalSettings {

    private static final String TAG = "LocalSettings";

    private static final String STORAGE_NAME = "EpicBattle";

    private static final String OPTION_MUSIC_ENABLED = "music_enabled";

    private static final String OPTION_SOUND_ENABLED = "sound_enabled";

    private Preferences mPreferences;

    public LocalSettings() {
        mPreferences = Gdx.app.getPreferences(STORAGE_NAME);
    }

    public void flush() {
        mPreferences.flush();
    }

    public int getInt(String key, int def) {
        return mPreferences.getInteger(key, def);
    }

    public boolean getMusicEnabled() {
        return mPreferences.getBoolean(OPTION_MUSIC_ENABLED, true);
    }

    public boolean getSoundEnabled() {
        return mPreferences.getBoolean(OPTION_SOUND_ENABLED, true);
    }

    public void setMusicEnabled(boolean enabled) {
        mPreferences.putBoolean(OPTION_MUSIC_ENABLED, enabled);
    }

    public void setSoundEnabled(boolean enabled) {
        mPreferences.putBoolean(OPTION_SOUND_ENABLED, enabled);
    }

    public int incCounter(String settingKey) {
        int val = mPreferences.getInteger(settingKey, 0) + 1;
        mPreferences.putInteger(settingKey, val);
        return val;
    }

}

package com.quadrolord.epicbattle.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class Sounds {

    private String mSoundsPath = "sounds/";

    public static final String SKILL_USE_FAIL = "skill_use_fail.ogg";

    public static final String LEVEL_UP = "level_up.ogg";

    public static final String SKILL_SUN_STRIKE = "skill_sun_strike.ogg";

    public static final String THING_BOTTLE = "thing_bottle.ogg";

    private HashMap<String, Sound> mLoaded = new HashMap<String, Sound>();

    public void loadSound(String name) {
        mLoaded.put(name, Gdx.audio.newSound(Gdx.files.internal(mSoundsPath + name)));
    }

    public void loadSounds(String[] names) {
        for (int i = 0; i < names.length; i++) {
            loadSound(names[i]);
        }
    }

    public long play(String name) {
        if (name == null) {
            return 0;
        }
        Gdx.app.log(getClass().getName(), "play sound " + name);
        return mLoaded.get(name).play();
    }

}

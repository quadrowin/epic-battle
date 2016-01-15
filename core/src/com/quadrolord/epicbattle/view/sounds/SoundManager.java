package com.quadrolord.epicbattle.view.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.screen.AbstractScreen;

import java.util.Iterator;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class SoundManager {

    private String mSoundsPath = "sounds/";

    public static final String EVENT_DEFEAT = "event/defeat.wav";

    public static final String SKILL_TOWER_CREAK1 = "skill/tower/creak1.wav";

    public static final String SKILL_TOWER_BLEED1 = "skill/tower/bleed1.wav";

    public static final String MENU_CLICK = "menu/click.wav";

    private ArrayMap<String, LoadedSound> mLoaded = new ArrayMap<String, LoadedSound>();

    public void disposeSounds(AbstractScreen screen) {
        for (Iterator<ObjectMap.Entry<String, LoadedSound>> iter = mLoaded.iterator(); iter.hasNext(); ) {
            ObjectMap.Entry<String, LoadedSound> item = iter.next();
            if (item.value.Screens.contains(screen, true)) {
                iter.remove();
            }
        }
    }

    public void loadSound(AbstractScreen screen, String name) {
        LoadedSound sound = mLoaded.get(name);
        if (sound == null) {
            sound = new LoadedSound();
            mLoaded.put(name, sound);
            sound.Item = Gdx.audio.newSound(Gdx.files.internal(mSoundsPath + name));
        }
        if (!sound.Screens.contains(screen, true)) {
            sound.Screens.add(screen);
        }
    }

    public void loadSounds(AbstractScreen screen, String[] names) {
        for (int i = 0; i < names.length; i++) {
            loadSound(screen, names[i]);
        }
    }

    public long play(String name) {
        if (name == null) {
            return 0;
        }
        Gdx.app.log(getClass().getName(), "play sound " + name);
        return mLoaded.get(name).Item.play();
    }

}

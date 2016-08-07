package com.quadrolord.ejge.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.ejge.view.AbstractScreen;

import java.util.Iterator;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class SoundManager {

    private boolean mEnabled = false;

    private String mSoundsPath = "sounds/";

    private ArrayMap<String, LoadedSound> mLoaded = new ArrayMap<String, LoadedSound>();

    public void loadSound(Object source, String name) {
        LoadedSound sound = mLoaded.get(name);
        if (sound == null) {
            sound = new LoadedSound();
            mLoaded.put(name, sound);
            sound.Item = Gdx.audio.newSound(Gdx.files.internal(mSoundsPath + name));
        }
        if (!sound.Sources.contains(source, true)) {
            sound.Sources.add(source);
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
        if (!mEnabled) {
            return 0;
        }
        Gdx.app.log(getClass().getName(), "play sound " + name);
        return mLoaded.get(name).Item.play();
    }

    /**
     * При закрытии экрана освобождаем ненужные звуки
     * @param source
     */
    public void onSourceDispose(Object source) {
        for (Iterator<ObjectMap.Entry<String, LoadedSound>> it = mLoaded.iterator(); it.hasNext(); ) {
            ObjectMap.Entry<String, LoadedSound> en = it.next();
            boolean removed = en.value.Sources.removeValue(source, true);
            if (removed && en.value.Sources.size == 0) {
                it.remove();
            }
        }
    }

}

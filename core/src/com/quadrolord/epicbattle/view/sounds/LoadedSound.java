package com.quadrolord.epicbattle.view.sounds;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.screen.AbstractScreen;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class LoadedSound {

    public Sound Item;

    /**
     * Скрины, использующие этот звук
     */
    public Array<AbstractScreen> Screens = new Array<AbstractScreen>();

}

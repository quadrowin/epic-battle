package com.quadrolord.epicbattle.screen.slider;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Goorus on 20.07.2016.
 */
abstract public class SliderListener<T> {

    abstract public void onSelect(TextButton btn, T data);

}

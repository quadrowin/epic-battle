package com.quadrolord.epicbattle.screen.slider;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Quadrowin on 28.06.2016.
 */
abstract public class SliderContent<T> {

    abstract public int getCount();

    /**
     * Первая инициализация элемента списка
     * @param btn
     * @param index
     */
    abstract public void initButton(TextButton btn, int index);

}

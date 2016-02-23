package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;

/**
 * Текстура, которая отображается в слайдере на экране выбора постройки
 * Created by Quadrowin on 23.02.2016.
 */
public class SliderTexture extends AbstractLoader {

    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        info.setSliderTexture(data.asString());
    }

}

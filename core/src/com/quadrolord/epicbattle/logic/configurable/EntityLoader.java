package com.quadrolord.epicbattle.logic.configurable;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Quadrowin on 31.01.2016.
 */
abstract public class EntityLoader<T extends AbstractEntity> {

    abstract public void assign(T info, JsonValue data);

}

package com.quadrolord.epicbattle.logic.bullet.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;

/**
 * Created by Quadrowin on 23.01.2016.
 */
public class MoveSpeed extends AbstractLoader {
    @Override
    public void assign(AbstractLogic info, JsonValue data) {
        info.setMoveSpeed(data.asFloat());
    }
}

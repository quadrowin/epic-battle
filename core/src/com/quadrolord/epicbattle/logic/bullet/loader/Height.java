package com.quadrolord.epicbattle.logic.bullet.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;

/**
 * Created by Goorus on 18.07.2016.
 */
public class Height extends AbstractLoader {

    @Override
    public void assign(AbstractLogic info, JsonValue data) {
        info.setHeight(data.asFloat());
    }

}

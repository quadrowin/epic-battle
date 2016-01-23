package com.quadrolord.epicbattle.logic.bullet.loader;

import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;

/**
 * Created by Quadrowin on 23.01.2016.
 */
public class ConstructionTime extends AbstractLoader {
    @Override
    public void assign(BulletInfo info, JsonValue data) {
        info.setConstructionTime(data.asFloat());
    }
}

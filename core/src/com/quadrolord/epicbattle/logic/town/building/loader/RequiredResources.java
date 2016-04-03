package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;

/**
 * Created by morph on 24.01.2016.
 */
public class RequiredResources extends AbstractLoader {
    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        Class<? extends AbstractThingEntity> className;

        ArrayMap<Class<? extends AbstractThingEntity>, Integer> resources = new ArrayMap<Class<? extends AbstractThingEntity>, Integer>();

        for (JsonValue.JsonIterator it = data.iterator(); it.hasNext(); ) {
            JsonValue val = it.next();
            String name = val.name();

            try {
                className = (Class<? extends AbstractThingEntity>)Class.forName("com.quadrolord.epicbattle.logic.town.thing.entity." + name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            resources.put(className, val.asInt());
        }

        info.setRequiredResources(resources);
    }
}

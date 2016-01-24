package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.BuildingInfo;
import com.quadrolord.epicbattle.logic.town.resource.Resource;

/**
 * Created by morph on 24.01.2016.
 */
public class RequiredResources extends AbstractLoader {
    @Override
    public void assign(BuildingInfo info, JsonValue data) {
        Class<? extends Resource> className;

        ArrayMap<Class<? extends Resource>, Integer> resources = new ArrayMap<Class<? extends Resource>, Integer>();

        for (JsonValue.JsonIterator it = data.iterator(); it.hasNext(); ) {
            JsonValue val = it.next();
            String name = val.name();

            try {
                className = (Class<? extends Resource>)Class.forName("com.quadrolord.epicbattle.logic.town.resource" + name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            resources.put(className, val.asInt());
        }

        info.setRequiredResources(resources);
    }
}

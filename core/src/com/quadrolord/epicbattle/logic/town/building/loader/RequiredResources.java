package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.resource.ResourceEntity;

/**
 * Created by morph on 24.01.2016.
 */
public class RequiredResources extends AbstractLoader {
    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        Class<? extends ResourceEntity> className;

        ArrayMap<Class<? extends ResourceEntity>, Integer> resources = new ArrayMap<Class<? extends ResourceEntity>, Integer>();

        for (JsonValue.JsonIterator it = data.iterator(); it.hasNext(); ) {
            JsonValue val = it.next();
            String name = val.name();

            try {
                className = (Class<? extends ResourceEntity>)Class.forName("com.quadrolord.epicbattle.logic.town.resource" + name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            resources.put(className, val.asInt());
        }

        info.setRequiredResources(resources);
    }
}

package com.quadrolord.epicbattle.logic.town.building.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.resource.ResourceEntity;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceEntity;

/**
 * Created by Quadrowin on 07.02.2016.
 */
public class Resources extends AbstractLoader {

    @Override
    public void assign(AbstractBuildingEntity info, JsonValue data) {
        for (JsonValue.JsonIterator it = data.iterator(); it.hasNext(); ) {
            JsonValue param = it.next();
            String resourceClassName = param.getString("name");
            Class<? extends ResourceEntity> resourceClass;
            try {
                resourceClass = (Class<? extends ResourceEntity>) Class.forName(resourceClassName);
            } catch (Exception e) {
                Gdx.app.error("Resources Loader", "ResourceEntity class not found", e);
                continue;
            }
            int maxBalance = param.getInt("max", 10);
            float productionRate = param.getFloat("production", 1);
            ResourceSourceEntity rs = new ResourceSourceEntity();
            rs.setMaxBalance(maxBalance);
            rs.setResourceClass(resourceClass);
            rs.setProductionRate(productionRate);
            info.getResources().add(rs);
        }
    }

}

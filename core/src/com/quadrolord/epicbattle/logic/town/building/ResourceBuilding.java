package com.quadrolord.epicbattle.logic.town.building;

import com.quadrolord.epicbattle.logic.town.resource.Resource;

/**
 * Created by morph on 17.01.2016.
 */
public abstract class ResourceBuilding extends Building{
    protected Resource mProductionResource;
    protected float mProductionTime;

    public float getProductionTime() {
        return mProductionTime;
    }

    public void setProductionTime(float productionTime) {
        mProductionTime = productionTime;
    }

    public Resource getProductionResource() {
        return mProductionResource;
    }

    public void setProductionResource(Resource productionResource) {
        mProductionResource = productionResource;
    }
}

package com.quadrolord.epicbattle.logic.campaign;

/**
 * Created by Quadrowin on 11.01.2016.
 */
abstract public class AbstractCampaign {

    private String name;

    abstract public Level[] getLevels();

    public String getName() {
        return name;
    }

}

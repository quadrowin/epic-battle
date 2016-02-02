package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.ResourceBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;
import com.quadrolord.epicbattle.logic.town.resource.ResourceItem;
import com.quadrolord.epicbattle.screen.MineBuildingScreen;
import com.quadrolord.epicbattle.view.town.building.MineView;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class Mine extends ResourceBuildingEntity {

    /**
     * Производство ресурса в минуту
     */
    private float mProductionRate = 0.01f;

    /**
     * Максимальное количество ресурса, которое может быть накоплено
     */
    private long mMaxBalance = 100;

    public Mine() {
        setViewClass(MineView.class);
    }


    @Override
    public void initItem(ResourceBuildingItem item) {
        item.setLastYield(TimeUtils.millis());
        item.setSize((int)mSize.x, (int)mSize.y);
        item.setResourceClass(IronOre.class);
    }

    @Override
    public void runOnSelect(AbstractBuildingItem item) {
        MineBuildingScreen scr = new MineBuildingScreen(item.getView().getScreen(), (ResourceBuildingItem)item);
        item.getView().getScreen().getAdapter().switchToScreen(scr, false);
    }

    /**
     * Забор накопленных ресурсов
     */
    public void takeAvailable(ResourceBuildingItem mine) {
        updateBalance(mine);
        long balance = mine.getCurrentBalance();
        mine.setLastYield(TimeUtils.millis());
        ResourceItem resource = mine.getTown().getResource(mine.getResourceClass());
        resource.incValue(balance);
    }

    public void updateBalance(ResourceBuildingItem mine) {
        int deltaBalance = (int)((TimeUtils.millis() - mine.getLastYield()) * mProductionRate);
        long balance = Math.min(mMaxBalance, deltaBalance);
        mine.setCurrentBalance(balance);
    }

}

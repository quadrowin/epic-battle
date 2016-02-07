package com.quadrolord.epicbattle.logic.town.building.entity;

import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.CommonBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;
import com.quadrolord.epicbattle.logic.town.resource.ResourceItem;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceEntity;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceItem;
import com.quadrolord.epicbattle.screen.MineBuildingScreen;
import com.quadrolord.epicbattle.view.town.building.MineView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 31.01.2016.
 */
public class Mine extends CommonBuildingEntity {

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

        ResourceSourceEntity rse = new ResourceSourceEntity();
        rse.setResourceClass(IronOre.class);
        rse.setMaxBalance(100);
        rse.setYieldTime(10);
        getResources().add(rse);
    }


    @Override
    public void initItem(CommonBuildingItem item) {
        initItemResources(item);
        item.setSize((int) mSize.x, (int) mSize.y);
    }

    @Override
    public void runOnSelect(AbstractBuildingItem item) {
        MineBuildingScreen scr = new MineBuildingScreen(item.getView().getScreen(), item);
        item.getView().getScreen().getAdapter().switchToScreen(scr, false);
    }

    /**
     * Забор накопленных ресурсов
     */
    public void takeAvailable(AbstractBuildingItem mine) {
        for (Iterator<ResourceSourceItem> it = mine.getResources().iterator(); it.hasNext(); ) {
            ResourceSourceItem rs = it.next();
            updateBalance(rs);
            long balance = rs.getCurrentBalance();
            rs.setLastYield(TimeUtils.millis());
            ResourceItem resource = mine.getTown().getResource(rs.getEntity().getResourceClass());
            resource.incValue(balance);
        }
    }

    public void updateBalance(ResourceSourceItem rs) {
        int deltaBalance = (int)((TimeUtils.millis() - rs.getLastYield()) * mProductionRate);
        long balance = Math.min(mMaxBalance, deltaBalance);
        rs.setCurrentBalance(balance);
    }

}

package com.quadrolord.epicbattle.logic.town.building;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.epicbattle.logic.configurable.AbstractEntity;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingItem;
import com.quadrolord.epicbattle.logic.town.building.leveling.AbstractStrategy;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceEntity;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceItem;
import com.quadrolord.epicbattle.logic.town.tile.Tile;
import com.quadrolord.epicbattle.screen.town.building.ConstructionBuildingScreen;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;

import java.util.Iterator;

/**
 * Created by morph on 17.01.2016.
 */
abstract public class AbstractBuildingEntity<T extends AbstractBuildingItem> extends AbstractEntity<T> {

    /**
     * Здание возможно снести
     */
    private boolean mAllowDestruction;

    /**
     * Здание возможно перемещать
     */
    private boolean mAllowMoving;

    protected String mTitle;

    /**
     * Количество занимаемых клеток
     */
    protected Vector2 mSize = new Vector2(1, 1);

    /**
     * Изображение на экране выборе постройки
     */
    protected String mSliderTexture;
    protected Class<? extends AbstractBuildingView> mViewClass;
    protected String mIcon;
    protected Class<? extends Tile> mTileClass;
    protected ArrayMap<Class<? extends AbstractThingEntity>, Integer> mRequiredResources = new ArrayMap<Class<? extends AbstractThingEntity>, Integer>();
    protected int mRequiredLevel = 1;
    private Array<ResourceSourceEntity> mResources = new Array<ResourceSourceEntity>();

    protected int mCostGem;
    protected long mConstructionTime;
    protected AbstractStrategy mLevelingStrategy;
    protected Array<AbstractBuildingEntity> mLevelUps = new Array<AbstractBuildingEntity>();

    /**
     * Доступные для заказа предметы
     */
    protected Array<AbstractThingEntity> mAvailableThings = new Array<AbstractThingEntity>();

    public boolean getAllowDestruction() {
        return mAllowDestruction;
    }

    public boolean getAllowMoving() {
        return mAllowMoving;
    }

    public Array<AbstractThingEntity> getAvailableThings() {
        return mAvailableThings;
    }

    public String getTitle() {
        return mTitle;
    }

    public Array<AbstractBuildingEntity> getLevelUps() {
        return mLevelUps;
    }

    public long getConstructionTime() {
        return mConstructionTime;
    }

    public Class<? extends Tile> getTileClass() {
        return mTileClass;
    }

    public String getIcon() {
        return mIcon;
    }

    public Class<? extends AbstractBuildingView> getViewClass() {
        return mViewClass;
    }

    public Vector2 getSize() {
        return mSize;
    }

    public String getSliderTextureFile() {
        return mSliderTexture;
    }

    public ArrayMap<Class<? extends AbstractThingEntity>, Integer> getRequiredResources() {
        return mRequiredResources;
    }

    public int getRequiredLevel() {
        return mRequiredLevel;
    }

    public Array<ResourceSourceEntity> getResources() {
        return mResources;
    }

    public int getCostGem() {
        return mCostGem;
    }

    public AbstractStrategy getLevelingStrategy() {
        return mLevelingStrategy;
    }

    /**
     * Выбор здания
     * @param item
     */
    public void runOnSelect(AbstractBuildingItem item) {

    }

    /**
     * Выбор здания в процессе постройки
     * @param item
     */
    public void runOnSelectUpdating(AbstractBuildingItem item) {
        ConstructionBuildingScreen scr = new ConstructionBuildingScreen(item.getView().getScreen(), item);
        item.getView().getScreen().getAdapter().switchToScreen(scr, false);
    }

    public void setAllowDestruction(boolean value) {
        mAllowDestruction = value;
    }

    public void setAllowMoving(boolean value) {
        mAllowMoving = value;
    }

    public AbstractBuildingEntity setConstructionTime(long time) {
        mConstructionTime = time;
        return this;
    }

    public AbstractBuildingEntity setTileClass(Class<? extends Tile> tileClass) {
        mTileClass = tileClass;
        return this;
    }

    public AbstractBuildingEntity setIcon(String icon)  {
        mIcon = icon;
        return this;
    }

    public AbstractBuildingEntity setViewClass(Class<? extends AbstractBuildingView> viewClass) {
        mViewClass = viewClass;
        return this;
    }

    public AbstractBuildingEntity setLevelUps(Array<AbstractBuildingEntity> levelUps) {
        mLevelUps = levelUps;
        return this;
    }

    public AbstractBuildingEntity setSize(Vector2 size) {
        mSize = size;
        return this;
    }

    public void setSliderTexture(String texture) {
        mSliderTexture = texture;
    }

    public AbstractBuildingEntity setRequiredResources(ArrayMap<Class<? extends AbstractThingEntity>, Integer> resources) {
        mRequiredResources = resources;
        return this;
    }

    public AbstractBuildingEntity setRequiredLevel(int level) {
        mRequiredLevel = level;
        return this;
    }

    public AbstractBuildingEntity setCostGem(int cost) {
        mCostGem = cost;
        return this;
    }

    public AbstractBuildingEntity setLevelingStrategy(AbstractStrategy strategy) {
        mLevelingStrategy = strategy;
        return this;
    }

    public AbstractBuildingEntity setTitle(String title) {
        mTitle = title;
        return this;
    }

    public void setLevel(int level) {
        getLevelingStrategy().setLevel(this, level);
    }

    /**
     * Забор накопленных ресурсов
     */
    public void takeAvailable(AbstractBuildingItem mine) {
        for (Iterator<ResourceSourceItem> it = mine.getResources().iterator(); it.hasNext(); ) {
            ResourceSourceItem rs = it.next();
            updateBalance(rs);
            int balance = rs.getCurrentBalance();
            rs.setLastYield(TimeUtils.millis());
            ThingItem resource = mine.getTown().getResource(rs.getInfo().getResourceClass());
            resource.incCount(balance);
        }
    }

    public void updateBalanceFull(AbstractBuildingItem mine) {
        for (Iterator<ResourceSourceItem> it = mine.getResources().iterator(); it.hasNext(); ) {
            ResourceSourceItem rs = it.next();
            updateBalance(rs);
        }
    }

    public void updateBalance(ResourceSourceItem rs) {
        float pr = rs.getInfo().getProductionRate();
        int deltaBalance = (int)((TimeUtils.millis() - rs.getLastYield()) * pr * .001f);
        int balance = Math.min(rs.getInfo().getMaxBalance(), deltaBalance);
        rs.setCurrentBalance(balance);
    }

}

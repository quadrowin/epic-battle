package com.quadrolord.epicbattle.logic.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.profile.PlayerProfile;
import com.quadrolord.epicbattle.logic.profile.ProfileBuilding;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingCostElement;
import com.quadrolord.epicbattle.logic.thing.ThingItem;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;
import com.quadrolord.epicbattle.logic.town.building.entity.DoodleShop;
import com.quadrolord.epicbattle.logic.town.building.entity.Mine;
import com.quadrolord.epicbattle.logic.town.building.entity.SheepFarm;
import com.quadrolord.epicbattle.logic.town.building.entity.Smithy;
import com.quadrolord.epicbattle.logic.town.building.entity.Warehouse;
import com.quadrolord.epicbattle.logic.town.tile.Tile;

import java.util.Iterator;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTown {

    public static final int MAP_CELL_WIDTH = 60;
    public static final int MAP_CELL_HEIGHT = 40;

    public static final int MAP_SIZE_X = 10;
    public static final int MAP_SIZE_Y = 10;

    private Game mGame;

    private Array<AbstractBuildingItem> mBuildings = new Array<AbstractBuildingItem>();
    private Tile[][] mMap;

    private int mLevel = 1;
    private int mGemsCount = 1;

    private ArrayMap<Class<? extends AbstractThingEntity>, ThingItem> mResources = new ArrayMap<Class<? extends AbstractThingEntity>, ThingItem>();
    private BuildingInfoManager mBuildingInfoManager;

    private TownListener mListener;

    private float mTime = 0;

    public MyTown(Game game) {
        mGame = game;
        mBuildingInfoManager = new BuildingInfoManager();
    }

    public void act(float delta) {
        mTime += delta;
    }

    /**
     * Возвращает типы зданий, которые можно построить
     * @return
     */
    public Array<AbstractBuildingEntity> getAvailableBuildingTypes() {
        Array<AbstractBuildingEntity> bts = new Array<AbstractBuildingEntity>();
        bts.add(mBuildingInfoManager.getInfo(DoodleShop.class));
        bts.add(mBuildingInfoManager.getInfo(Mine.class));
        bts.add(mBuildingInfoManager.getInfo(SheepFarm.class));
        bts.add(mBuildingInfoManager.getInfo(DoodleShop.class));
        bts.add(mBuildingInfoManager.getInfo(Smithy.class));
        bts.add(mBuildingInfoManager.getInfo(Warehouse.class));
        return bts;
    }

    public Array<AbstractBuildingItem> getBuildings() {
        return mBuildings;
    }

    public BuildingInfoManager getBuildingInfoManager() {
        return mBuildingInfoManager;
    }

    public Tile getMapCell(int col, int row) {
        Gdx.app.log("getMapCell", " " + mMap.length + " " + mMap[col].length + " at " + col + " " + row);
        if (col < 0 || row < 0 || col >= mMap.length || row >= mMap[col].length) {
            return null;
        }
        return mMap[col][row];
    }

    public boolean hasGems(int gemsCount) {
        return mGemsCount >= gemsCount;
    }

    public boolean hasLevel(int level) {
        return mLevel >= level;
    }

    public boolean hasResources(AbstractBuildingEntity building) {
        Iterator<ObjectMap.Entry<Class<? extends AbstractThingEntity>, Integer>> iter = building.getRequiredResources().iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Class<? extends AbstractThingEntity>, Integer> next = iter.next();
            Class<? extends AbstractThingEntity> resourceClass = next.key;
            int cost = next.value;

            if (!mResources.containsKey(resourceClass) || mResources.get(resourceClass).getCount() < cost) {
                return false;
            }
        }

        return true;
    }

    public boolean canBuild(AbstractBuildingEntity entity, int col, int row) {
        if (col < 0 || row < 0) {
            return false;
        }
        for (int i = col; i < col + entity.getSize().x; i++) {
            for (int j = row; j < row + entity.getSize().y; j++) {
                if (
                        i >= mMap.length || j >= mMap[i].length
                        || mMap[i] == null || mMap[i][j] == null
                        || !mMap[i][j].isFree()
                ) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canBuildItem(AbstractBuildingItem building, int col, int row) {
        if (col < 0 || row < 0 || col >= mMap.length || row >= mMap[col].length) {
            return false;
        }
        Tile cell = mMap[col][row];
        if (cell == null) {
            return true;
        }
        return cell.isFree() || cell.getBuilding() == building;
    }

    public void cancelBuildingMode() {
        mListener.onCancelBuildingMode();
    }

    public void confirmBuilding() {
        mListener.onConfirmBuilding();
    }

    public AbstractBuildingItem build(Class<? extends AbstractBuildingEntity> entityClass, int col, int row, boolean isRotated, boolean takeResources, boolean takeGems) {
        return build(mBuildingInfoManager.getInfo(entityClass), col, row, isRotated, takeResources, takeGems);
    }

    public AbstractBuildingItem build(AbstractBuildingEntity entity, int col, int row, boolean isRotated, boolean takeResources, boolean takeGems) {
        if (takeGems && !hasGems(entity.getCostGem())) {
            mListener.onUserActionFail(TownListener.BuildingAction.CREATE_NO_GEMS);
            return null;
        }
        if (takeResources && !hasResources(entity)) {
            mListener.onUserActionFail(TownListener.BuildingAction.CREATE_NO_RESOURCES);
            return null;
        }

        if (!hasLevel(entity.getRequiredLevel())) {
            mListener.onUserActionFail(TownListener.BuildingAction.CREATE_NO_LEVEL);
            return null;
        }

        if (!canBuild(entity, col, row)) {
            mListener.onUserActionFail(TownListener.BuildingAction.CREATE_NO_PLACE);
//            return null;
        }

        takeAwayResources(entity);

        AbstractBuildingItem item = instantiateBuilding(entity);
        item.setX(col);
        item.setY(row);

        if (isRotated && !item.isRotated()) {
            item.rotate();
        }

        mBuildings.add(item);
        if (takeResources || takeGems) {
            // TODO separated flag for construction start required.
            item.startConstruction();
        }

        for (int i = col; i < col + item.getWidth(); i++) {
            for (int j = row; j < row + item.getHeight(); j++) {
                if (mMap[i][j] == null) {
                    mMap[i][j] = new Tile();
                }
                mMap[i][j].markAsBusy(item);
            }
        }

        mListener.onBuildingAdd(item);
        return item;
    }

    public void enterBuildingMode(AbstractBuildingEntity be) {
        mListener.onEnterBuildingMode(be);
    }

    public void enterMovingMode(AbstractBuildingItem b) {
        mListener.onEnterBuildingMode(b);
    }

    public ThingItem getResource(Class<? extends AbstractThingEntity> resourceClass) {
        if (!mResources.containsKey(resourceClass)) {
            ThingItem resource = new ThingItem();

            AbstractThingEntity info;
            try {
                info = resourceClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            resource.setInfo(info);

            mResources.put(resourceClass, resource);
            return resource;
        }
        return mResources.get(resourceClass);
    }

    public AbstractBuildingItem instantiateBuilding(AbstractBuildingEntity buildingInfo) {
        AbstractBuildingItem item;
        try {
            item = (AbstractBuildingItem)buildingInfo.getItemClass().getConstructor(MyTown.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        item.setInfo(buildingInfo);
        buildingInfo.initItem(item);
        return item;
    }

    public void setListener(TownListener listener) {
        mListener = listener;
    }

    /**
     * Забор ресурсов за постройку
     * @param entity
     */
    public void takeAwayResources(AbstractBuildingEntity entity) {
        Iterator<ObjectMap.Entry<Class<? extends AbstractThingEntity>, Integer>> iter = entity.getRequiredResources().iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<Class<? extends AbstractThingEntity>, Integer> next = iter.next();
            Class<? extends AbstractThingEntity> resourceClass = next.key;
            int cost = next.value;

            if (mResources.containsKey(resourceClass)) {
                ThingItem res = mResources.get(resourceClass);
                res.incCount(-cost);
            }
        }
    }

    public void levelUp(AbstractBuildingItem building, boolean isByGems) {
        AbstractBuildingEntity info = building.getInfo();
        boolean hasResources = isByGems ? hasGems(info.getCostGem()) : hasResources(info);

        if (!hasResources) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_NO_RESOURCES);
            return;
        }

        if (building.isInConstruction()) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_IN_UPDATING);
            return;
        }

        if (!hasLevel(info.getRequiredLevel())) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_BAD_USER_LEVEL);
            return;
        }

        if (!building.canLevelUp()) {
            mListener.onUserActionFail(TownListener.BuildingAction.LEVEL_ALREADY_MAX);
            return;
        }

        takeAwayResources(info);
        building.levelUp();
        mListener.onBuildingChange(building);
    }

    public void loadTown() {
        PlayerProfile profile = mGame.getProfileManager().getProfile();

        if (mMap == null) {
            mMap = new Tile[MAP_SIZE_X][MAP_SIZE_Y];
        } else {
            for (int x = 0; x < mMap.length; x++) {
                for (int y = 0; y < mMap[x].length; y++) {
                    mMap[x][y] = null;
                }
            }
        }

        for (Iterator<ProfileBuilding> it = profile.getBuildings().iterator(); it.hasNext(); ) {
            ProfileBuilding pb = it.next();
            Class<? extends AbstractBuildingEntity> buildingClass;
            try {
                buildingClass = (Class<? extends AbstractBuildingEntity>)Class.forName(pb.getBuildingName());
            } catch (Exception e) {
                continue;
            }
            build(
                    buildingClass,
                    pb.getX(),
                    pb.getY(),
                    pb.isRotated(),
                    false,
                    false
            );
        }
    }

    public void demolish(AbstractBuildingItem building) {
        mBuildings.removeValue(building, true);
        mListener.onBuildingRemove(building);
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public void setSelected(int col, int row) {
        for (int i = 0; i < mBuildings.size; i++) {
            AbstractBuildingItem b = mBuildings.get(i);
            if (col == b.getX() && row == b.getY()) {
                mListener.onBuildingSelect(b);
                if (b.isInConstruction()) {
                    b.getInfo().runOnSelectUpdating(b);
                } else {
                    b.getInfo().runOnSelect(b);
                }
            }
        }
    }

    public void tryOrderThing(AbstractBuildingItem building, AbstractThingEntity thing) {
        for (Iterator<ThingCostElement> it = thing.getCost().getResources().iterator(); it.hasNext();) {
            ThingCostElement el = it.next();
            ThingItem ri = mResources.get(el.getResource());
            if (ri == null || ri.getCount() < el.getCount()) {
                // недостаток одного из ресурсов
                mListener.onOrderResourceLack(el);
                //return;
            }
        }

        CraftPlanItem cpi = new CraftPlanItem();
        cpi.setCreated(mGame.getGameMillis());
        cpi.setThing(thing);
        building.getCraftPlan().add(cpi);

        mListener.onThingAddToPlan(building, cpi);
    }

}

package com.quadrolord.epicbattle.logic.town.building.leveling;

/**
 * Created by morph on 17.01.2016.
 */
public class LevelingDto {
    protected int mCostMoney;
    protected int mCostGem;
    protected int mConstructionTime;
    protected int mProductionTime;

    public int getCostMoney() {
        return mCostMoney;
    }

    public LevelingDto setCostMoney(int costMoney) {
        this.mCostMoney = costMoney;
        return this;
    }

    public int getCostGem() {
        return mCostGem;
    }

    public LevelingDto setCostGem(int costGem) {
        this.mCostGem = costGem;
        return this;
    }

    public int getConstructionTime() {
        return mConstructionTime;
    }

    public LevelingDto setConstructionTime(int constructionTime) {
        this.mConstructionTime = constructionTime;
        return this;
    }

    public int getProductionTime() {
        return mProductionTime;
    }

    public LevelingDto setProductionTime(int productionTime) {
        this.mProductionTime = productionTime;
        return this;
    }
}

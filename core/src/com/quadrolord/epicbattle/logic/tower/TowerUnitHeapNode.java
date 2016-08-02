package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.utils.BinaryHeap;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;

/**
 * Created by Goorus on 02.08.2016.
 */
public class TowerUnitHeapNode extends BinaryHeap.Node {

    private GameUnit mUnit;

    public TowerUnitHeapNode(GameUnit unit) {
        super(unit.getX());
        mUnit = unit;
    }

    public GameUnit getUnit() {
        return mUnit;
    }

}

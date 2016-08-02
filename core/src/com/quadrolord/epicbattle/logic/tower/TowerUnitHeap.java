package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.utils.BinaryHeap;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.TowerUnitHeapNode;

import java.util.HashMap;

/**
 * Created by Goorus on 02.08.2016.
 */
public class TowerUnitHeap {

    private BinaryHeap<TowerUnitHeapNode> mHeap = new BinaryHeap<TowerUnitHeapNode>();

    private HashMap<GameUnit, TowerUnitHeapNode> mNodeIndex = new HashMap<GameUnit, TowerUnitHeapNode>();

    public void act(GameUnit unit) {
        TowerUnitHeapNode node = mNodeIndex.get(unit);
        mHeap.setValue(node, unit.getX());
    }

    public void add(GameUnit unit) {
        TowerUnitHeapNode node = new TowerUnitHeapNode(unit);
        mNodeIndex.put(unit, node);
        mHeap.add(node, unit.getX());
    }

    public void remove(GameUnit unit) {
        TowerUnitHeapNode node = mNodeIndex.get(unit);
        mHeap.remove(node);
        mNodeIndex.remove(unit);
    }

}

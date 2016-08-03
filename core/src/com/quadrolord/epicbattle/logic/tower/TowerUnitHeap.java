package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.utils.BinaryHeap;
import com.badlogic.gdx.utils.StringBuilder;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.TowerUnitHeapNode;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Goorus on 02.08.2016.
 */
public class TowerUnitHeap {

    private HashMap<GameUnit, TowerUnitHeapNode> mNodeIndex = new HashMap<GameUnit, TowerUnitHeapNode>();

    private int size;

    private TowerUnitHeapNode[] nodes;
    private final boolean isMaxHeap;

    public TowerUnitHeap() {
        this(16, false);
    }

    public TowerUnitHeap(int capacity, boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
        nodes = new TowerUnitHeapNode[capacity];
    }

    public TowerUnitHeapNode add(TowerUnitHeapNode node) {
        // Expand if necessary.
        if (size == nodes.length) {
            TowerUnitHeapNode[] newNodes = new TowerUnitHeapNode[size << 1];
            System.arraycopy(nodes, 0, newNodes, 0, size);
            nodes = newNodes;
        }
        // Insert at end and bubble up.
        node.index = size;
        nodes[size] = node;
        up(size++);
        return node;
    }

    public TowerUnitHeapNode add(TowerUnitHeapNode node, float value) {
        node.value = value;
        return add(node);
    }

    public void add(GameUnit unit) {
        TowerUnitHeapNode node = new TowerUnitHeapNode(unit);
        mNodeIndex.put(unit, node);
        add(node);
    }

    public GameUnit get(int index) {
        return nodes[index].getUnit();
    }

    public int getSize() {
        return size;
    }

    public TowerUnitHeapNode peek () {
        if (size == 0) throw new IllegalStateException("The heap is empty.");
        return nodes[0];
    }

    public TowerUnitHeapNode pop () {
        return remove(0);
    }

    public TowerUnitHeapNode remove(TowerUnitHeapNode node) {
        return remove(node.index);
    }

    public void remove(GameUnit unit) {
        TowerUnitHeapNode node = mNodeIndex.get(unit);
        remove(node.index);
        mNodeIndex.remove(unit);
    }

    private TowerUnitHeapNode remove(int index) {
        TowerUnitHeapNode[] nodes = this.nodes;
        TowerUnitHeapNode removed = nodes[index];
        nodes[index] = nodes[--size];
        nodes[size] = null;
        if (size > 0 && index < size) down(index);
        return removed;
    }

    public void clear() {
        TowerUnitHeapNode[] nodes = this.nodes;
        for (int i = 0, n = size; i < n; i++)
            nodes[i] = null;
        size = 0;
    }

    public void setValue (TowerUnitHeapNode node, float value) {
        float oldValue = node.value;
        node.value = value;
        if (value < oldValue ^ isMaxHeap)
            up(node.index);
        else
            down(node.index);
    }

    private void up (int index) {
        TowerUnitHeapNode[] nodes = this.nodes;
        TowerUnitHeapNode node = nodes[index];
        float value = node.value;
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            TowerUnitHeapNode parent = nodes[parentIndex];
            if (value < parent.value ^ isMaxHeap) {
                nodes[index] = parent;
                parent.index = index;
                index = parentIndex;
            } else
                break;
        }
        nodes[index] = node;
        node.index = index;
    }

    private void down (int index) {
        TowerUnitHeapNode[] nodes = this.nodes;
        int size = this.size;

        TowerUnitHeapNode node = nodes[index];
        float value = node.value;

        while (true) {
            int leftIndex = 1 + (index << 1);
            if (leftIndex >= size) break;
            int rightIndex = leftIndex + 1;

            // Always have a left child.
            TowerUnitHeapNode leftNode = nodes[leftIndex];
            float leftValue = leftNode.value;

            // May have a right child.
            TowerUnitHeapNode rightNode;
            float rightValue;
            if (rightIndex >= size) {
                rightNode = null;
                rightValue = isMaxHeap ? Float.MIN_VALUE : Float.MAX_VALUE;
            } else {
                rightNode = nodes[rightIndex];
                rightValue = rightNode.value;
            }

            // The smallest of the three values is the parent.
            if (leftValue < rightValue ^ isMaxHeap) {
                if (leftValue == value || (leftValue > value ^ isMaxHeap)) break;
                nodes[index] = leftNode;
                leftNode.index = index;
                index = leftIndex;
            } else {
                if (rightValue == value || (rightValue > value ^ isMaxHeap)) break;
                nodes[index] = rightNode;
                rightNode.index = index;
                index = rightIndex;
            }
        }

        nodes[index] = node;
        node.index = index;
    }

    @Override
    public boolean equals (Object obj) {
        if (!(obj instanceof BinaryHeap)) return false;
        TowerUnitHeap other = (TowerUnitHeap)obj;
        if (other.size != size) return false;
        for (int i = 0, n = size; i < n; i++)
            if (other.nodes[i].value != nodes[i].value) return false;
        return true;
    }

    public int hashCode () {
        int h = 1;
        for (int i = 0, n = size; i < n; i++)
            h = h * 31 + Float.floatToIntBits(nodes[i].value);
        return h;
    }

    public String toString () {
        if (size == 0) return "[]";
        TowerUnitHeapNode[] nodes = this.nodes;
        StringBuilder buffer = new StringBuilder(32);
        buffer.append('[');
        buffer.append(nodes[0].value);
        for (int i = 1; i < size; i++) {
            buffer.append(", ");
            buffer.append(nodes[i].value);
        }
        buffer.append(']');
        return buffer.toString();
    }

    public void act(GameUnit unit) {
        TowerUnitHeapNode node = mNodeIndex.get(unit);
        setValue(node, unit.getX());
    }

    public Iterator<GameUnit> iterator() {
        final TowerUnitHeap self = this;
        Iterator<GameUnit> it = new Iterator<GameUnit>() {

            private int mIteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return mIteratorIndex < self.size;
            }

            @Override
            public GameUnit next() {
                return self.nodes[mIteratorIndex++].getUnit();
            }

            @Override
            public void remove() {
                self.remove(mIteratorIndex);
            }

        };

        return it;
    }

}

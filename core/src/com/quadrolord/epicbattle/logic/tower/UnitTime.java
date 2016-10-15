package com.quadrolord.epicbattle.logic.tower;

/**
 * Created by Goorus on 15.10.2016.
 */
public class UnitTime {

    /**
     * Game unit exists time
     */
    public float existsTime;

    /**
     * Unit time in current state (from 0 to infinite)
     */
    public float stateTime;

    /**
     * Unit animation part. From 0 to 1. May be greater than 1, in using mod 1 required.
     */
    public float statePart;

}

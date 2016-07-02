package com.quadrolord.epicbattle.logic.tower.controller;

import com.badlogic.gdx.utils.Queue;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 12.01.2016.
 */
public class ControllerPlayer extends AbstractController {

    private Queue<SkillItem> mFireCalls = new Queue<SkillItem>();

    public ControllerPlayer(BattleGame game) {
        super(game);
    }

    @Override
    public void act(float delta) {
        for (; mFireCalls.size > 0; ) {
            SkillItem skill = mFireCalls.removeFirst();
            getGame().createUnit(getTower(), skill, true, true);
        }
    }

    public void callFire(SkillItem bulletSkill) {
        mFireCalls.addLast(bulletSkill);
    }

    public void reset() {
        mFireCalls.clear();
    }

}

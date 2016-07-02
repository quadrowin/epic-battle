package com.quadrolord.epicbattle.logic.skill;

import com.badlogic.gdx.Gdx;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.tower.Tower;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Quadrowin on 25.01.2016.
 */
abstract public class BulletSkill <T extends AbstractBullet> extends AbstractSkillEntity {

    private BulletInfo mBullet;

    public BulletInfo getBullet() {
        return mBullet;
    }

    @Override
    public String getDescription()
    {
        Gdx.app.log("bullet desc", mBullet.getDescription());
        return mBullet.getDescription();
    }

    @Override
    public void initSkill(BattleGame game)
    {
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        Class parameter = (Class)type.getActualTypeArguments()[0];
        mBullet = game.getBulletInfoManager().getBulletInfo(parameter);
        setDescription("Summon a battle unit.");
        setIcon(mBullet.getIcon());
        setName(mBullet.getTitle());
    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(SkillItem skill, Tower tower) {
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        Class parameter = (Class)type.getActualTypeArguments()[0];
        tower.addBulletSkill(parameter, skill.getLevel());
    }

}

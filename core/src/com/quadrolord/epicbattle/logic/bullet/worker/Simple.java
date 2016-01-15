package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.BulletInfoDto;
import com.quadrolord.epicbattle.logic.bullet.BulletLevelUp;
import com.quadrolord.epicbattle.logic.bullet.BulletLevelUpDto;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Simple extends AbstractBullet {

    public static final String TITLE = "Simple";
    public static final int COST = 100;
    public static final float CONSTRUCTION_TIME = 3;
    public static final float ATTACK_DAMAGE = 50;
    public static final float ATTACK_DISTANCE = 1;
    public static final float ATTACK_TIME = 1;
    public static final float MOVE_SPEED = 50;
    public static final int TARGET_COUNT = 1;
    public static final int MAX_HP = 100;

    public Simple(Game game) {
        super(game);
    }

    @Override
    public void initInfo(BulletInfo info) {
        BulletInfoDto dto = new BulletInfoDto();

        dto.
                setTitle(TITLE).
                setCost(COST).
                setConstructionTime(CONSTRUCTION_TIME).
                setAttackDamage(ATTACK_DAMAGE).
                setAttackDistance(ATTACK_DISTANCE).
                setAttackTime(ATTACK_TIME).
                setMoveSpeed(MOVE_SPEED).
                setMaxTargetCount(TARGET_COUNT).
                setMaxHp(MAX_HP).
                setViewClass(com.quadrolord.epicbattle.view.worker.Simple.class).
                setBulletClass(Simple.class).
                setIcon(new Texture("icons/units/simple.png"));

        info.setInfo(dto);

        info.setLevelUps(new Array<BulletLevelUp>(new BulletLevelUp[]{
                new BulletLevelUp() {
                    @Override
                    public BulletLevelUpDto action() {
                        return getDdo().setMaxHpDelta(10).setAttackDamageDelta(5);
                    }
                },
                new BulletLevelUp() {
                    @Override
                    public BulletLevelUpDto action() {
                        return getDdo().setMaxHpDelta(20).setAttackDamageDelta(10);
                    }
                },
                new BulletLevelUp() {
                    @Override
                    public BulletLevelUpDto action() {
                        return getDdo().setMaxHpDelta(25).setAttackDamageDelta(15);
                    }
                },
                new BulletLevelUp() {
                    @Override
                    public BulletLevelUpDto action() {
                        return getDdo().setMaxHpDelta(25).setAttackDamageDelta(15);
                    }
                },
                new BulletLevelUp() {
                    @Override
                    public BulletLevelUpDto action() {
                        return getDdo().setMaxHpDelta(30).setAttackDamageDelta(20);
                    }
                },
                new BulletLevelUp() {
                    @Override
                    public BulletLevelUpDto action() {
                        return getDdo().setMaxHpDelta(30).setAttackDamageDelta(20);
                    }
                },
                new BulletLevelUp() {
                    @Override
                    public BulletLevelUpDto action() {
                        return getDdo().setMaxHpDelta(35).setAttackDamageDelta(25);
                    }
                }
        }));
    }
}

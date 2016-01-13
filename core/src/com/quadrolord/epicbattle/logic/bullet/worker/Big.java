package com.quadrolord.epicbattle.logic.bullet.worker;

import com.badlogic.gdx.graphics.Texture;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.BulletInfoDto;

/**
 * Created by Quadrowin on 09.01.2016.
 */
public class Big extends AbstractBullet {

    public static final String TITLE = "Big";
    public static final int COST = 500;
    public static final float CONSTRUCTION_TIME = 10;
    public static final float ATTACK_DAMAGE = 100;
    public static final float ATTACK_DISTANCE = 2;
    public static final float ATTACK_TIME = 2;
    public static final float MOVE_SPEED = 30;
    public static final int TARGET_COUNT = 2;
    public static final int MAX_HP = 500;

    public Big(Game game) {
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
                setViewClass(com.quadrolord.epicbattle.view.worker.Big.class).
                setBulletClass(Big.class).
                setIcon(new Texture("icons/units/big.png"));

        info.setInfo(dto);
    }

}

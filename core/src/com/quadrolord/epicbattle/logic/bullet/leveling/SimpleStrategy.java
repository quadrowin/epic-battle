package com.quadrolord.epicbattle.logic.bullet.leveling;

import com.quadrolord.epicbattle.logic.bullet.BulletInfo;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class SimpleStrategy extends AbstractStrategy {

    @Override
    public void setLevel(BulletInfo info, int level) {
        for (int i = 2; i <= level; i++) {

            LevelingDto dto = info.getLevelUps().get(i - 2);

            if (dto == null) {
                continue;
            }

            if (dto.getBulletClass() != null) {
                info.setBulletClass(dto.getBulletClass());
            }

            if (dto.getTitle() != "") {
                info.setTitle(dto.getTitle());
            }

            if (dto.getIcon() != null) {
                info.setIcon(dto.getIcon());
            }

            if (dto.getViewClass() != null) {
                info.setViewClass(dto.getViewClass());
            }

            info.setAttackDamage(info.getAttackDamage() + dto.getAttackDamageDelta());
            info.setAttackTime(info.getAttackTime() + dto.getAttackTimeDelta());
            info.setAttackDistance(info.getAttackDistance() + dto.getAttackDistanceDelta());
            info.setMaxHp(info.getMaxHp() + dto.getMaxHpDelta());
            info.setMoveSpeed(info.getMoveSpeed() + dto.getMoveSpeedDelta());
            info.setMaxTargetCount(info.getMaxTargetCount() + dto.getMaxTargetCountDelta());
            info.setCost(info.getCost() + dto.getCostDelta());
            info.setConstructionTime(info.getConstructionTime() + dto.getConstructionTimeDelta());
        }
    }
}

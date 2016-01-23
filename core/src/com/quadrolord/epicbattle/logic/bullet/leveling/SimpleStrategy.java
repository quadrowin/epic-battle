package com.quadrolord.epicbattle.logic.bullet.leveling;

import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class SimpleStrategy extends AbstractStrategy {

    @Override
    public void setLevel(BulletSkill skill, int level) {
        BulletInfo info = skill.getInfo();
        for (int i = 2; i <= level; i++) {

            LevelingDto dto = info.getLevelUps().get(i - 2);

            if (dto == null) {
                continue;
            }

            if (dto.getBulletClass() != null) {
                skill.setBulletClass(dto.getBulletClass());
            }

            if (dto.getTitle() != "") {
                skill.setTitle(dto.getTitle());
            }

            if (dto.getIcon() != null) {
                skill.setIcon(dto.getIcon());
            }

            if (dto.getViewClass() != null) {
                skill.setViewClass(dto.getViewClass());
            }

            skill.setAttackDamage(info.getAttackDamage() + dto.getAttackDamageDelta());
            skill.setAttackTime(info.getAttackTime() + dto.getAttackTimeDelta());
            skill.setAttackDistance(info.getAttackDistance() + dto.getAttackDistanceDelta());
            skill.setMaxHp(info.getMaxHp() + dto.getMaxHpDelta());
            skill.setMoveSpeed(info.getMoveSpeed() + dto.getMoveSpeedDelta());
            skill.setMaxTargetCount(info.getMaxTargetCount() + dto.getMaxTargetCountDelta());
            skill.setCost(info.getCost() + dto.getCostDelta());
            skill.setConstructionTime(info.getConstructionTime() + dto.getConstructionTimeDelta());
        }
    }
}

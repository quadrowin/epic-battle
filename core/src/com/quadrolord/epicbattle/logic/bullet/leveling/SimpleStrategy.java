package com.quadrolord.epicbattle.logic.bullet.leveling;

import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class SimpleStrategy extends AbstractStrategy {

    @Override
    public void setLevel(AbstractBulletSkill skill, int level) {
        AbstractLogic info = skill.getBulletLogic();
        for (int i = 2; i <= level; i++) {

            if (info.getLevelUps().size <= i - 2) {
                continue;
            }

            LevelingDto dto = (LevelingDto)info.getLevelUps().get(i - 2);

            if (dto == null) {
                continue;
            }

            if (dto.getTitle() != "") {
                skill.setName(dto.getTitle());
            }

            if (dto.getIcon() != null) {
                skill.setIcon(dto.getIcon());
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

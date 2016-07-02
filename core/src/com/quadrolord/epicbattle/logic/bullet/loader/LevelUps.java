package com.quadrolord.epicbattle.logic.bullet.loader;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.bullet.leveling.LevelingDto;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;

/**
 * Created by Quadrowin on 23.01.2016.
 */
public class LevelUps extends AbstractLoader {
    @Override
    public void assign(AbstractLogic info, JsonValue data) {
        Array<LevelingDto> levelUps = new Array<LevelingDto>();
        JsonValue levelingJson = data;

        for (JsonValue levelJson : levelingJson.iterator()) {

            LevelingDto dto = new LevelingDto();

            if (levelJson.has("title")) {
                dto.setTitle(levelJson.getString("title"));
            }

            if (levelJson.has("icon")) {
                dto.setIcon(levelJson.getString("icon"));
            }

            if (levelJson.has("attack_damage")) {
                dto.setAttackDamageDelta(levelJson.getFloat("attack_damage"));
            }

            if (levelJson.has("attack_distance")) {
                dto.setAttackDistanceDelta(levelJson.getFloat("attack_distance"));
            }

            if (levelJson.has("attack_time")) {
                dto.setAttackTimeDelta(levelJson.getFloat("attack_time"));
            }

            if (levelJson.has("construction_time")) {
                dto.setConstructionTimeDelta(levelJson.getFloat("construction_time"));
            }

            if (levelJson.has("cost")) {
                dto.setCostDelta(levelJson.getInt("cost"));
            }

            if (levelJson.has("max_hp")) {
                dto.setMaxHpDelta(levelJson.getInt("max_hp"));
            }

            if (levelJson.has("target_count")) {
                dto.setMaxTargetCountDelta(levelJson.getInt("target_count"));
            }

            if (levelJson.has("move_speed")) {
                dto.setMoveSpeedDelta(levelJson.getFloat("move_speed"));
            }

            levelUps.add(dto);
        }

        info.setLevelUps(levelUps);
    }
}

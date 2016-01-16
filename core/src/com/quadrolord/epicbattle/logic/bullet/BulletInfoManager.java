package com.quadrolord.epicbattle.logic.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.bullet.leveling.LevelingDto;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;

/**
 * Created by morph on 16.01.2016.
 */
public class BulletInfoManager {
    private JsonReader mReader;

    public BulletInfoManager() {
        mReader = new JsonReader();
    }

    public BulletInfo getBulletInfo(Class<? extends AbstractBullet> bulletClass) {
        String fileName = "config/units/" + bulletClass.getSimpleName() + ".json";
        BulletInfo info = new BulletInfo();
        JsonValue json;

        Gdx.app.log("bullets", "Loaded unit config: " + fileName);

        try {
            json = mReader.parse(Gdx.files.internal(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Cannot found config file: " + fileName);
        }

        if (!json.has("title")) {
            throw new RuntimeException("Empty unit name");
        }

        if (json.has("icon")) {
            info.setIcon(new Texture(Gdx.files.internal(json.getString("icon"))));
        }

        info.setTitle(json.getString("title"));
        info.setAttackDamage(json.getFloat("attack_damage"));
        info.setAttackDistance(json.getFloat("attack_distance"));
        info.setAttackTime(json.getFloat("attack_time"));
        info.setConstructionTime(json.getFloat("construction_time"));
        info.setCost(json.getInt("cost"));
        info.setMaxHp(json.getInt("max_hp"));
        info.setMoveSpeed(json.getFloat("move_speed"));
        info.setMaxTargetCount(json.getInt("target_count"));

        Array<LevelingDto> levelUps = new Array<LevelingDto>();
        JsonValue levelingJson = json.get("level_ups");

        for (JsonValue levelJson : levelingJson.iterator()) {

            LevelingDto dto = new LevelingDto();

            if (levelJson.has("title")) {
                dto.setTitle(levelJson.getString("title"));
            }

            if (levelJson.has("icon")) {
                dto.setIcon(new Texture(Gdx.files.internal(levelJson.getString("icon"))));
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

        return info;
    }
}

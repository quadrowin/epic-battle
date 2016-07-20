package com.quadrolord.epicbattle.logic.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.quadrolord.epicbattle.logic.bullet.loader.AbstractLoader;
import com.quadrolord.epicbattle.logic.bullet.loader.AttackDamage;
import com.quadrolord.epicbattle.logic.bullet.loader.AttackDistance;
import com.quadrolord.epicbattle.logic.bullet.loader.AttackTime;
import com.quadrolord.epicbattle.logic.bullet.loader.ConstructionTime;
import com.quadrolord.epicbattle.logic.bullet.loader.Cost;
import com.quadrolord.epicbattle.logic.bullet.loader.Height;
import com.quadrolord.epicbattle.logic.bullet.loader.Icon;
import com.quadrolord.epicbattle.logic.bullet.loader.LevelUps;
import com.quadrolord.epicbattle.logic.bullet.loader.MaxHp;
import com.quadrolord.epicbattle.logic.bullet.loader.MaxTargetCount;
import com.quadrolord.epicbattle.logic.bullet.loader.MoveSpeed;
import com.quadrolord.epicbattle.logic.bullet.loader.Title;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;

/**
 * Created by morph on 16.01.2016.
 */
public class BulletInfoManager {

    private ArrayMap<String, AbstractLoader> mLoaders;

    private ArrayMap<Class<? extends AbstractLogic>, AbstractLogic> mLogics;

    private JsonReader mReader;

    public BulletInfoManager() {
        mReader = new JsonReader();
        mLogics = new ArrayMap<Class<? extends AbstractLogic>, AbstractLogic>();

        mLoaders = new ArrayMap<String, AbstractLoader>();
        mLoaders.put("attack_damage", new AttackDamage());
        mLoaders.put("attack_distance", new AttackDistance());
        mLoaders.put("attack_time", new AttackTime());
        mLoaders.put("construction_time", new ConstructionTime());
        mLoaders.put("cost", new Cost());
        mLoaders.put("icon", new Icon());
        mLoaders.put("level_ups", new LevelUps());
        mLoaders.put("max_hp", new MaxHp());
        mLoaders.put("max_target_count", new MaxTargetCount());
        mLoaders.put("move_speed", new MoveSpeed());
        mLoaders.put("title", new Title());
        mLoaders.put("height", new Height());
    }

    public ArrayMap<Class<? extends AbstractLogic>, AbstractLogic> getAllLogics() {
        return mLogics;
    }

    public AbstractLogic getBulletLogic(Class<? extends AbstractLogic> bulletClass) {
        AbstractLogic bl = mLogics.get(bulletClass);

        if (bl != null) {
            return bl;
        }

        String logicName = bulletClass.getSimpleName(); // LalalaLogic
        String fileName = "config/units/" + logicName.substring(0, logicName.length() - 5) + ".json";

        try {
            bl = bulletClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

        for (JsonValue.JsonIterator it = json.iterator(); it.hasNext(); ) {
            JsonValue val = it.next();
            String name = val.name();
            if (mLoaders.containsKey(name)) {
                mLoaders.get(name).assign(bl, val);
            } else {
                Gdx.app.log(BulletInfoManager.class.getName(), "Unknown param in " + bulletClass.getName() + ": " + name);
            }
        }

        mLogics.put(bulletClass, bl);
        return bl;
    }
}

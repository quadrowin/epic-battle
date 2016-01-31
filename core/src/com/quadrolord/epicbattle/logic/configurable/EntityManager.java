package com.quadrolord.epicbattle.logic.configurable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Quadrowin on 31.01.2016.
 */
abstract public class EntityManager<T extends AbstractEntity> {

    private Class<T> mInfoClass;

    protected ArrayMap<String, EntityLoader<T>> mLoaders;

    protected JsonReader mReader;

    public EntityManager(Class<T> infoClass) {
        mInfoClass = infoClass;
        mReader = new JsonReader();
        mLoaders = new ArrayMap<String, EntityLoader<T>>();
    }

    /**
     * @return Path to the path with configs like "config/units
     */
    abstract public String getConfigDir();

    public T getInfo(Class<? extends T> entityClass) {
        String fileName = getConfigDir() + "/" + entityClass.getSimpleName() + ".json";
        JsonValue json;

        Gdx.app.log(getClass().getName(), "Loading config: " + fileName);

        try {
            json = mReader.parse(Gdx.files.internal(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Cannot found config file: " + fileName);
        }

        validate(json);


        T info = null;
        try {
            info = entityClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (JsonValue.JsonIterator it = json.iterator(); it.hasNext(); ) {
            JsonValue val = it.next();
            String name = val.name();
            if (mLoaders.containsKey(name)) {
                mLoaders.get(name).assign(info, val);
            } else {
                Gdx.app.log(getClass().getName(), "Unknown param for " + entityClass.getName() + ": " + name);
            }
        }

        return info;
    }

    /**
     * Проверка перед загрузкой
     * @param json
     */
    public void validate(JsonValue json) {
        if (!json.has("title")) {
            throw new RuntimeException("Empty unit name");
        }
    }

}

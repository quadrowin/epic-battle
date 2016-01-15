package com.quadrolord.epicbattle.logic.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class AndroidFileService extends AbstractFileService {

    /**
     * Указатель на файл в приватной директории приложения
     * @param path
     * @return
     */
    @Override
    public FileHandle getUserStorageFile(String path) {
        return Gdx.files.local(path);
    }

}

package com.quadrolord.epicbattle.desktop.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.quadrolord.epicbattle.logic.utils.AbstractFileService;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class DesktopFileService extends AbstractFileService {

    private String mUserStoragePath;

    private String getUserStoragePath() {
        if (mUserStoragePath == null) {
            mUserStoragePath = "Android/data/com.quadrolord.epicbattle/";
            String dirName = mUserStoragePath.substring(0, mUserStoragePath.length() - 1);
            FileHandle dirHandle = Gdx.files.external(dirName);
            if (!dirHandle.exists()) {
                dirHandle.mkdirs();
            }
        }
        return mUserStoragePath;
    }

    /**
     * Указатель на файл в локальной директории текущего пользователя
     * @param path
     * @return
     */
    @Override
    public FileHandle getUserStorageFile(String path) {
        return Gdx.files.external(getUserStoragePath() + path);
    }

}

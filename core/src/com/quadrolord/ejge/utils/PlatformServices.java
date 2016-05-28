package com.quadrolord.ejge.utils;

/**
 * Платформозависимые сервисы
 */
public class PlatformServices {

    private AbstractFileService mFileService;

    public PlatformServices(AbstractFileService fileService) {
        mFileService = fileService;
    }

    public AbstractFileService getFileService() {
        return mFileService;
    }

}

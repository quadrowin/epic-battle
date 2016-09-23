package com.quadrolord.ejge.utils;

/**
 * Платформозависимые сервисы
 */
public class PlatformServices {

    private AbstractAuthService mAuthService;
    private AbstractFileService mFileService;

    private static PlatformServices mInstance;

    private String mBuildTime;

    public PlatformServices(AbstractFileService fileService, AbstractAuthService authService) {
        mInstance = this;
        mAuthService = authService;
        mFileService = fileService;
    }

    public static PlatformServices i() {
        return mInstance;
    }

    public AbstractAuthService getAuthService() {
        return mAuthService;
    }

    public String getBuildTime() {
        return mBuildTime;
    }

    public AbstractFileService getFileService() {
        return mFileService;
    }

    public void setBuildTime(String time) {
        mBuildTime = time;
    }

}

package com.quadrolord.ejge;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.ejge.sound.SoundManager;

/**
 * Created by Quadrowin on 28.05.2016.
 */
abstract public class AbstractGameAdapter extends ApplicationAdapter {

    private FPSLogger mFps;

    private Skin mSkin;

    private AbstractScreen mScreen;

    private SoundManager mSoundManager = new SoundManager();

    private PlatformServices mPlatformServices;

    public AbstractGameAdapter(PlatformServices platformServices) {
        mPlatformServices = platformServices;
    }

    @Override
    public void create () {
        mFps = new FPSLogger();
    }

    public long getGameMillis() {
        return TimeUtils.millis();
    }

    public PlatformServices getPlatformServices() {
        return mPlatformServices;
    }

    public AbstractScreen getScreen() {
        return mScreen;
    }

    public Skin getSkin() {
        if (mSkin == null) {
            mSkin = new Skin();
            initCommonSkin(mSkin);
        }
        return mSkin;
    }

    public SoundManager getSoundManager() {
        return mSoundManager;
    }

    abstract public void initCommonSkin(Skin skin);



    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mScreen.render(Gdx.graphics.getDeltaTime());
        mFps.log();
    }

    @Override
    public void resize (int width, int height) {
        if (mScreen != null) {
            mScreen.resize(width, height);
        }
    }

    @Override
    public void resume() {
        if (mScreen != null) {
            mScreen.resume();
        }
    }

    public void switchToScreen(Class<? extends AbstractScreen> screenClass) {
        try {
            AbstractScreen screen = screenClass.getConstructor(this.getClass()).newInstance(this);
            switchToScreen(screen, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToScreen(AbstractScreen newScreen, boolean dispose) {
        if (dispose && mScreen != null) {
            mScreen.dispose();
        }
        mScreen = newScreen;
        if (mScreen != null) {
            mScreen.switchIn();
        }
    }

}

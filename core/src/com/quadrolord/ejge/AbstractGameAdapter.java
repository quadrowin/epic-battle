package com.quadrolord.ejge;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.quadrolord.ejge.sound.SoundManager;
import com.quadrolord.ejge.utils.PlatformServices;
import com.quadrolord.ejge.utils.ServiceFactory;
import com.quadrolord.ejge.view.AbstractScreen;

/**
 * Created by Quadrowin on 28.05.2016.
 */
abstract public class AbstractGameAdapter extends ApplicationAdapter {

    private ArrayMap<Class, ServiceFactory> mFactories = new ArrayMap<Class, ServiceFactory>();

    private FPSLogger mFps;

    private ArrayMap<Class, Object> mServices = new ArrayMap<Class, Object>();

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

    public <T> T get (Class<T> type) {
        if (!mServices.containsKey(type) && mFactories.containsKey(type)) {
            mServices.put(type, mFactories.get(type).create(this));
        }
        return (T)mServices.get(type);
    }

    abstract public AbstractScreen getDefaultScreen();

    public long getGameMillis() {
        return TimeUtils.millis();
    }

    /**
     * Создание нового скина с базовыми ресурсами
     * @return
     */
    public Skin getNewSkin() {
        Skin src = getSkin();
        Skin dst = new Skin();

        Object[] copyResources = new Object[] {
//                "default", BitmapFont.class,
//                "test-texture", Texture.class,
//                "default-label-style", Label.LabelStyle.class,
//                "default-text-button-style", TextButton.TextButtonStyle.class,
        };

        for (int i = 0; i < copyResources.length; i += 2) {
            dst.add(
                    (String)copyResources[i],
                    src.get((String)copyResources[i], (Class)copyResources[i + 1]),
                    (Class)copyResources[i + 1]
            );
        }

        return dst;
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

    public void registerFactory(Class type, ServiceFactory factory) {
        mFactories.put(type, factory);
    }

    public void registerService(Object service) {
        mServices.put(service.getClass(), service);
    }

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
            mSoundManager.onSourceDispose(mScreen);
        }
        if (newScreen == null) {
            newScreen = getDefaultScreen();
        }
        mScreen = newScreen;
        if (mScreen != null) {
            mScreen.switchIn();
        }
    }

}

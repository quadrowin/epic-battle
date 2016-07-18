package com.quadrolord.ejge.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.epicbattle.view.SpriteAnimationLoader;

/**
 * Created by Quadrowin on 27.11.2015.
 */
public abstract class AbstractScreen implements Screen {

    protected Stage mStage;

    protected AbstractGameAdapter mAdapter;

    protected AbstractScreen mParentScreen;

    protected Skin mSkin;

    protected TextureManager mTextures;

    /**
     * Размер пикселя
     */
    protected float mPx = 2;

    private SpriteAnimationLoader mSpriteAnimationLoader;

    public AbstractScreen(AbstractGameAdapter adapter) {
        mAdapter = adapter;
        mStage = new Stage();
        mStage.getRoot().setScale(mPx);
        mSkin = adapter.getNewSkin();
        mTextures = new TextureManager(mSkin);
    }

    public AbstractScreen(AbstractScreen parentScreen) {
        this(parentScreen.getAdapter());
        mParentScreen = parentScreen;
    }

    public <T> T get (Class<T> type) {
        return mAdapter.get(type);
    }

    public AbstractGameAdapter getAdapter() {
        return mAdapter;
    }

    public AbstractScreen getParentScreen() {
        return mParentScreen;
    }

    public float getPx() {
        return mPx;
    }

    public Skin getSkin() {
        return mSkin;
    }

    public SpriteAnimationLoader getSpriteAnimationLoader() {
        if (mSpriteAnimationLoader == null) {
            mSpriteAnimationLoader = new SpriteAnimationLoader();
        }
        return mSpriteAnimationLoader;
    }

    public Stage getStage() {
        return mStage;
    }

    public TextureManager getTextures() {
        return mTextures;
    }

    public void initFitViewport() {
        mStage.setViewport(new FitViewport(400 * mPx, 300 * mPx));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    /**
     * Переключение на этот скрин с другого
     */
    public void switchIn() {
        Gdx.input.setInputProcessor(mStage);
    }

    /** Called when a screen should render itself */
    public abstract void draw (float delta);

    /** Called when the screen should update itself, e.g. continue a simulation etc. */
    public abstract void update (float delta);

    @Override
    public void render (float delta) {
        update(delta);
        draw(delta);
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }

}

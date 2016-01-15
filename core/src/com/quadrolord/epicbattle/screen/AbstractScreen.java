package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.view.SpriteAnimationLoader;

/**
 * Created by Quadrowin on 27.11.2015.
 */
public abstract class AbstractScreen implements Screen {

    protected Stage mStage;

    protected EpicBattle mAdapter;

    protected Game mGame;

    protected Skin mSkin;

    /**
     * Размер пикселя
     */
    protected float mPx = 2;

    private SpriteAnimationLoader mSpriteAnimationLoader;

    public AbstractScreen(EpicBattle adapter) {
        mAdapter = adapter;
        mGame = adapter.getGame();
        mStage = new Stage();
        mStage.getRoot().setScale(mPx);
        mSkin = adapter.getNewSkin();
    }

    public EpicBattle getAdapter() {
        return mAdapter;
    }

    public Game getGame() {
        return mGame;
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

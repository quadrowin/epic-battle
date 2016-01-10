package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.Game;

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

    public AbstractScreen(EpicBattle adapter, Game game) {
        mAdapter = adapter;
        mGame = game;
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

    public Stage getStage() {
        return mStage;
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
    public void resize (int width, int height) {

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

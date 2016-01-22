package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.building.Building;
import com.quadrolord.epicbattle.view.town.building.BuildingView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTownScreen extends AbstractScreen {

    private int mSizeX = 8;
    private int mSizeY = 8;

    private int mCellWidth = 32;
    private int mCellHeight = 32;

    private float mDeltaX;
    private float mDeltaY;

    private MyTown mTown;

    public MyTownScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();

        mTown = new MyTown(mGame);


        TextButton btnToCampaignSelect = new TextButton("Select campaign", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnToCampaignSelect.setBounds(10, 240, 180, 50);
        mStage.addActor(btnToCampaignSelect);
        btnToCampaignSelect.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(CampaignSelectScreen.class);
            }

        });

        {

            Texture txMap = new Texture("town/map-bg.png");
            Drawable drMap = new TextureRegionDrawable(new TextureRegion(txMap));
            ImageButton ibMap = new ImageButton(drMap);
            ibMap.setBounds(0, 0, 400, 220);
            mStage.addActor(ibMap);

            Texture txLeftHand = new Texture("town/left-hand-tower.png");
            Drawable drLeftHand = new TextureRegionDrawable(new TextureRegion(txLeftHand));
            final ImageButton ibLeft = new ImageButton(drLeftHand);
            ibLeft.setBounds(200 - 30, 150 - 30, 30, 60);
            ibMap.addActor(ibLeft);
            final AbstractScreen screen = this;
            ibLeft.addListener(new ClickListener() {

                @Override
                public void clicked (InputEvent event, float x, float y) {
                    HintScreen hs = new HintScreen(screen, ibLeft.getX(), ibLeft.getY(), "It's your left hand");
                    mAdapter.switchToScreen(hs, false);

                }

            });

            Drawable drRightHand = new TextureRegionDrawable(new TextureRegion(txLeftHand, 1, 0, 0.1f, 1));
            final ImageButton ibRight = new ImageButton(drRightHand);
            ibRight.setBounds(200, 150 - 30, 30, 60);
            ibMap.addActor(ibRight);
            ibRight.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    HintScreen hs = new HintScreen(screen, ibRight.getX(), ibRight.getY(), "It's your right hand");
                    mAdapter.switchToScreen(hs, false);
                }

            });

        }

        for (Iterator<Building> it = mTown.getBuildings().iterator(); it.hasNext(); ) {
            Building bld = it.next();
            new BuildingView(this, bld);
        }

    }

    @Override
    public void draw(float delta) {

        float width = mSizeX * mCellWidth + mDeltaX;
        float height = mSizeY * mCellHeight + mDeltaY;

        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {
        float dx = delta * 10;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mDeltaX -= dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mDeltaY -= dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mDeltaX += dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mDeltaY += dx;
        }
    }
}

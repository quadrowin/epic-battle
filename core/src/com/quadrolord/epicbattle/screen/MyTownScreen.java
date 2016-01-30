package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuilding;
import com.quadrolord.epicbattle.logic.town.building.Mine;
import com.quadrolord.epicbattle.screen.town.MapGrid;
import com.quadrolord.epicbattle.view.town.building.BuildingView;
import com.quadrolord.epicbattle.view.town.building.LeftHandTempleView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTownScreen extends AbstractScreen {

    private float mDeltaX = 20;
    private float mDeltaY = 10;
    private float mDeltaZ = 150;

    private MyTown mTown;

    private MapGrid mMap;
    private Camera mMapCamera;
    private Stage mMapStage;

    private InputMultiplexer mMultiplexer;

    public MyTownScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();


        mMapCamera = new OrthographicCamera(400, 300);
        mMapCamera.far = 1000;
        mMapCamera.position.set(mDeltaX, mDeltaY, 10);
        mMapCamera.update();
        mMapStage = new Stage(new FitViewport(400, 300, mMapCamera));
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

        mMap = new MapGrid(this, mMapStage);

        {


            Texture txLeftHand = new Texture("town/left-hand-tower.png");
            final AbstractScreen screen = this;

            Drawable drRightHand = new TextureRegionDrawable(new TextureRegion(txLeftHand, 1, 0, 0.1f, 1));
            final ImageButton ibRight = new ImageButton(drRightHand);
            ibRight.setBounds(200, 150 - 30, 30, 60);
            mMap.addActor(ibRight);
            ibRight.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    HintScreen hs = new HintScreen(screen, ibRight.getX(), ibRight.getY(), "It's your right hand");
                    mAdapter.switchToScreen(hs, false);
                }

            });

        }

        for (Iterator<AbstractBuilding> it = mTown.getBuildings().iterator(); it.hasNext(); ) {
            AbstractBuilding bld = it.next();
            new BuildingView(this, mMap, bld);
        }

        new BuildingView(this, mMap, new Mine(mTown));

        Mine mine1 = new Mine(mTown);
        mine1.setPosition(4, 0);
        new LeftHandTempleView(this, mMap, mine1);

        Mine mine2 = new Mine(mTown);
        mine2.setPosition(9, 4);
        new BuildingView(this, mMap, mine2);

        Mine mine3 = new Mine(mTown);
        mine3.setPosition(8, 5);
        new BuildingView(this, mMap, mine3);

        mStage.addListener(new EventListener() {

            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent)) {
                    return false;
                }
                InputEvent ie = ((InputEvent) event);
                if (ie.getType() != InputEvent.Type.scrolled) {
                    return false;
                }
                float deltaX = ie.getScrollAmount();

                float maxZ = 1000;
                float minZ = 10;
                mDeltaZ = Math.max(
                        minZ,
                        Math.min(
                                maxZ,
                                mDeltaZ + deltaX
                        )
                );
                return true;
            }
        });

        mMultiplexer = new InputMultiplexer(mStage, mMapStage);
    }

    @Override
    public void draw(float delta) {

        mMap.setPosition(-mDeltaX, -mDeltaY);
//        mMapCamera.position.set(mDeltaX, mDeltaY, mDeltaZ);
//        mMapCamera.lookAt(mDeltaX, mDeltaY + 30, 0);
//        mMapCamera.update();

        mMapStage.act(delta);
        mMapStage.draw();

        mStage.getCamera().update();
        mStage.act(delta);
        mStage.draw();
    }

    /**
     * Переключение на этот скрин с другого
     */
    @Override
    public void switchIn() {
        Gdx.input.setInputProcessor(mMultiplexer);
    }

    @Override
    public void update(float delta) {
        float dx = delta * 100;

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

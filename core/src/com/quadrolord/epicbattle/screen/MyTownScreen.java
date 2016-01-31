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
import com.quadrolord.epicbattle.logic.town.TownListener;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.entity.LeftHandTemple;
import com.quadrolord.epicbattle.logic.town.building.entity.Mine;
import com.quadrolord.epicbattle.logic.town.building.entity.RightLegTemple;
import com.quadrolord.epicbattle.screen.town.MapGrid;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;

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

        TextButton btnBuildSomething = new TextButton("Build something", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuildSomething.setBounds(200, 240, 180, 50);
        mStage.addActor(btnBuildSomething);
        btnBuildSomething.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mTown.build(
                        Mine.class,
                        2 + mTown.getBuildings().size * 2, 2,
                        false, false
                );
            }

        });

        mMap = new MapGrid(this, mTown, mMapStage);
        final AbstractScreen screen = this;

        {


            Texture txLeftHand = new Texture("town/left-hand-tower.png");

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

        mTown.setListener(new TownListener() {
            @Override
            public void onBuildingAdd(AbstractBuildingItem building) {
                Class<AbstractBuildingView> viewClass = building.getInfo().getViewClass();
                AbstractBuildingView view;
                try {
                    view = viewClass.getConstructor(AbstractScreen.class, MapGrid.class, AbstractBuildingItem.class).newInstance(screen, mMap, building);
                    building.setView(view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBuildingChange(AbstractBuildingItem building) {

            }

            @Override
            public void onBuildingRemove(AbstractBuildingItem building) {

            }

            @Override
            public void onBuildingSelect(AbstractBuildingItem building) {

            }

            @Override
            public void onUserActionFail(BuildingAction action) {
                Gdx.app.log(screen.getClass().getName(), "Action fail: " + action);
            }
        });

        mTown.build(
                LeftHandTemple.class,
                5, 0, false, false
        );
        mTown.build(
                RightLegTemple.class,
                6, -1, false, false
        );

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

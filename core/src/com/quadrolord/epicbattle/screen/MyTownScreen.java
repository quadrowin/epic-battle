package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.TownListener;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.entity.DoodleShop;
import com.quadrolord.epicbattle.logic.town.building.entity.LeftHandTemple;
import com.quadrolord.epicbattle.logic.town.building.entity.Mine;
import com.quadrolord.epicbattle.logic.town.building.entity.RightLegTemple;
import com.quadrolord.epicbattle.logic.town.building.entity.SheepFarm;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;
import com.quadrolord.epicbattle.screen.town.MapGrid;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;
import com.quadrolord.epicbattle.view.town.resource.IronOreLabel;

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
        mTown.loadTown();

        TextButton btnToCampaignSelect = new TextButton("Select campaign", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnToCampaignSelect.setBounds(10, 240, 150, 50);
        mStage.addActor(btnToCampaignSelect);
        btnToCampaignSelect.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mAdapter.switchToScreen(CampaignSelectScreen.class);
            }

        });

        TextButton btnBuild1 = new TextButton("Build M", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuild1.setBounds(190, 240, 65, 50);
        mStage.addActor(btnBuild1);
        btnBuild1.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mTown.build(
                        Mine.class,
                        mTown.getBuildings().size * 2, 2,
                        false, false, false
                );
            }

        });

        TextButton btnBuild2 = new TextButton("Build D", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuild2.setBounds(260, 240, 65, 50);
        mStage.addActor(btnBuild2);
        btnBuild2.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mTown.build(
                        DoodleShop.class,
                        mTown.getBuildings().size * 2, 2,
                        false, false, false
                );
            }

        });

        TextButton btnBuild3 = new TextButton("Build S", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnBuild3.setBounds(330, 240, 65, 50);
        mStage.addActor(btnBuild3);
        btnBuild3.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mTown.build(
                        SheepFarm.class,
                        mTown.getBuildings().size * 2, 2,
                        false, false, false
                );
            }

        });

        mMap = new MapGrid(this, mTown, mMapStage);
        final AbstractScreen screen = this;



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
                5, 0, false, false, false
        );
        mTown.build(
                RightLegTemple.class,
                6, -1, false, false, false
        );

        new IronOreLabel(
                mTown.getResource(IronOre.class),
                mSkin,
                mStage
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
        mTown.getResource(IronOre.class).setValue(
                mTown.getResource(IronOre.class).getValue() + delta
        );

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

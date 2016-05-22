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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.thing.ThingCostElement;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.TownListener;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.BuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;
import com.quadrolord.epicbattle.logic.town.building.entity.LeftHandTemple;
import com.quadrolord.epicbattle.logic.town.building.entity.RightLegTemple;
import com.quadrolord.epicbattle.logic.town.listener.OnThingAddToPlan;
import com.quadrolord.epicbattle.logic.thing.entity.resource.IronOre;
import com.quadrolord.epicbattle.logic.thing.entity.resource.Noodles;
import com.quadrolord.epicbattle.screen.town.MapGrid;
import com.quadrolord.epicbattle.screen.town.PlacingControl;
import com.quadrolord.epicbattle.screen.town.panel.BuildingModePanel;
import com.quadrolord.epicbattle.screen.town.panel.GeneralPanel;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;
import com.quadrolord.epicbattle.view.town.resource.IronOreLabel;
import com.quadrolord.epicbattle.view.town.resource.NoodlesLabel;

/**
 * Created by Quadrowin on 16.01.2016.
 */
public class MyTownScreen extends AbstractScreen {

    private float mDeltaX = -200;
    private float mDeltaY = 0;
    private float mDeltaZ = 150;

    private MyTown mTown;

    private MapGrid mMap;
    private Camera mMapCamera;
    private Stage mMapStage;

    private InputMultiplexer mMultiplexer;

    private GeneralPanel mGuiGeneral;
    private BuildingModePanel mGuiBuildingMode;

    /**
     * Контрол перемещения здания
     */
    private PlacingControl mPlacing;

    public MyTownScreen(EpicBattle adapter) {
        super(adapter);
        initFitViewport();


        mMapCamera = new OrthographicCamera(400, 300);
        mMapCamera.far = 1000;
        mMapCamera.position.set(0, mDeltaY, 10);
        mMapCamera.update();
        mMapStage = new Stage(new FitViewport(400, 300, mMapCamera));
        mTown = mGame.getTown();
        mTown.loadTown();

        mGuiGeneral = new GeneralPanel(this, mTown);
        mGuiBuildingMode = new BuildingModePanel(this, mTown);

        final AbstractScreen screen = this;

        mMap = new MapGrid(this, mTown, mMapStage);

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
            public void onBuildingAdd(BuildingItem building) {
                Class<? extends AbstractBuildingView> viewClass = building.getInfo().getViewClass();
                AbstractBuildingView view;
                try {
                    view = viewClass.getConstructor(AbstractScreen.class, MapGrid.class, BuildingItem.class).newInstance(screen, mMap, building);
                    building.setView(view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBuildingRemove(BuildingItem building) {

            }

            @Override
            public void onBuildingSelect(BuildingItem building) {

            }

            @Override
            public void onBuildingConstructed(BuildingItem building) {

            }

            @Override
            public void onBuildingUpgraded(BuildingItem building) {

            }

            @Override
            public void onCancelBuildingMode() {
                if (mPlacing != null) {
                    mPlacing.getBuildingView().remove();
                    mPlacing.remove();
                    mPlacing = null;
                }
                mGuiBuildingMode.setVisible(false);
                mGuiGeneral.setVisible(true);
            }

            @Override
            public void onConfirmBuilding() {
                if (mPlacing != null) {
                    mTown.build(
                            mPlacing.getBuildingItem().getInfo(),
                            mPlacing.getBuildingX(),
                            mPlacing.getBuildingY(),
                            false,
                            true,
                            false

                    );
                    mPlacing.getBuildingView().remove();
                    mPlacing.remove();
                    mPlacing = null;
                }
                mGuiBuildingMode.setVisible(false);
                mGuiGeneral.setVisible(true);
            }

            @Override
            public void onConfirmMoving() {
                if (mPlacing != null) {
                    mTown.moveBuilding(
                            mPlacing.getBuildingItem(),
                            mPlacing.getBuildingStartX(),
                            mPlacing.getBuildingStartY(),
                            mPlacing.getBuildingX(),
                            mPlacing.getBuildingY()

                    );
                    mPlacing.remove();
                    mPlacing = null;
                }
                mGuiBuildingMode.setVisible(false);
                mGuiGeneral.setVisible(true);
            }

            /**
             * Создание
             * @param buildingInfo
             */
            @Override
            public void onEnterBuildingMode(AbstractBuildingEntity buildingInfo) {
                BuildingItem building = mTown.instantiateBuilding(buildingInfo);
                Class<? extends AbstractBuildingView> viewClass = buildingInfo.getViewClass();
                if (viewClass == null) {
                    Exception e = new Exception("No view class for building " + buildingInfo.getTitle());
                    e.printStackTrace();
                    return;
                }
                AbstractBuildingView view;
                try {
                    view = viewClass.getConstructor(AbstractScreen.class, MapGrid.class, BuildingItem.class).newInstance(screen, mMap, building);
                    building.setView(view);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                building.setPosition(
                        Math.round(mDeltaX / mMap.getCellSideX()) + 4,
                        Math.round(mDeltaY / mMap.getCellSideY())
                );
                mPlacing = new PlacingControl(screen, building, mMap, view);
                mGuiGeneral.setVisible(false);
                mGuiBuildingMode.showBuildingMode();
            }

            /**
             * Перемещение
             * @param building
             */
            @Override
            public void onEnterBuildingMode(BuildingItem building) {
                AbstractBuildingView view = building.getView();
                mPlacing = new PlacingControl(screen, building, mMap, view);
                mGuiGeneral.setVisible(false);
                mGuiBuildingMode.showMovingMode();
            }

            @Override
            public void onUserActionFail(BuildingAction action) {
                Gdx.app.log(screen.getClass().getName(), "Action fail: " + action);
            }

            @Override
            public void onOrderResourceLack(ThingCostElement cost) {
                Gdx.app.log("Out of resource ", cost.getResource().getName());
            }

            @Override
            public void onThingAddToPlan(BuildingItem building, CraftPlanItem plan) {
                Gdx.app.log("Thing added to plan ", plan.getThing().getTitle());
                if (mAdapter.getScreen() instanceof OnThingAddToPlan) {
                    ((OnThingAddToPlan) mAdapter.getScreen()).onThingAddToPlan(building, plan);
                }

            }
        });

        mTown.build(
                LeftHandTemple.class,
                4, 1, false, false, false
        );
        mTown.build(
                RightLegTemple.class,
                5, 0, false, false, false
        );

        new IronOreLabel(
                mTown.getResource(IronOre.class),
                mSkin,
                mStage
        );

        new NoodlesLabel(
                mTown.getResource(Noodles.class),
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

        if (Gdx.input.isTouched() && mGuiGeneral.isVisible()) {
            mMapCamera.position.x -= Gdx.input.getDeltaX();
            mMapCamera.position.y += Gdx.input.getDeltaY();
        }

        mTown.act(delta);
    }
}

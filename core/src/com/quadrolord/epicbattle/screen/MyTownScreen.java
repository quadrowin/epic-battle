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
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.CraftPlanItem;
import com.quadrolord.epicbattle.logic.town.building.entity.LeftHandTemple;
import com.quadrolord.epicbattle.logic.town.building.entity.RightLegTemple;
import com.quadrolord.epicbattle.logic.town.resource.IronOre;
import com.quadrolord.epicbattle.logic.town.resource.Noodles;
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

    private float mDeltaX = 20;
    private float mDeltaY = 10;
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
        mMapCamera.position.set(mDeltaX, mDeltaY, 10);
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
            public void onEnterBuildingMode(AbstractBuildingEntity buildingInfo) {
                AbstractBuildingItem building = mTown.instantiateBuilding(buildingInfo);
                Class<AbstractBuildingView> viewClass = buildingInfo.getViewClass();
                if (viewClass == null) {
                    Exception e = new Exception("No view class for building " + buildingInfo.getTitle());
                    e.printStackTrace();
                    return;
                }
                AbstractBuildingView view;
                try {
                    view = viewClass.getConstructor(AbstractScreen.class, MapGrid.class, AbstractBuildingItem.class).newInstance(screen, mMap, building);
                    building.setView(view);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                building.setPosition(
                        Math.round(mDeltaX / mMap.getCellSideX()) + 4,
                        Math.round(mDeltaY / mMap.getCellSideY())
                );
                mPlacing = new PlacingControl(screen, building, view);
                mGuiGeneral.setVisible(false);
                mGuiBuildingMode.setVisible(true);
            }

            @Override
            public void onEnterBuildingMode(AbstractBuildingItem building) {
                AbstractBuildingView view = building.getView();
                mPlacing = new PlacingControl(screen, building, view);
                mGuiGeneral.setVisible(false);
                mGuiBuildingMode.setVisible(true);
            }

            @Override
            public void onUserActionFail(BuildingAction action) {
                Gdx.app.log(screen.getClass().getName(), "Action fail: " + action);
            }

            @Override
            public void onOrderResourceLack(ThingCostElement cost) {
                Gdx.app.debug("Out of resource ", cost.getResource().getName());
            }

            @Override
            public void onThingAddToPlan(AbstractBuildingItem building, CraftPlanItem plan) {
                Gdx.app.debug("Thing added to plan ", plan.getThing().getTitle());
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

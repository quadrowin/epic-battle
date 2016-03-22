package com.quadrolord.epicbattle.screen.town.building;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.SubScreenWindow;
import com.quadrolord.epicbattle.screen.town.item.OrderButton;

import java.util.Iterator;

/**
 * Created by Quadrowin on 12.03.2016.
 */
public class SmithyBuildingScreen extends AbstractScreen{

    private AbstractScreen mParentScreen;

    private AbstractBuildingItem mBuilding;

    public SmithyBuildingScreen(final AbstractScreen parentScreen, AbstractBuildingItem building) {
        super(parentScreen.getAdapter());
        mBuilding = building;
        mParentScreen = parentScreen;
        initFitViewport();

        Group wg = new SubScreenWindow(this).getInnerGroup();

        createTakeButton(wg);
        createMoveButton(wg);

        for (Iterator<AbstractThingEntity> it = building.getInfo().getAvailableThings().iterator(); it.hasNext();) {
            AbstractThingEntity thing = it.next();
            new OrderButton(this, wg, thing);
        }
    }

    private void createTakeButton(Group parent) {
        TextButton btnTake = new TextButton("Take", getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnTake.setBounds(
                parent.getWidth() - 80,
                40,
                40,
                40
        );
        parent.addActor(btnTake);
        btnTake.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mBuilding.getInfo().takeAvailable(mBuilding);
                getAdapter().switchToScreen(mParentScreen, true);
            }

        });
    }

    private void createMoveButton(Group parent) {
        TextButton btnTake = new TextButton("Move", getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnTake.setBounds(
                parent.getWidth() - 160,
                40,
                40,
                40
        );
        parent.addActor(btnTake);
        btnTake.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mBuilding.getTown().enterMovingMode(mBuilding);
                getAdapter().switchToScreen(mParentScreen, true);
            }

        });
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {
        mBuilding.getInfo().updateBalanceFull(mBuilding);

    }

}

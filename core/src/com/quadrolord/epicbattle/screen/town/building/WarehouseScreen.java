package com.quadrolord.epicbattle.screen.town.building;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.quadrolord.epicbattle.logic.thing.AbstractThingEntity;
import com.quadrolord.epicbattle.logic.thing.ThingItem;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.SubScreenWindow;

import java.util.Iterator;

/**
 * Created by Quadrowin on 23.04.2016.
 */
public class WarehouseScreen extends AbstractScreen {

    private AbstractScreen mParentScreen;

    private AbstractBuildingItem mBuilding;

    public WarehouseScreen(final AbstractScreen parentScreen, AbstractBuildingItem building) {
        super(parentScreen.getAdapter());
        mBuilding = building;
        mParentScreen = parentScreen;
        initFitViewport();

        Group wg = new SubScreenWindow(this).getInnerGroup();
        ArrayMap<Class<? extends AbstractThingEntity>, ThingItem> rs = mBuilding.getTown().getResources();

        int number = 0;
        for (Iterator<ObjectMap.Entry<Class<? extends AbstractThingEntity>, ThingItem>> it = rs.iterator(); it.hasNext(); number++) {
            ObjectMap.Entry<Class<? extends AbstractThingEntity>, ThingItem> en = it.next();
            ThingItem ti = en.value;
            Label lbl = new Label(
                    ti.getInfo().getTitle() + " " + ti.getCount(),
                    mSkin.get("default-label-style", Label.LabelStyle.class)
            );
            lbl.setAlignment(Align.left);
            lbl.setBounds(0, 15 * number, wg.getWidth(), 15);
            wg.addActor(lbl);
        }

        createMoveButton(wg);
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
//        mBuilding.getInfo().updateBalanceFull(mBuilding);
//        Array<ResourceSourceItem> resources = mBuilding.getResources();
    }
}

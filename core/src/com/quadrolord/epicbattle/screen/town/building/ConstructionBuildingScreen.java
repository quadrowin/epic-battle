package com.quadrolord.epicbattle.screen.town.building;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.SubScreenWindow;

/**
 * Created by Quadrowin on 07.03.2016.
 */
public class ConstructionBuildingScreen extends AbstractScreen {

    private Label mRemainLabel;

    private AbstractBuildingItem mBuilding;

    public ConstructionBuildingScreen(final AbstractScreen parentScreen, AbstractBuildingItem building) {
        super(parentScreen);
        mBuilding = building;
        initFitViewport();

        Group wg = new SubScreenWindow(this).getInnerGroup();

        createCancelButton(wg);
        createWaitButton(wg);
        createRemainLabel(wg);
    }

    private void createCancelButton(Group parent) {
        TextButton btn = new TextButton("Cancel building", getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btn.setBounds(
                40,
                40,
                120,
                40
        );
        parent.addActor(btn);
        btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                getAdapter().switchToScreen(mParentScreen, true);
            }

        });
    }

    private void createRemainLabel(Group parent) {
        int remain = (int)mBuilding.getConstructionRemainingTime();
        mRemainLabel = new Label("Remains: " + remain, mSkin.get("default-label-style", Label.LabelStyle.class));
        mRemainLabel.setAlignment(Align.center, Align.center);
        mRemainLabel.setBounds(0, 100, parent.getWidth(), 100);
        parent.addActor(mRemainLabel);
    }

    private void createWaitButton(Group parent) {
        TextButton btn = new TextButton("Wait", getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btn.setBounds(
                200,
                40,
                120,
                40
        );
        parent.addActor(btn);
        btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
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
        float remain = (float)mBuilding.getConstructionRemainingTime() / 1000;
        mRemainLabel.setText(String.format("Remains: %.1f s", remain));
    }

}

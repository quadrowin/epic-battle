package com.quadrolord.epicbattle.screen.town.building;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.resource.ResourceSourceItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.town.SubScreenWindow;
import com.quadrolord.epicbattle.screen.town.button.MoveButtonFactory;
import com.quadrolord.epicbattle.screen.town.button.UpgradeButtonFactory;

/**
 * Экран при заходе в ресурсопоставляющее здание
 */
public class MineBuildingScreen extends AbstractScreen {

    private Label mResourceCountLabel;

    private AbstractBuildingItem mBuilding;

    public MineBuildingScreen(final AbstractScreen parentScreen, AbstractBuildingItem building) {
        super(parentScreen);
        mBuilding = building;
        initFitViewport();

        Group wg = new SubScreenWindow(this).getInnerGroup();

        Array<ResourceSourceItem> resources = mBuilding.getResources();
        mResourceCountLabel = new Label("ResourceEntity count " + resources.get(0).getCurrentBalance(), mSkin.get("default-label-style", Label.LabelStyle.class));
        mResourceCountLabel.setAlignment(Align.center, Align.center);
        mResourceCountLabel.setBounds(0, 0, wg.getWidth(), wg.getHeight());
        wg.addActor(mResourceCountLabel);

        createTakeButton(wg);
        MoveButtonFactory.create(this, mBuilding, wg);

        if (
                !building.isInUpgrading()
                && !building.isInConstruction()
                && building.getInfo().getLevelingStrategy().canLevelUp(building)
        ) {
            UpgradeButtonFactory.create(this, mBuilding, wg);
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

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {
        mBuilding.getInfo().updateBalanceFull(mBuilding);
        Array<ResourceSourceItem> resources = mBuilding.getResources();
        mResourceCountLabel.setText("ResourceEntity count " + resources.get(0).getCurrentBalance());
    }
}

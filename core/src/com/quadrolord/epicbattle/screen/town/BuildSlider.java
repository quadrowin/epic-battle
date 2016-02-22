package com.quadrolord.epicbattle.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.town.MyTown;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingEntity;
import com.quadrolord.epicbattle.logic.town.building.AbstractBuildingItem;
import com.quadrolord.epicbattle.logic.town.building.ExampleBuildingItem;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.NewBuildingScreen;
import com.quadrolord.epicbattle.view.town.building.AbstractBuildingView;

/**
 * Created by Quadrowin on 20.02.2016.
 */
public class BuildSlider extends Group {

    private TextButton mBackground;

    private Group mWrapper;

    private float mPosition;

    public BuildSlider(final NewBuildingScreen screen, MyTown town) {
        Skin skin = screen.getSkin();

        Texture texture = new Texture("ui/panel-64.png");
        skin.add("ui-panel-64", texture);

        NinePatch npPanel = new NinePatch(
                skin.get("ui-panel-64", Texture.class),
                16, 16, 16, 16
        );

        Drawable npdPanel = new NinePatchDrawable(npPanel);

        mBackground = new TextButton(
                "",
                new TextButton.TextButtonStyle(
                        npdPanel,
                        npdPanel,
                        null,
                        skin.getFont("default")
                )
        );
        mBackground.setBounds(30, 30, 340, 120);
        addActor(mBackground);


        Array<AbstractBuildingEntity> bts = town.getAvailableBuildingTypes();

        mWrapper = new Group();
        mWrapper.setBounds(0, 0, bts.size * 120 + 20, mBackground.getHeight());
        mBackground.addActor(mWrapper);

        for (int i = 0; i < bts.size; i++) {
            ExampleBuildingItem ebi = new ExampleBuildingItem(town);
            AbstractBuildingView bv;
            try {
                bv = (AbstractBuildingView)bts.get(i).getViewClass().getConstructor(AbstractScreen.class, MapGrid.class, AbstractBuildingItem.class).newInstance(screen, null, ebi);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            bv.setBounds(i * 120 + 10, 10, 100, 100);
            mWrapper.addActor(bv);
        }

        TextButton btnClose = new TextButton("Close", skin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnClose.setBounds(260, 80, 65, 30);
        mBackground.addActor(btnClose);
        btnClose.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getAdapter().switchToScreen(screen.getPausedScreen(), true);
            }

        });

        screen.getStage().addActor(this);


    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched()) {
            mWrapper.setX(Math.max(
                    mBackground.getWidth() - mWrapper.getWidth(),
                    Math.min(
                            0,
                            mWrapper.getX() + Gdx.input.getDeltaX()
                    )
            ));
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);
        resetTransform(batch);
    }

}

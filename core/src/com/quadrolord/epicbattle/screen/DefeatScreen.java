package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class DefeatScreen extends AbstractScreen {

    private AbstractScreen mBattleScreen;

    public DefeatScreen(AbstractScreen battleScreen) {
        super(battleScreen.getAdapter(), battleScreen.getGame());
        mBattleScreen = battleScreen;
        initFitViewport();

        Texture texture = new Texture("ui/panel-64.png");
        mSkin.add("ui-panel-64", texture);

        NinePatch npPanel = new NinePatch(
                mSkin.get("ui-panel-64", Texture.class),
                16, 16, 16, 16
        );

        Drawable npdPanel = new NinePatchDrawable(npPanel);

        TextButton background = new TextButton(
                "",
                new TextButton.TextButtonStyle(
                        npdPanel,
                        npdPanel,
                        null,
                        mSkin.getFont("default")
                )
        );
        background.setBounds(50, 50, 300, 200);
        mStage.addActor(background);

        Label lblTitle = new Label("Defeat!", mSkin.get("default-label-style", Label.LabelStyle.class));
        lblTitle.setAlignment(Align.center, Align.center);
        lblTitle.setBounds(0, background.getHeight() - 50, background.getWidth(), 30);
        lblTitle.setFontScale(getPx());
        background.addActor(lblTitle);
    }

    @Override
    public void draw(float delta) {
        mBattleScreen.draw(delta);
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void update(float delta) {

    }
}
package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.screen.pause.CloseButton;
import com.quadrolord.epicbattle.screen.pause.LeaveButton;

/**
 * Created by Quadrowin on 04.01.2016.
 */
public class PauseScreen extends com.quadrolord.ejge.view.AbstractScreen {

    private com.quadrolord.ejge.view.AbstractScreen mPausedScreen;

    public PauseScreen(com.quadrolord.ejge.view.AbstractScreen pausedScreen) {
        super(pausedScreen.getAdapter());
        mPausedScreen = pausedScreen;
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
        background.setBounds(100, 100, SES.SCREEN_WIDTH - 200, SES.SCREEN_HEIGHT - 200);
        mStage.addActor(background);

        Label lblTitle = new Label("GAME PAUSED", RM.getLabelStyleLarge());
        lblTitle.setAlignment(Align.center, Align.center);
        lblTitle.setBounds(0, background.getHeight() - 50, background.getWidth(), 30);
        background.addActor(lblTitle);

        new CloseButton(this, background);
        new LeaveButton(this, background);
    }

    @Override
    public void draw(float delta) {
        mPausedScreen.draw(0);
        mStage.act(delta);
        mStage.draw();
    }

    public com.quadrolord.ejge.view.AbstractScreen getPausedScreen() {
        return mPausedScreen;
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }

}

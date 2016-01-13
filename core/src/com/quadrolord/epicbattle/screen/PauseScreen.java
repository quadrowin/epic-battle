package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.screen.pause.CloseButton;
import com.quadrolord.epicbattle.screen.pause.LeaveButton;

/**
 * Created by Quadrowin on 04.01.2016.
 */
public class PauseScreen extends AbstractScreen {

    private AbstractScreen mPausedScreen;

    public PauseScreen(AbstractScreen pausedScreen) {
        super(pausedScreen.getAdapter(), pausedScreen.getGame());
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
        background.setBounds(50, 50, 300, 200);
        mStage.addActor(background);

        Label lblTitle = new Label("GAME IS PAUSED", mSkin.get("default-label-style", Label.LabelStyle.class));
        lblTitle.setAlignment(Align.center, Align.center);
        lblTitle.setBounds(0, background.getHeight() - 50, background.getWidth(), 30);
        lblTitle.setFontScale(getPx());
        background.addActor(lblTitle);

        new CloseButton(this, background);
        new LeaveButton(this, background);
    }

    @Override
    public void draw(float delta) {
        mPausedScreen.draw(0);
        mStage.draw();
    }

    public AbstractScreen getPausedScreen() {
        return mPausedScreen;
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }

}

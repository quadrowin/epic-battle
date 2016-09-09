package com.quadrolord.epicbattle.screen.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.screen.PauseScreen;
import com.quadrolord.epicbattle.screen.SES;
import com.quadrolord.epicbattle.view.Sounds;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class PauseButton {

    public PauseButton(final AbstractScreen screen, Stage stage) {
        TextButton btnPause = new TextButton("", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnPause.setBounds(
                SES.SCREEN_WIDTH - 50 * SES.F,
                SES.SCREEN_HEIGHT - 50 * SES.F,
                SES.BUTTON_HEIGHT,
                SES.BUTTON_HEIGHT
        );
        stage.addActor(btnPause);
        btnPause.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("", "pause click");
                screen.getAdapter().getSoundManager().play(Sounds.MENU_CLICK);
                screen.getAdapter().switchToScreen(getPausedScreen(screen), true);
            }

        });

        Label lblPause = new Label("||", screen.getSkin(), "default", Color.WHITE);
        lblPause.setBounds(0, 0, btnPause.getWidth(), btnPause.getHeight());
        lblPause.setAlignment(Align.center, Align.center);
        btnPause.addActor(lblPause);
    }

    private AbstractScreen getPausedScreen(AbstractScreen screen) {
        return new PauseScreen(screen);
    }

}

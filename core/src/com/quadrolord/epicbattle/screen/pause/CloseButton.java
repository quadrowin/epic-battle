package com.quadrolord.epicbattle.screen.pause;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.screen.PauseScreen;

/**
 * Created by Quadrowin on 10.01.2016.
 */
public class CloseButton {

    public CloseButton(final PauseScreen screen, Group panel) {
        TextButton btnPause = new TextButton("", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnPause.setBounds(
                panel.getWidth() - 30,
                panel.getHeight() - 30,
                40,
                40
        );
        panel.addActor(btnPause);
        btnPause.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getAdapter().switchToScreen(screen.getPausedScreen(), true);
            }

        });

        Label lblPause = new Label("||", screen.getSkin(), "default", Color.WHITE);
        lblPause.setBounds(0, 0, btnPause.getWidth(), btnPause.getHeight());
        lblPause.setAlignment(Align.center, Align.center);
        btnPause.addActor(lblPause);
    }

}

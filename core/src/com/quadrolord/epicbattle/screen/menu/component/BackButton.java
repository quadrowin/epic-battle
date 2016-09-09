package com.quadrolord.epicbattle.screen.menu.component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.RM;
import com.quadrolord.epicbattle.screen.SES;

/**
 * Created by Goorus on 21.08.2016.
 */
public class BackButton {

    public static TextButton create(final AbstractScreen screen, final Class<? extends AbstractScreen> backScreenClass) {
        TextButton btnBack = new TextButton("Back", RM.getTextButtonStyle());
        btnBack.setBounds(SES.SCREEN_BORDER, SES.SCREEN_BORDER, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        screen.getStage().addActor(btnBack);
        btnBack.addListener(new ClickListener() {

            public void clicked (InputEvent event, float x, float y) {
                AbstractScreen ps = screen.getParentScreen();
                if (ps == null) {
                    screen.getAdapter().switchToScreen(backScreenClass);
                } else {
                    screen.getAdapter().switchToScreen(screen.getParentScreen(), true);
                }
            }

        });
        return btnBack;
    }

}

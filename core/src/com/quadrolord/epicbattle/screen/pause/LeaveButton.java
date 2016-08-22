package com.quadrolord.epicbattle.screen.pause;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.menu.LevelSelectScreen;
import com.quadrolord.epicbattle.screen.PauseScreen;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class LeaveButton extends Group {

    public LeaveButton(final PauseScreen screen, Group panel) {
        TextButton btnPause = new TextButton("Leave battle", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnPause.setBounds(
                40,
                40,
                100,
                50
        );
        panel.addActor(btnPause);
        btnPause.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                LevelSelectScreen levelsScreen = new LevelSelectScreen(screen.getAdapter(), screen.get(BattleGame.class).getLevel().getCampaign());
                screen.getAdapter().switchToScreen(levelsScreen, true);
            }

        });
    }

}

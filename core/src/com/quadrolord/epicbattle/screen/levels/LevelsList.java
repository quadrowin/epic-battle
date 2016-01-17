package com.quadrolord.epicbattle.screen.levels;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.screen.AbstractScreen;
import com.quadrolord.epicbattle.screen.BattleScreen;

/**
 * Created by Quadrowin on 13.01.2016.
 */
public class LevelsList extends Group {

    public LevelsList(final AbstractScreen screen, Level[] levels) {
        for (int i = 0; i < levels.length; i++) {
            final Level level = levels[i];
            TextButton btnLevel = new TextButton(level.getName(), screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
            btnLevel.setBounds(
                    10,
                    10 + i * 60,
                    180,
                    50
            );
            screen.getStage().addActor(btnLevel);
            btnLevel.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    BattleScreen battleScreen = new BattleScreen(screen.getAdapter(), level);
                    screen.getAdapter().switchToScreen(battleScreen, true);
                }

            });
        }
    }

}

package com.quadrolord.epicbattle.screen.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.quadrolord.ejge.view.AbstractScreen;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.screen.MyTownScreen;
import com.quadrolord.epicbattle.screen.SES;

import java.util.Iterator;

/**
 * Created by Quadrowin on 15.01.2016.
 */
public class DebugPanel extends Group {

    private boolean mOpened = false;

    private Group mPanel;

    private AbstractScreen mScreen;

    private Actor mToggleButton;

    private int mButtonsCount = 0;

    public DebugPanel(final AbstractScreen screen, Stage stage) {
        mScreen = screen;

        Texture texture = new Texture("ui/panel-64.png");
        NinePatch ninePatch = new NinePatch(
                texture,
                16, 16, 16, 16
        );
        Drawable drBg = new NinePatchDrawable(ninePatch);

        mPanel = new TextButton("", new TextButton.TextButtonStyle(drBg, drBg, null, screen.getSkin().getFont("default")));
        mPanel.setBounds(50, 50, SES.SCREEN_WIDTH - 100, SES.SCREEN_HEIGHT - 100);
        mPanel.setVisible(mOpened);
        stage.addActor(mPanel);

        mToggleButton = new TextButton("debug", screen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        mToggleButton.setBounds(
                500,
                500,
                180,
                SES.BUTTON_HEIGHT
        );
        stage.addActor(mToggleButton);
        mToggleButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mOpened = !mOpened;
                mPanel.setVisible(mOpened);
            }

        });

        addButton("Save Profile", new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("debug panel", "click Save Profile");
                screen.get(ProfileManager.class).saveProfile();
            }

        });

        addButton("Load Profile", new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("debug panel", "click Load Profile");
                screen.get(ProfileManager.class).getProfile();
            }

        });

        addButton("To my town", new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mScreen.getAdapter().switchToScreen(MyTownScreen.class);
            }

        });

        addButton("Up units lvl", new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (Iterator<SkillItem> it = mScreen.get(BattleGame.class).getPlayerTower().getBulletSkills().values().iterator(); it.hasNext(); ) {
                    SkillItem skill = it.next();
                    skill.setLevel(skill.getLevel() + 1);
                }
            }

        });
    }

    public void addButton(String title, ClickListener listener) {
        TextButton button = new TextButton(title, mScreen.getSkin().get("default-text-button-style", TextButton.TextButtonStyle.class));
        button.setBounds(
                10 + 90 * (mButtonsCount % 3),
                10 + 50 * Math.round(mButtonsCount / 3),
                90,
                40
        );
        mPanel.addActor(button);
        button.addListener(listener);
        mButtonsCount++;
    }

}

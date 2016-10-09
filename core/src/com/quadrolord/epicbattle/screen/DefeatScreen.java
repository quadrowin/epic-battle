package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.tower.BattleGame;

/**
 * Created by Quadrowin on 11.01.2016.
 */
public class DefeatScreen extends com.quadrolord.ejge.view.AbstractScreen {

    private com.quadrolord.ejge.view.AbstractScreen mBattleScreen;

    public DefeatScreen(com.quadrolord.ejge.view.AbstractScreen battleScreen) {
        super(battleScreen.getAdapter());
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
        background.setBounds(50, 50, SES.SCREEN_WIDTH - 100, SES.SCREEN_HEIGHT - 100);
        mStage.addActor(background);

        Label lblTitle = new Label("Defeat!", mSkin.get("default-label-style", Label.LabelStyle.class));
        lblTitle.setAlignment(Align.center, Align.center);
        lblTitle.setBounds(0, background.getHeight() - 50 * SES.F, background.getWidth(), 30);
        lblTitle.setFontScale(getPx());
        background.addActor(lblTitle);


        TextButton btnRestart = new TextButton("Try again", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnRestart.setBounds(160 * SES.F, 20 * SES.F, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        background.addActor(btnRestart);
        btnRestart.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Level level = get(BattleGame.class).getLevel();
                get(BattleGame.class).startLevel(level);
                mAdapter.switchToScreen(mBattleScreen, true);
            }

        });


        TextButton btnLeave = new TextButton("Leave this place", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnLeave.setBounds(20 * SES.F, 20 * SES.F, SES.BUTTON_WIDTH, SES.BUTTON_HEIGHT);
        background.addActor(btnLeave);
        btnLeave.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                com.quadrolord.epicbattle.screen.menu.LevelSelectScreen levelsScreen = new com.quadrolord.epicbattle.screen.menu.LevelSelectScreen(mAdapter, get(BattleGame.class).getLevel().getCampaign());
                mAdapter.switchToScreen(levelsScreen, true);
            }

        });
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

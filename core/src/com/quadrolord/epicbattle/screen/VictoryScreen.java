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
public class VictoryScreen extends com.quadrolord.ejge.view.AbstractScreen {

    private com.quadrolord.ejge.view.AbstractScreen mBattleScreen;

    private Level mLevel;

    public VictoryScreen(com.quadrolord.ejge.view.AbstractScreen battleScreen, Level level) {
        super(battleScreen.getAdapter());
        mBattleScreen = battleScreen;
        mLevel = level;
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

        Label lblTitle = new Label("Victory!", mSkin.get("default-label-style", Label.LabelStyle.class));
        lblTitle.setAlignment(Align.center, Align.center);
        lblTitle.setBounds(0, background.getHeight() - 50, background.getWidth(), 30);
        lblTitle.setFontScale(getPx());
        background.addActor(lblTitle);

        Label lblRewardExp = new Label("+" + mLevel.getRewardExp() + " exp", mSkin.get("default-label-style", Label.LabelStyle.class));
        lblRewardExp.setAlignment(Align.center, Align.center);
        lblRewardExp.setBounds(0, background.getHeight() - 100, background.getWidth(), 30);
        lblRewardExp.setFontScale(getPx());
        background.addActor(lblRewardExp);


        TextButton btnGoNext = new TextButton("Go next", mSkin.get("default-text-button-style", TextButton.TextButtonStyle.class));
        btnGoNext.setBounds(160, 40, 100, 50);
        background.addActor(btnGoNext);
        btnGoNext.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Level level = get(BattleGame.class).getLevel();
                level = get(BattleGame.class).getCampaignManager().getNextLevel(level);
                get(BattleGame.class).startLevel(level);
                mAdapter.switchToScreen(mBattleScreen, true);
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

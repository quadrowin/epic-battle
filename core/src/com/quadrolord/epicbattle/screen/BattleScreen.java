package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.BulletUnit;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameListener;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.screen.battle.CashLabel;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.TowerView;
import com.quadrolord.epicbattle.view.ViewLoader;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class BattleScreen extends AbstractScreen {

    public BattleScreen(EpicBattle adapter, Game game) {
        super(adapter, game);

        mStage.setViewport(new FitViewport(400 * mPx, 300 * mPx));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        TextureRegion tr1 = new TextureRegion(mSkin.get("test-texture", Texture.class), 64, 64);
        TextureRegion tr2 = new TextureRegion(mSkin.get("test-texture", Texture.class), 64, 0, 64, 64);
        NinePatch np1 = new NinePatch(tr1, 10, 10, 10, 10);
        NinePatch np2 = new NinePatch(tr2, 15, 15, 15, 15);
        NinePatchDrawable npd1 = new NinePatchDrawable(np1);
        NinePatchDrawable npd2 = new NinePatchDrawable(np2);



        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
                npd1,
                npd2,
                null,
                mSkin.getFont("default")
        );
        mSkin.add("text-button-style-default", textButtonStyle);

        TextButton btn = new TextButton("OK", textButtonStyle);
        btn.setBounds(100, 200, 100, 50);
        mStage.addActor(btn);

        ViewLoader vl = new ViewLoader();
        vl.loadTextures(
                mSkin,
                new String[]{
                        "tower", "tower.png"
                }
        );

        final AbstractScreen screen = this;
        mGame.setListener(new GameListener() {

            @Override
            public void onBulletCreate(BulletUnit bullet) {
                new BulletUnitView(bullet, screen);
            }

            @Override
            public void onTowerCreate(final Tower tower) {

                if (tower.getSpeedRatio() < 0) {
                    new CashLabel(tower, screen);
                }

                TowerView tv = new TowerView(tower, screen);
                tv.addListener(new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        mGame.createUnit(tower, BulletUnit.class);
                    }

                });
            }

        });

        mGame.startLevel();
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {
        mGame.act(delta);
    }

}

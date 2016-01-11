package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameListener;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.Tower;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.screen.battle.AttackAnimation;
import com.quadrolord.epicbattle.screen.battle.Background;
import com.quadrolord.epicbattle.screen.battle.CashLabel;
import com.quadrolord.epicbattle.screen.battle.CreateBulletPanel;
import com.quadrolord.epicbattle.screen.battle.PauseButton;
import com.quadrolord.epicbattle.screen.battle.TowerHp;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.TowerDeath;
import com.quadrolord.epicbattle.view.TowerView;
import com.quadrolord.epicbattle.view.ViewLoader;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class BattleScreen extends AbstractScreen {

    private Stage mBackStage;

    private Stage mFrontStage;

    public BattleScreen(EpicBattle adapter, Game game) {
        super(adapter, game);

        mPx = 2;

        mStage.setViewport(new FitViewport(400 * mPx, 300 * mPx));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        mBackStage = new Stage(new FitViewport(400 * mPx, 300 * mPx));
        mBackStage.getRoot().setScale(mPx);
        mBackStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        mFrontStage = new Stage(new FitViewport(400 * mPx, 300 * mPx));
        mFrontStage.getRoot().setScale(mPx);
        mFrontStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Gdx.input.setInputProcessor(mFrontStage);

        new Background(this, mBackStage, mStage.getCamera());

        new PauseButton(this, mFrontStage);

        new CreateBulletPanel(this, mFrontStage);

//        TextureRegion tr1 = new TextureRegion(mSkin.get("test-texture", Texture.class), 64, 64);
//        TextureRegion tr2 = new TextureRegion(mSkin.get("test-texture", Texture.class), 64, 0, 64, 64);
//        NinePatch np1 = new NinePatch(tr1, 10, 10, 10, 10);
//        NinePatch np2 = new NinePatch(tr2, 15, 15, 15, 15);
//        NinePatchDrawable npd1 = new NinePatchDrawable(np1);
//        NinePatchDrawable npd2 = new NinePatchDrawable(np2);
//        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
//                npd1,
//                npd2,
//                null,
//                mSkin.getFont("default")
//        );
//        mSkin.add("text-button-style-default", textButtonStyle);
//
//        TextButton btn = new TextButton("OK", textButtonStyle);
//        btn.setBounds(100, 200, 100, 50);
//        mFrontStage.addActor(btn);

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
            public void onBulletAttack(AbstractBullet attacker, GameUnit target) {
                BulletUnitView buv = ((BulletUnitView) attacker.getViewObject());
                buv.startAttackAnimation();
                new AttackAnimation(attacker, target, mSkin, mStage);
            }

            @Override
            public void onBulletCreate(AbstractBullet bullet) {
                Gdx.app.log("", "fire with " + bullet.getInfo().getTitle() + " at " + bullet.getX());

                Class <? extends BulletUnitView> viewClass = bullet.getInfo().getViewClass();

                try {
                    viewClass.getConstructor(AbstractBullet.class, AbstractScreen.class).newInstance(bullet, screen);
                } catch (Exception e) {
                    new BulletUnitView(bullet, screen) {
                        @Override
                        protected SpriteAnimationDrawable getRunningAnimation(AbstractScreen screen) {
                            return null;
                        }

                        @Override
                        protected SpriteAnimationDrawable getAttackingAnimation(AbstractScreen screen) {
                            return null;
                        }

                        @Override
                        protected SpriteAnimationDrawable getDeadAnimation(AbstractScreen screen) {
                            return null;
                        }
                    };
                }
            }

            @Override
            public void onBulletCreateFailCash(float current, int required) {
                Gdx.app.log("", "create fail cash: " + current + "/" + required);
            }

            @Override
            public void onBulletCreateFailCooldown() {
                Gdx.app.log("", "create fail cooldown");
            }

            @Override
            public void onBulletRemove(AbstractBullet bullet) {
                BulletUnitView buv = ((BulletUnitView) bullet.getViewObject());
                buv.startDeadAnimation();
            }

            @Override
            public void onTowerCreate(final Tower tower) {

                if (tower.getSpeedRatio() > 0) {
                    new CashLabel(tower, screen, mFrontStage);
                }

                TowerView tv = new TowerView(tower, screen);
                tv.setHpLabel(new TowerHp(tower, screen));

                tv.addListener(new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        mGame.createUnit(tower, Simple.class);
                    }

                });
            }

            @Override
            public void onTowerDeath(Tower tower) {
                Gdx.app.log("towers", "tower death");
                new TowerDeath((TowerView)tower.getViewObject(), screen);
            }

        });

        mGame.startLevel();
    }

    @Override
    public void draw(float delta) {
        mBackStage.act(delta);
        mBackStage.draw();

        mStage.act(delta);
        mStage.draw();

        mFrontStage.act(delta);
        mFrontStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
        mBackStage.getViewport().update(width, height, true);
        mFrontStage.getViewport().update(width, height, true);
    }

    @Override
    public void switchIn() {
        Gdx.input.setInputProcessor(mFrontStage);
    }

    @Override
    public void update(float delta) {

        if (Gdx.input.isTouched()) {
            mStage.getCamera().position.x = Math.max(
                    mGame.getTowerLeft() * mStage.getRoot().getScaleX(),
                    Math.min(
                            mGame.getTowerRight() * mStage.getRoot().getScaleX(),
                            mStage.getCamera().position.x - Gdx.input.getDeltaX()
                    )
            );
        }

        mGame.act(delta);
    }

}

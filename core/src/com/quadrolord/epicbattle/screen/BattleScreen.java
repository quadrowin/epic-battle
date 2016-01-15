package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.GameListener;
import com.quadrolord.epicbattle.logic.GameUnit;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.skill.TowerRandomBleed;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.screen.battle.AttackAnimation;
import com.quadrolord.epicbattle.screen.battle.Background;
import com.quadrolord.epicbattle.screen.battle.BulletPanel;
import com.quadrolord.epicbattle.screen.battle.Cash;
import com.quadrolord.epicbattle.screen.battle.DebugPanel;
import com.quadrolord.epicbattle.screen.battle.LevelName;
import com.quadrolord.epicbattle.screen.battle.PauseButton;
import com.quadrolord.epicbattle.screen.battle.TowerHp;
import com.quadrolord.epicbattle.view.BulletUnitView;
import com.quadrolord.epicbattle.view.SpriteAnimationDrawable;
import com.quadrolord.epicbattle.view.TowerDeath;
import com.quadrolord.epicbattle.view.TowerView;
import com.quadrolord.epicbattle.view.ViewLoader;
import com.quadrolord.epicbattle.view.sounds.SoundManager;
import com.quadrolord.epicbattle.view.visualization.AbstractVisualization;
import com.quadrolord.epicbattle.view.visualization.TowerRandomBleedView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class BattleScreen extends AbstractScreen {

    private Stage mBackStage;

    private Stage mFrontStage;

    /**
     * Представления, которые нужно удалить со сцены при очистке уровня
     */
    private Array<Actor> mLevelViews = new Array<Actor>();

    private static final ArrayMap<Class, Class<? extends AbstractVisualization>> mVisualization = new ArrayMap<Class, Class<? extends AbstractVisualization>>();

    {
        mVisualization.put(TowerRandomBleed.class, TowerRandomBleedView.class);
    }

    public BattleScreen(EpicBattle adapter, Level level) {
        super(adapter);

        mGame.getSoundManager().loadSounds(
                this,
                new String[] {
                        SoundManager.EVENT_DEFEAT,
                        SoundManager.MENU_CLICK,
                        SoundManager.SKILL_TOWER_BLEED1,
//                        SoundManager.SKILL_TOWER_CREAK1,
                }
        );

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

        new BulletPanel(this, mFrontStage);

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
            public void beforeStageClear() {
                for (Iterator<Actor> iter = mLevelViews.iterator(); iter.hasNext(); ) {
                    iter.next().remove();
                }
            }

            @Override
            public void onBulletAttack(AbstractBullet attacker, GameUnit target) {
                BulletUnitView buv = ((BulletUnitView) attacker.getViewObject());
                buv.startAttackAnimation();
                new AttackAnimation(attacker, target, mSkin, mStage);
            }

            @Override
            public void onBulletCreate(AbstractBullet bullet) {
                Gdx.app.log("", "fire with " + bullet.getInfo().getTitle() + " at " + bullet.getX());

                Class<? extends BulletUnitView> viewClass = bullet.getInfo().getViewClass();
                BulletUnitView view;

                try {
                    view = viewClass.getConstructor(AbstractBullet.class, AbstractScreen.class).newInstance(bullet, screen);
                } catch (Exception e) {
                    view = new BulletUnitView(bullet, screen) {
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

                mLevelViews.add(view);
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
            public void onLevelDefeat() {
                DefeatScreen ds = new DefeatScreen(screen);
                mAdapter.switchToScreen(ds, false);
            }

            @Override
            public void onLevelVictory() {
                VictoryScreen vs = new VictoryScreen(screen);
                mAdapter.switchToScreen(vs, false);
            }

            @Override
            public void onTowerCreate(final Tower tower) {

                if (tower.getSpeedRatio() > 0) {
                    Cash cl = new Cash(tower, screen, mFrontStage);
                    mLevelViews.add(cl);
                }

                TowerView tv = new TowerView(tower, screen);
                mLevelViews.add(tv);

                TowerHp twHp = new TowerHp(tower, screen);
                tv.setHpLabel(twHp);
                mLevelViews.add(twHp);
            }

            @Override
            public void onTowerDeath(Tower tower) {
                Gdx.app.log("towers", "tower death");
                Actor td = new TowerDeath((TowerView) tower.getViewObject(), screen);
                mLevelViews.add(td);
            }

            @Override
            public void onVisualEvent(float x, float y, Class visualEventClass) {
                Class <? extends AbstractVisualization> avc = mVisualization.get(visualEventClass);
                try {
                    avc.getConstructor(AbstractScreen.class, Float.TYPE, Float.TYPE).newInstance(screen, x, y);
                } catch (Exception e) {
                    Gdx.app.error("onVisualEvent", visualEventClass.getName());
                }
            }

        });

        if (level == null) {
            level = mGame.getCampaignManager().getLevel(0, 0);
        }

        new LevelName(level, mSkin, mFrontStage);
        mGame.startLevel(level);

        new DebugPanel(this, mFrontStage);
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

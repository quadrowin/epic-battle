package com.quadrolord.epicbattle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.quadrolord.ejge.AbstractGameAdapter;
import com.quadrolord.epicbattle.logic.GameListener;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.profile.PlayerProfile;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;
import com.quadrolord.epicbattle.logic.skill.passive.TowerRandomBleed;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.tower.GameUnit;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.logic.tower.BattlePhysic;
import com.quadrolord.epicbattle.screen.battle.ActiveSkillButton;
import com.quadrolord.epicbattle.screen.battle.AttackAnimation;
import com.quadrolord.epicbattle.screen.battle.Background;
import com.quadrolord.epicbattle.screen.battle.BangAnimation;
import com.quadrolord.epicbattle.screen.battle.BleedAnimation;
import com.quadrolord.epicbattle.screen.battle.BulletPanel;
import com.quadrolord.epicbattle.screen.battle.Cash;
import com.quadrolord.epicbattle.screen.battle.LevelName;
import com.quadrolord.epicbattle.screen.battle.PauseButton;
import com.quadrolord.epicbattle.screen.battle.TowerHp;
import com.quadrolord.epicbattle.screen.debug.DebugPanel;
import com.quadrolord.epicbattle.view.bullet.AbstractBulletView;
import com.quadrolord.epicbattle.view.Sounds;
import com.quadrolord.ejge.view.TextureManager;
import com.quadrolord.epicbattle.view.TowerDeath;
import com.quadrolord.epicbattle.view.TowerView;
import com.quadrolord.epicbattle.view.visualization.AbstractVisualization;
import com.quadrolord.epicbattle.view.visualization.TowerRandomBleedView;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class BattleScreen extends com.quadrolord.ejge.view.AbstractScreen {

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

    public BattleScreen(AbstractGameAdapter adapter, Level level) {
        super(adapter);

        adapter.getSoundManager().loadSounds(
                this,
                new String[]{
                        Sounds.EVENT_DEFEAT,
                        Sounds.MENU_CLICK,
                        Sounds.SKILL_TOWER_BLEED1,
//                        Sounds.SKILL_TOWER_CREAK1,
                }
        );

        ScalingViewport vp = new FillViewport(SES.SCREEN_WIDTH, SES.SCREEN_HEIGHT);

        mStage.setViewport(vp);
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        mBackStage = new Stage(vp);
        mBackStage.getRoot().setScale(mPx);
        mBackStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        mFrontStage = new Stage(new FitViewport(SES.SCREEN_WIDTH * mPx, SES.SCREEN_HEIGHT * mPx));
        mFrontStage.getRoot().setScale(mPx);
        mFrontStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Gdx.input.setInputProcessor(mFrontStage);

        new Background(mBackStage, mStage.getCamera());

        new PauseButton(this, mFrontStage);

        TextureManager vl = new TextureManager(mSkin);
        vl.loadTextures(
                mSkin,
                new String[]{
                        "tower", "tower.png"
                }
        );

        final com.quadrolord.ejge.view.AbstractScreen screen = this;
        get(BattleGame.class).setListener(new GameListener() {

            @Override
            public void beforeStageClear() {
                for (Iterator<Actor> iter = mLevelViews.iterator(); iter.hasNext(); ) {
                    iter.next().remove();
                }
            }

            @Override
            public void onBulletAttack(AbstractBullet attacker, GameUnit target) {
                new AttackAnimation(attacker, target, mSkin, mStage);
            }

            @Override
            public void onBulletInjure(AbstractBullet target) {
                AbstractBulletView buv = ((AbstractBulletView) target.getViewObject());
                float x = target.getX() - buv.getScaleX() * target.getWidth() / 2 + 15;

                new BleedAnimation(screen, x, buv.getHeight() / 2 - 15 + buv.getY());
            }

            @Override
            public void onBulletCreate(AbstractBullet bullet) {
                Gdx.app.log("", "fire with " + bullet.getSkill().getInfo().getName() + " at " + bullet.getX());

                Class<? extends AbstractBulletView> viewClass = ((AbstractBulletSkill)bullet.getSkill().getInfo()).getViewClass();
                AbstractBulletView view;

                try {
                    view = viewClass.getConstructor(AbstractBullet.class, com.quadrolord.ejge.view.AbstractScreen.class).newInstance(bullet, screen);
                } catch (Exception e) {
                    Gdx.app.error(screen.getClass().getName(), "Bullet view class not found: " + viewClass.getName());
                    view = new AbstractBulletView(bullet, screen) {

                        @Override
                        protected void initAnimations(com.quadrolord.ejge.view.AbstractScreen screen) {

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
                AbstractBulletView buv = ((AbstractBulletView) bullet.getViewObject());
                buv.remove();
            }

            @Override
            public void onLevelDefeat() {
                DefeatScreen ds = new DefeatScreen(screen);
                mAdapter.switchToScreen(ds, false);
            }

            @Override
            public void onLevelStart() {
                Level level = get(BattleGame.class).getLevel();
                new LevelName(level, mFrontStage);
            }

            @Override
            public void onLevelVictory() {
                Level level = get(BattleGame.class).getLevel();
                PlayerProfile prof = get(ProfileManager.class).getProfile();
                prof.incExperience(level.getRewardExp());
                get(ProfileManager.class).saveProfile();
                VictoryScreen vs = new VictoryScreen(screen, level);
                mAdapter.switchToScreen(vs, false);
            }

            @Override
            public void onTowerCreate(final Tower tower) {

                if (tower.isPlayer()) {
                    // башня игрока
                    Cash cl = new Cash(tower, screen, mFrontStage);
                    mLevelViews.add(cl);

                    // панель вызова юнитов
                    BulletPanel bp = new BulletPanel(screen, mFrontStage);
                    mLevelViews.add(bp);

                    // активный скил
                    ActiveSkillButton asb = new ActiveSkillButton(screen, mFrontStage);
                    mLevelViews.add(asb);
                }

                TowerView tv = new TowerView(tower, screen);
                mLevelViews.add(tv);

                TowerHp twHp = new TowerHp(tower, screen);
                tv.setHpLabel(twHp);
                mLevelViews.add(twHp);
            }

            @Override
            public void onTowerInjure(Tower tower) {
                new BangAnimation(screen, tower);
            }

            @Override
            public void onTowerDeath(Tower tower) {
                Gdx.app.log("towers", "tower death");
                Actor td = new TowerDeath((TowerView) tower.getViewObject(), screen);
                mLevelViews.add(td);
            }

            @Override
            public void onVisualEvent(float x, float y, Class visualEventClass) {
                Class<? extends AbstractVisualization> avc = mVisualization.get(visualEventClass);
                try {
                    avc.getConstructor(com.quadrolord.ejge.view.AbstractScreen.class, Float.TYPE, Float.TYPE).newInstance(screen, x, y);
                } catch (Exception e) {
                    Gdx.app.error("onVisualEvent", visualEventClass.getName());
                }
            }

        });

        if (level == null) {
            level = get(BattleGame.class).getCampaignManager().getLevel(0, 0);
        }

        get(BattleGame.class).startLevel(level);

        mFrontStage.addListener(new EventListener() {

            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent)) {
                    return false;
                }
                InputEvent ie = ((InputEvent) event);
                if (ie.getType() != InputEvent.Type.scrolled) {
                    return false;
                }
                float deltaX = ie.getScrollAmount();
                OrthographicCamera oc = (OrthographicCamera)mStage.getCamera();

                float maxZoom = 3;
                float minZoom = 0.5f;
                oc.zoom = Math.max(
                        minZoom,
                        Math.min(
                                maxZoom,
                                oc.zoom + deltaX * 0.1f
                        )
                );
                float zeroY = BattlePhysic.ZERO_Y;
                oc.position.y = oc.zoom * oc.viewportHeight / 2 + zeroY * (1 - oc.zoom) * getPx();
                return true;
            }
        });

        new DebugPanel(this, mFrontStage);
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(0.65f, 0.59f, 0.70f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



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
                    get(BattleGame.class).getTowerLeft() * mStage.getRoot().getScaleX(),
                    Math.min(
                            get(BattleGame.class).getTowerRight() * mStage.getRoot().getScaleX(),
                            mStage.getCamera().position.x - Gdx.input.getDeltaX()
                    )
            );
        }

        get(BattleGame.class).act(delta);
    }

}

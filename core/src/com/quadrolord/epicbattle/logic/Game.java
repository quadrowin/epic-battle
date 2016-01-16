package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.BulletInfoManager;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.Big;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.logic.campaign.CampaignManager;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.profile.PlayerProfile;
import com.quadrolord.epicbattle.logic.profile.ProfileManager;
import com.quadrolord.epicbattle.logic.profile.ProfileSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractSkill;
import com.quadrolord.epicbattle.logic.skill.DummySkill;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.logic.tower.controller.AbstractController;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerAi;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerPlayer;
import com.quadrolord.epicbattle.logic.utils.PlatformServices;
import com.quadrolord.epicbattle.view.sounds.SoundManager;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class Game {

    private Array<AbstractController> mControllers = new Array<AbstractController>();
    private Array<Tower> mTowers = new Array<Tower>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    private Level mLevel;

    private GameListener mListener;

    private ControllerPlayer mPlayerController = new ControllerPlayer(this);

    private float mTowerLeft;

    private float mTowerRight;

    private float mLevelTime;

    private CampaignManager mCampaignManager = new CampaignManager();

    private PlatformServices mPlatformServices;

    private ProfileManager mProfileManager;

    private BulletInfoManager mBulletInfoManager = new BulletInfoManager();

    private SoundManager mSoundManager = new SoundManager();

    private Tower mPlayerTower;
    private Tower mEnemyTower;

    public Game(PlatformServices platformServices) {
        mPlatformServices = platformServices;
        mProfileManager = new ProfileManager(mPlatformServices);

        mPlayerTower = new Tower(this);
        mEnemyTower = new Tower(this);

        mTowers.add(mPlayerTower);
        mTowers.add(mEnemyTower);

        mPlayerTower.getBulletClasses().add(Simple.class);
        mPlayerTower.getBulletClasses().add(Big.class);

        ArrayMap<Class<? extends AbstractBullet>, Integer> bulletLevels = new ArrayMap<Class<? extends AbstractBullet>, Integer>();
        bulletLevels.put(Big.class, 3);
        bulletLevels.put(Simple.class, 8);
        getPlayerTower().setBulletLevels(bulletLevels);

        ArrayMap<Class<? extends AbstractBullet>, Integer> levels = mPlayerTower.getBulletLevels();
        Iterator<Class<? extends AbstractBullet>> iter = mPlayerTower.getBulletClasses().iterator();

        while (iter.hasNext()) {
            Class<? extends AbstractBullet> bulletClass = iter.next();

            if (levels.containsKey(bulletClass)) {
                mPlayerTower.getBulletInfo(bulletClass).setLevel(levels.get(bulletClass));
            }
        }
    }

    public void act(float delta) {
        mLevelTime += delta;

        for (Iterator<AbstractController> iter = mControllers.iterator(); iter.hasNext(); ) {
            iter.next().act(delta);
        }

        for (Iterator<Tower> towers = mTowers.iterator(); towers.hasNext(); ) {
            towers.next().act(delta);
        }

        int i = 0;
        for (Iterator<AbstractBullet> units = mBullets.iterator(); units.hasNext(); i++) {
            AbstractBullet unit = units.next();

            if (unit.isDied()) {
                units.remove();
                mListener.onBulletRemove(unit);
            } else {
                unit.act(delta);
            }
        }
    }

    public void spawnTower(Tower tower, float position, float speedRatio, AbstractController controller) {
        if (position < mTowerLeft) {
            mTowerLeft = position;
        }

        if (position > mTowerRight) {
            mTowerRight = position;
        }

        tower.spawnReset();
        tower.setX(position);
        tower.setSpeedRatio(speedRatio);
        tower.setWidth(60);
        tower.setCash(0);
        controller.setTower(tower);

        mControllers.add(controller);
        mTowers.add(tower);

        mListener.onTowerCreate(tower);
    }

    public void createUnit(Tower tower, Class<? extends AbstractBullet> workerClass) {
        AbstractBullet bullet;
        try {
            bullet = workerClass.getConstructor(Game.class).newInstance(this);
        } catch (Exception e) {
            bullet = new Simple(this);
        }

        BulletInfo bi = tower.getBulletInfo(workerClass);
        bullet.setInfo(bi);

        if (tower.isInCooldown(bullet)) {
            mListener.onBulletCreateFailCooldown();
            return ;
        }

        if (!tower.hasCash(bullet)) {
            mListener.onBulletCreateFailCash(tower.getCash(), bi.getCost());
            return;
        }

        bullet.setWidth(30);
        bullet.setMaxHp(bi.getMaxHp());
        bullet.setHp(bullet.getMaxHp());
        bullet.setVelocity(bi.getMoveSpeed() * tower.getSpeedRatio());
        bullet.setX(tower.getX() + tower.getWidth() / 2);

        tower.setCash(tower.getCash() - bi.getCost());
        tower.addUnit(bullet);
        tower.toCooldown(bullet);

        mBullets.add(bullet);
        mListener.onBulletCreate(bullet);

        mListener.onBulletAttack(bullet, tower);
    }

    public Array<AbstractBullet> getBullets() {
        return mBullets;
    }

    public GameListener getListener() {
        return mListener;
    }

    public ControllerPlayer getPlayerController() {
        return mPlayerController;
    }

    public ProfileManager getProfileManager() {
        return mProfileManager;

    }

    public BulletInfoManager getBulletInfoManager() {
        return mBulletInfoManager;
    }

    public SoundManager getSoundManager() {
        return mSoundManager;
    }

    private void initPlayerTower() {
        spawnTower(mPlayerTower, 10, 1, mPlayerController);
        PlayerProfile profile = mProfileManager.getProfile();
        for (int i = 0; i < profile.getSkills().length; i++) {
            ProfileSkill skillInfo = profile.getSkills()[i];
            AbstractSkill skill;
            try {
                skill = skillInfo.getSkillClass().newInstance();
                Gdx.app.log("initPlayerTower", "Skill inited " + skillInfo.getSkillName());
            } catch (Exception e) {
                Gdx.app.error("initPlayerTower", "Error skill " + skillInfo.getSkillName());
                skill = new DummySkill();
            }
            skill.setLevel(skillInfo.getLevel());
            skill.initTower(mPlayerTower);
        }
    }

    public void setListener(GameListener listener) {
        mListener = listener;
    }

    public void startLevel(Level level) {
        mListener.beforeStageClear();
        mControllers.clear();

        mBullets.clear();
        mTowers.clear();

        mLevel = level;
        mTowerLeft = 10;
        mTowerRight = 640;

        initPlayerTower();

        ControllerAi ai = new ControllerAi(this);
        ai.setEnemyParams(level.getEnemyTower());

        spawnTower(mEnemyTower, level.getEnemyTower().getX(), -1, ai);
        mEnemyTower.setMaxHp(level.getEnemyTower().getMaxHp());
        mEnemyTower.setHp(level.getEnemyTower().getMaxHp());

        mLevelTime = 0;

        mListener.onLevelStart();
    }

    public void towerDeath(Tower tower) {
        mListener.onTowerDeath(tower);
        if (tower.isPlayer()) {
            mListener.onLevelDefeat();
        } else {
            mListener.onLevelVictory();
        }
    }

    public Level getLevel() {
        return mLevel;
    }

    public float getLevelTime() {
        return mLevelTime;
    }

    public CampaignManager getCampaignManager() {
        return mCampaignManager;
    }

    public Array<Tower> getTowers() {
        return mTowers;
    }

    public Tower getPlayerTower() {
        return mPlayerTower;
    }

    public float getTowerLeft() {
        return mTowerLeft;
    }

    public float getTowerRight() {
        return mTowerRight;
    }

}

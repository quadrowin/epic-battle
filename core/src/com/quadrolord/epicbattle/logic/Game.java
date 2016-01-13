package com.quadrolord.epicbattle.logic;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.quadrolord.epicbattle.logic.bullet.BulletInfo;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.Big;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.logic.campaign.CampaignManager;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.logic.tower.controller.AbstractController;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerAi;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerPlayer;

import java.util.Iterator;

/**
 * Created by Quadrowin on 08.01.2016.
 */
public class Game {

    private Array<AbstractController> mControllers = new Array<AbstractController>();
    private Array<Tower> mTowers = new Array<Tower>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    private ArrayMap<Class<? extends AbstractBullet>, BulletInfo> mBulletInfos = new ArrayMap<Class<? extends AbstractBullet>, BulletInfo>();
    private Array<Class<? extends AbstractBullet>> mPlayerBulletClasses = new Array<Class<? extends AbstractBullet>>();

    private Level mLevel;

    private GameListener mListener;

    private ControllerPlayer mPlayerController = new ControllerPlayer(this);

    private float mTowerLeft;

    private float mTowerRight;

    private float mLevelTime;

    private CampaignManager mCampaignManager = new CampaignManager();

    private Tower mPlayerTower;

    public Game() {
        mPlayerBulletClasses.add(Simple.class);
        mPlayerBulletClasses.add(Big.class);
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

    public Tower createTower(float position, float speedRatio, AbstractController controller) {
        if (position < mTowerLeft) {
            mTowerLeft = position;
        }
        if (position > mTowerRight) {
            mTowerRight = position;
        }
        Tower tower = new Tower(this);
        tower.setX(position);
        tower.setSpeedRatio(speedRatio);
        tower.setWidth(60);
        controller.setTower(tower);
        mTowers.add(tower);
        mListener.onTowerCreate(tower);
        return tower;
    }

    public void createUnit(Tower tower, Class<? extends AbstractBullet> workerClass) {
        AbstractBullet bullet;
        try {
            bullet = workerClass.getConstructor(Game.class).newInstance(this);
        } catch (Exception e) {
            bullet = new Simple(this);
        }

        BulletInfo bi = getBulletInfo(workerClass);
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
        bullet.setX(tower.getX());

        tower.setCash(tower.getCash() - bi.getCost());
        tower.addUnit(bullet);
        tower.toCooldown(bullet);

        mBullets.add(bullet);
        mListener.onBulletCreate(bullet);

        mListener.onBulletAttack(bullet, tower);
    }

    public BulletInfo getBulletInfo(Class<? extends AbstractBullet> workerClass) {
        BulletInfo bi = mBulletInfos.get(workerClass);
        if (bi == null) {
            bi = new BulletInfo();
            mBulletInfos.put(workerClass, bi);
            AbstractBullet bullet;
            try {
                bullet = workerClass.getConstructor(Game.class).newInstance(this);
            } catch (Exception e) {
                bullet = new Simple(this);
            }
            bullet.initInfo(bi);
        }
        return bi;
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

    public void setListener(GameListener listener) {
        mListener = listener;
    }

    public void startLevel(Level level) {
        mListener.beforeStageClear();
        mControllers.clear();
        mControllers.add(mPlayerController);
        mBullets.clear();
        mTowers.clear();

        mLevel = level;
        mTowerLeft = 10;
        mTowerRight = 640;

        mPlayerTower = createTower(10, 1, mPlayerController);

        ControllerAi ai = new ControllerAi(this);
        ai.setEnemyParams(level.getEnemyTower());
        Tower tower = createTower(level.getEnemyTower().getX(), -1, ai);
        tower.setMaxHp(level.getEnemyTower().getMaxHp());
        tower.setHp(level.getEnemyTower().getMaxHp());
        mControllers.add(ai);

        mLevelTime = 0;
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

    public Array<Class<? extends AbstractBullet>> getPlayerBulletClasses() {
        return mPlayerBulletClasses;
    }

    public float getTowerLeft() {
        return mTowerLeft;
    }

    public float getTowerRight() {
        return mTowerRight;
    }

}

package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.EpicBattle;
import com.quadrolord.epicbattle.logic.GameListener;
import com.quadrolord.epicbattle.logic.bullet.BulletInfoManager;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.logic.bullet.worker.simple.SimpleBullet;
import com.quadrolord.epicbattle.logic.campaign.CampaignManager;
import com.quadrolord.epicbattle.logic.campaign.EnemyTower;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.profile.PlayerProfile;
import com.quadrolord.epicbattle.logic.profile.ProfileSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractBulletSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.skill.SkillManager;
import com.quadrolord.epicbattle.logic.skill.bullet.Simple;
import com.quadrolord.epicbattle.logic.skill.passive.MoneyGrowth;
import com.quadrolord.epicbattle.logic.tower.controller.AbstractController;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerAi;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerPlayer;

import java.util.Iterator;

/**
 * Игра - битва
 * Created by Quadrowin on 13.03.2016.
 */
public class BattleGame {

    private float mDeathDuration = 3;

    private EpicBattle mGame;

    private Array<AbstractController> mControllers = new Array<AbstractController>();
    private Array<Tower> mTowers = new Array<Tower>();
    private Array<AbstractBullet> mBullets = new Array<AbstractBullet>();

    private Level mLevel;

    private GameListener mListener;

    private ControllerPlayer mPlayerController = new ControllerPlayer(this);

    private float mTowerLeft;

    private float mTowerRight;

    private float mLevelTime;

    private float mTimeFactor = 0.6f;

    private float mZeroY = 70;

    private CampaignManager mCampaignManager = new CampaignManager();

    private BulletInfoManager mBulletInfoManager = new BulletInfoManager();

    private SkillManager mSkillManager = new SkillManager(this);

    private Tower mPlayerTower;

    private Tower mEnemyTower;

    public BattleGame(EpicBattle game) {
        mGame = game;

        mPlayerTower = new Tower(this);
        mEnemyTower = new Tower(this);

        mTowers.add(mPlayerTower);
        mTowers.add(mEnemyTower);
    }

    public void act(float delta) {
        delta *= mTimeFactor;
        mLevelTime += delta;

        for (Iterator<AbstractController> it = mControllers.iterator(); it.hasNext(); ) {
            it.next().act(delta);
        }

        for (Iterator<Tower> it = mTowers.iterator(); it.hasNext(); ) {
            it.next().act(delta);
        }

        for (Iterator<AbstractBullet> it = mBullets.iterator(); it.hasNext(); ) {
            AbstractBullet unit = it.next();

            if (unit.isDied() && unit.getStateTime() >= mDeathDuration) {
                it.remove();
                mListener.onBulletRemove(unit);
            } else {
                unit.act(delta);
            }
        }
    }

    public void foldBackUnit(AbstractBullet blt) {
        blt.setState(BulletState.FOLD_BACK, AbstractBullet.FOLD_BACK_TIME);
    }

    public void towerReset(Tower tower, float position, float direction) {
        if (position < mTowerLeft) {
            mTowerLeft = position;
        }

        if (position > mTowerRight) {
            mTowerRight = position;
        }

        tower.spawnReset();
        tower.setY(mZeroY);
        tower.setX(position);
        tower.setDirection(direction);
        tower.setWidth(60);
        tower.setHeight(60);
        tower.setCash(0);
    }

    public void towerFinish(Tower tower, AbstractController controller) {
        tower.setHp(tower.getMaxHp());
        controller.setTower(tower);

        mControllers.add(controller);
        mTowers.add(tower);

        mListener.onTowerCreate(tower);
    }

    public AbstractBullet createUnit(Tower tower, SkillItem skill, boolean checkCooldown, boolean useResources) {
        if (checkCooldown && skill.isInCooldown()) {
            mListener.onBulletCreateFailCooldown();
            return null;
        }

        if (!(skill.getInfo() instanceof AbstractBulletSkill)) {
            return null;
        }

        if (useResources && !tower.hasCash(skill)) {
            mListener.onBulletCreateFailCash(tower.getCash(), skill.getCost());
            return null;
        }

        AbstractBulletSkill bs = (AbstractBulletSkill) skill.getInfo();
        AbstractBullet bullet;

        try {
            bullet = (AbstractBullet)bs.getBulletClass().getConstructor(BattleGame.class).newInstance(this);
        } catch (Exception e) {
            Gdx.app.error("Game.createUnit", "error create bullet " + bs.getName());
            e.printStackTrace();
            bullet = new SimpleBullet(this);
        }
        bullet.setSkill(skill);
        ((AbstractBulletSkill) skill.getInfo()).getBulletLogic().initBullet(skill, bullet);

        if (useResources) {
            tower.setCash(tower.getCash() - skill.getCost());
        }
        tower.addUnit(bullet);
        tower.toCooldown(skill);

        mBullets.add(bullet);
        mListener.onBulletCreate(bullet);

        mListener.onBulletAttack(bullet, tower);
        return bullet;
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

    public BulletInfoManager getBulletInfoManager() {
        return mBulletInfoManager;
    }

    public SkillManager getSkillManager() {
        return mSkillManager;
    }

    private void initAiTower(Tower tower, EnemyTower enemyTower) {
        ControllerAi ai = new ControllerAi(this);
        ai.setEnemyParams(enemyTower);

        towerReset(tower, enemyTower.getX(), GameUnit.DIRECTION_LEFT);

        setTowerSkill(tower, Simple.class, 1);
        setTowerSkill(tower, MoneyGrowth.class, 10);
        tower.setMaxHp(enemyTower.getMaxHp());
        tower.setHp(enemyTower.getMaxHp());

        towerFinish(tower, ai);
    }

    private void initPlayerTower(Tower tower) {
        towerReset(tower, 10, GameUnit.DIRECTION_RIGHT);
        PlayerProfile profile = mGame.getProfileManager().getProfile();
        // доступные скилы
        setTowerSkill(tower, MoneyGrowth.class, 1);
        for (Iterator<ProfileSkill> it = profile.getSkills().iterator(); it.hasNext(); ) {
            ProfileSkill profileSkill = it.next();
            AbstractSkillEntity skillEntity = mSkillManager.get(profileSkill.getSkillClass());
            tower.addSkillEntity(
                    skillEntity,
                    profileSkill.getLevel()
            );
            Gdx.app.log("initPlayerTower", "Skill inited " + profileSkill.getSkillName());
        }
//        // доступные виды юнитов
//        for (Iterator<ProfileBuilding> it = profile.getBullets().iterator(); it.hasNext(); ) {
//            ProfileBuilding pb = it.next();
//            Gdx.app.log("Game.initPlayerTower", "create " +  pb.getBulletName());
//            try {
//                tower.addBulletSkill(
//                        (Class<? extends AbstractBullet>)Class.forName(pb.getBulletName()),
//                        pb.getLevel()
//                );
//            } catch (Exception e) {
//                Gdx.app.error("Game.initPlayerTower", "Bullet skill class not found: " + pb.getBulletName());
//            }
//        }
        Gdx.app.log("Game.initPlayerTower", "player bullet skills: " + tower.getBulletSkills().size);
        towerFinish(tower, mPlayerController);
    }

    public void setListener(GameListener listener) {
        mListener = listener;
    }

    public SkillItem setTowerSkill(Tower tower, Class<? extends AbstractSkillEntity> skill, int level) {
        return tower.addSkillEntity(mSkillManager.get(skill), level);
    }

    public void startLevel(Level level) {
        mListener.beforeStageClear();
        mControllers.clear();

        mBullets.clear();
        mTowers.clear();

        mLevel = level;
        mTowerLeft = 10;
        mTowerRight = 640;

        initPlayerTower(mPlayerTower);

        initAiTower(mEnemyTower, level.getEnemyTower());

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

    public void upgradeProfileSkill(ProfileSkill skill) {
        int upgradeCost = mSkillManager.get(skill.getSkillClass()).getBaseUpgradingCost();
        for (int i = 0; i < skill.getLevel(); i++) {
            upgradeCost += upgradeCost;
        }
        mGame.getProfileManager().getProfile().decExperience( upgradeCost );
        skill.setLevel(skill.getLevel() + 1);
        mGame.getProfileManager().saveProfile();
    }

}

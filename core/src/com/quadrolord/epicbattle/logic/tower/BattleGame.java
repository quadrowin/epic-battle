package com.quadrolord.epicbattle.logic.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.Game;
import com.quadrolord.epicbattle.logic.GameListener;
import com.quadrolord.epicbattle.logic.bullet.BulletInfoManager;
import com.quadrolord.epicbattle.logic.bullet.BulletSkill;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.BulletState;
import com.quadrolord.epicbattle.logic.bullet.worker.Simple;
import com.quadrolord.epicbattle.logic.campaign.CampaignManager;
import com.quadrolord.epicbattle.logic.campaign.EnemyTower;
import com.quadrolord.epicbattle.logic.campaign.Level;
import com.quadrolord.epicbattle.logic.profile.PlayerProfile;
import com.quadrolord.epicbattle.logic.profile.ProfileSkill;
import com.quadrolord.epicbattle.logic.skill.AbstractSkillEntity;
import com.quadrolord.epicbattle.logic.skill.SkillItem;
import com.quadrolord.epicbattle.logic.skill.SkillManager;
import com.quadrolord.epicbattle.logic.tower.controller.AbstractController;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerAi;
import com.quadrolord.epicbattle.logic.tower.controller.ControllerPlayer;

import java.util.Iterator;

/**
 * Игра - битва
 * Created by Quadrowin on 13.03.2016.
 */
public class BattleGame {

    private Game mGame;

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

    private BulletInfoManager mBulletInfoManager = new BulletInfoManager();

    private SkillManager mSkillManager = new SkillManager();

    private Tower mPlayerTower;

    private Tower mEnemyTower;

    public BattleGame(Game game) {
        mGame = game;

        mPlayerTower = new Tower(this);
        mEnemyTower = new Tower(this);

        mTowers.add(mPlayerTower);
        mTowers.add(mEnemyTower);
    }

    public void act(float delta) {
        mLevelTime += delta;

        for (Iterator<AbstractController> it = mControllers.iterator(); it.hasNext(); ) {
            it.next().act(delta);
        }

        for (Iterator<Tower> it = mTowers.iterator(); it.hasNext(); ) {
            it.next().act(delta);
        }

        for (Iterator<AbstractBullet> it = mBullets.iterator(); it.hasNext(); ) {
            AbstractBullet unit = it.next();

            if (unit.isDied()) {
                it.remove();
                mListener.onBulletRemove(unit);
            } else {
                unit.act(delta);
            }
        }
    }

    public void foldBackUnit(AbstractBullet blt) {
        blt.setState(BulletState.FOLD_BACK);
    }

    public void towerReset(Tower tower, float position, float speedRatio) {
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
    }

    public void towerFinish(Tower tower, AbstractController controller) {
        controller.setTower(tower);

        mControllers.add(controller);
        mTowers.add(tower);

        mListener.onTowerCreate(tower);
    }

    public AbstractBullet createUnit(Tower tower, Class<? extends AbstractBullet> workerClass) {
        BulletSkill skill = tower.getBulletSkill(workerClass);

        if (tower.isInCooldown(workerClass)) {
            mListener.onBulletCreateFailCooldown();
            return null;
        }

        if (!tower.hasCash(workerClass)) {
            mListener.onBulletCreateFailCash(tower.getCash(), skill.getCost());
            return null;
        }

        return createUnitEx(tower, skill);
    }

    /**
     * Создание юнита без проверок
     * @param tower
     * @param skill
     */
    public AbstractBullet createUnitEx(Tower tower, BulletSkill skill) {
        AbstractBullet bullet;
        try {
            bullet = skill.getBulletClass().getConstructor(BattleGame.class).newInstance(this);
        } catch (Exception e) {
            Gdx.app.error("Game.createUnit", "error create bullet " + skill.getBulletClass().getName());
            e.printStackTrace();
            bullet = new Simple(this);
        }
        bullet.setSkill(skill);

        bullet.setWidth(30);
        bullet.setMaxHp(skill.getMaxHp());
        bullet.setHp(bullet.getMaxHp());
        bullet.setVelocity(skill.getMoveSpeed() * tower.getSpeedRatio());
        bullet.setX(tower.getX() + tower.getWidth() / 2);

        tower.setCash(tower.getCash() - skill.getCost());
        tower.addUnit(bullet);
        tower.toCooldown(bullet);

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


    private void initAiTower(Tower tower, EnemyTower enemyTower) {
        ControllerAi ai = new ControllerAi(this);
        ai.setEnemyParams(enemyTower);

        towerReset(tower, enemyTower.getX(), -1);

        tower.addBulletSkill(Simple.class, 1);
        tower.setMaxHp(enemyTower.getMaxHp());
        tower.setHp(enemyTower.getMaxHp());

        towerFinish(tower, ai);
    }

    private void initPlayerTower(Tower tower) {
        towerReset(tower, 10, 1);
        PlayerProfile profile = mGame.getProfileManager().getProfile();
        // доступные скилы
        for (Iterator<ProfileSkill> it = profile.getSkills().iterator(); it.hasNext(); ) {
            ProfileSkill profileSkill = it.next();
            AbstractSkillEntity skillEntity = mSkillManager.get(profileSkill.getSkillClass());
            SkillItem skill = new SkillItem();
            skill.setInfo(skillEntity);
            skill.setLevel(profileSkill.getLevel());
            Gdx.app.log("initPlayerTower", "Skill inited " + profileSkill.getSkillName());
            skillEntity.initTower(skill, tower);
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

}

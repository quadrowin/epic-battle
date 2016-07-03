package com.quadrolord.epicbattle.logic.skill;

import com.badlogic.gdx.Gdx;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractLogic;
import com.quadrolord.epicbattle.logic.tower.BattleGame;
import com.quadrolord.epicbattle.logic.tower.Tower;
import com.quadrolord.epicbattle.view.BulletUnitView;

import java.lang.reflect.ParameterizedType;

/**
 * Родитель для скилов - выстрелов.
 * Скил на выстрел как он есть у игрока в общем.
 * Общая информация по скилу (лвл, варианты прокачки).
 * Текущее состояние на поле боя (откаты, режимы), в другом классе.
 * Created by Quadrowin on 25.01.2016.
 */
abstract public class AbstractBulletSkill<T extends AbstractLogic> extends AbstractSkillEntity {

    private AbstractLogic mBulletLogic;

    private float mAttackDamage = 50;

    private float mAttackDistance = 1;

    private float mAttackTime = 1000;

    private float mConstructionTime = 1;

    private int mCost;

    private int mLevel;

    private float mMoveSpeed = 1;

    private int mMaxHp = 100;

    private int mMaxTargetCount = 1;

    public static int debug = 0;

    public AbstractBulletSkill() {
        if (debug > 0) {
            debug = 0;
        } else {
            Gdx.app.log("", "" + (1 / 0));
        }
    }

    public void assignBase(AbstractLogic info) {
        setAttackDamage(info.getAttackDamage());
        setAttackDistance(info.getAttackDistance());
        setAttackTime(info.getAttackTime());
        setCost(info.getCost());
        setConstructionTime(info.getConstructionTime());
        setIcon(info.getIcon());
        setMaxHp(info.getMaxHp());
        setMaxTargetCount(info.getMaxTargetCount());
        setMoveSpeed(info.getMoveSpeed());
        setName(info.getTitle());
    }

    public float getAttackDamage() {
        return mAttackDamage;
    }

    public float getAttackDistance() {
        return mAttackDistance;
    }

    public float getAttackTime() {
        return mAttackTime;
    }

    @Override
    public float getCooldownLength() {
        return mConstructionTime;
    }

    public float getConstructionTime() {
        return mConstructionTime;
    }

    @Override
    public int getSkillCost() {
        return mCost;
    }

    @Override
    public String getDescription()
    {
        return mBulletLogic.getDescription();
    }

    public float getMoveSpeed() {
        return mMoveSpeed;
    }

    public int getMaxHp() {
        return mMaxHp;
    }

    public int getMaxTargetCount() {
        return mMaxTargetCount;
    }

    public int getLevel() {
        return mLevel;
    }

    public AbstractLogic getBulletLogic() {
        return mBulletLogic;
    }

    public Class<? extends AbstractBullet> getBulletClass() {
        return mBulletLogic.getBulletClass();
    }

    public Class<? extends BulletUnitView> getViewClass() {
        return mBulletLogic.getViewClass();
    }

    @Override
    public void initSkill(BattleGame game)
    {
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        Class parameter = (Class)type.getActualTypeArguments()[0];
        mBulletLogic = game.getBulletInfoManager().getBulletLogic(parameter);
        assignBase(mBulletLogic);
    }

    /**
     * Инициализация башни в начале уровня
     * @param tower
     */
    @Override
    public void initTower(SkillItem skill, Tower tower) {
//        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
//        Class parameter = (Class)type.getActualTypeArguments()[0];
//        mBulletLogic = game.getBulletInfoManager().getBulletLogic(parameter);
//        tower.addSkillEntity(this, skill.getLevel());
    }

    public void setAttackDistance(float attackDistance) {
        mAttackDistance = attackDistance;
    }

    public void setAttackTime(float attackTime) {
        mAttackTime = attackTime;
    }

    public void setAttackDamage(float attackDamage) {
        mAttackDamage = attackDamage;
    }

    public void setConstructionTime(float constructionTime) {
        mConstructionTime = constructionTime;
    }

    public void setCost(int cost) {
        mCost = cost;
    }

    public void setMaxHp(int maxHp) {
        mMaxHp = maxHp;
    }

    public void setMoveSpeed(float moveSpeed) {
        mMoveSpeed = moveSpeed;
    }

    public void setMaxTargetCount(int maxTargetCount) {
        mMaxTargetCount = maxTargetCount;
    }

}

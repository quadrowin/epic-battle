package com.quadrolord.epicbattle.logic.profile;

import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.skill.AbstractSkill;

import java.util.Iterator;

/**
 * Created by Quadrowin on 14.01.2016.
 */
public class PlayerProfile {

    /**
     * Кол-во запусков битв
     */
    private int battlesStarted = 0;

    /**
     * Кол-во побед
     */
    private int battlesVictory = 0;

    /**
     * Кол-во поражений
     */
    private int battlesDefeat = 0;

    /**
     * Текущий опыт
     */
    private long experience = 0;

    /**
     * Всего получено опыта
     */
    private long experienceTotal = 0;

    private String name;

    private Array<ProfileSkill> skills = new Array<ProfileSkill>();

    private Array<ProfileBullet> bullets = new Array<ProfileBullet>();

    public Array<ProfileBullet> getBullets() {
        return bullets;
    }

    public ProfileBullet addBullet(Class<? extends AbstractBullet> bulletClass) {
        ProfileBullet bullet = new ProfileBullet();
        bullet.setBulletClass(bulletClass);
        bullets.add(bullet);
        return bullet;
    }

    public ProfileBullet addBulletSafe(Class<? extends AbstractBullet> bulletClass) {
        if (hasBullet(bulletClass)) {
            return null;
        }
        return addBullet(bulletClass);
    }

    public void addSkill(Class<? extends AbstractSkill> skillClass, int level) {
        ProfileSkill sk = new ProfileSkill();
        sk.setSkillClass(skillClass);
        sk.setLevel(level);
        skills.add(sk);
    }

    public long decExperience(long value) {
        return experience -= value;
    }

    public long getExperience() {
        return experience;
    }

    public long getExperienceTotal() {
        return experienceTotal;
    }

    public boolean hasBullet(Class<? extends AbstractBullet> bulletClass) {
        String bulletClassName = bulletClass.getName();
        for (Iterator<ProfileBullet> it = bullets.iterator(); it.hasNext(); ) {
            ProfileBullet pb = it.next();
            if (pb.getBulletName().equals(bulletClassName)) {
                return true;
            }
        }
        return false;
    }

    public long incExperience(long value) {
        experienceTotal += value;
        return experience += value;
    }

    public String getName() {
        return name;
    }

    public Array<ProfileSkill> getSkills() {
        return skills;
    }

    public void setName(String value) {
        name = value;
    }

    public void setSkills(Array<ProfileSkill> value) {
        skills = value;
    }

}

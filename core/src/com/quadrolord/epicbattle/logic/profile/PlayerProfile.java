package com.quadrolord.epicbattle.logic.profile;

import com.badlogic.gdx.utils.Array;
import com.quadrolord.epicbattle.logic.bullet.worker.AbstractBullet;
import com.quadrolord.epicbattle.logic.skill.AbstractSkill;

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

    public void addBullet(Class<? extends AbstractBullet> bulletClass) {
        ProfileBullet bullet = new ProfileBullet();
        bullet.setBulletClass(bulletClass);
        bullets.add(bullet);
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
